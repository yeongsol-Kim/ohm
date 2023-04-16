package com.ohm.service;

import com.ohm.entity.Ceo.Ceo;
import com.ohm.repository.ceo.CeoRepository;
import com.ohm.s3.AmazonS3ResourceStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ohm.config.AppConfig;
import com.ohm.dto.GymDto.GymDto;
import com.ohm.dto.GymDto.GymPriceDto;
import com.ohm.dto.GymDto.GymTimeDto;
import com.ohm.dto.requestDto.GymRequestDto;
import com.ohm.dto.responseDto.GymImgResponseDto;
import com.ohm.dto.responseDto.GymResponseDto;
import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Gym.GymImg;
import com.ohm.entity.Gym.GymPrice;
import com.ohm.entity.Gym.GymTime;
import com.ohm.repository.gym.GymImgRepository;
import com.ohm.repository.gym.GymPriceRepository;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.gym.GymTimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GymService {


    private final AmazonS3ResourceStorage amazonS3ResourceStorage;
    private final GymRepository gymRepository;
    private final GymImgRepository gymImgRepository;
    private final AppConfig appConfig;
    private final CeoRepository ceoRepository;
    private final GymTimeRepository gymTimeRepository;
    private final GymPriceRepository gymPriceRepository;


    //어플에서 선택한 price id를 가져와 삭제함
    @Transactional
    public void deletePrice(List<Long> ids) throws Exception {
        for (Long id : ids) {
            gymPriceRepository.delete(gymPriceRepository.findById(id).get());
        }
        return;
    }


    @Transactional
    public void deleteImg(List<Long> ids) throws Exception {

        for (Long id : ids) {
            GymImg gymImg = gymImgRepository.findById(id).get();
            amazonS3ResourceStorage.deleteObjectByKey(gymImg.getFilePath());
            gymImgRepository.delete(gymImg);
        }
    }


    @Transactional
    public Long registerGym(GymRequestDto gymDto, Long ceoId) throws Exception {

        Optional<Ceo> byId = ceoRepository.findById(ceoId);

        //Builder entity 영역으로 이동(리팩토링)
        Gym gym = Gym.builder()
                .address(gymDto.getAddress())
                //간접참조로 변경후 리팩토링
                .ceoId(byId.get().getId())
                .currentCount(0L)
                .count(gymDto.getCount())
                .name(gymDto.getName())
                .area(gymDto.getArea())
                .onelineIntroduce(gymDto.getOnelineIntroduce())
                .introduce(gymDto.getIntroduce())
                .build();


        return gymRepository.save(gym).getId();

    }

    @Transactional
    public Long saveImg(Long gymId, List<MultipartFile> files) throws Exception {
        Optional<Gym> gym = gymRepository.findById(gymId);
        if (files == null) {

        } else {

            for (MultipartFile multipartFile : files) {
                //로직분리(리팩토링)
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter dateTimeFormatter =
                        DateTimeFormatter.ofPattern("yyyyMMdd");
                String current_date = now.format(dateTimeFormatter);
                String uuid_string = UUID.randomUUID().toString();
                String ext = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

                GymImg gymImg = GymImg.builder()
                        .gym(gym.get())
                        .origFileName(multipartFile.getOriginalFilename())
                        .filePath(current_date + File.separator + uuid_string + ext)
                        .build();

                amazonS3ResourceStorage.upload(multipartFile, current_date, uuid_string + ext);
                gymImgRepository.save(gymImg);
            }
        }

        return gym.get().getId();
    }


    public List<GymResponseDto> findall() {
        //fetch join으로 gym img를 한번에 가져옴
        //fetch join으로 안가져와도 proxy 저장후 지연로딩으로 가져오지만 최적화를 위해 fetch join 사용
        List<Gym> gyms = gymRepository.findAllFetchJoin();

        List<GymResponseDto> gymDtos = new ArrayList<GymResponseDto>();

        for (Gym gym : gyms) {
            List<GymImgResponseDto> gymImgDtos = new ArrayList<GymImgResponseDto>();
            for (GymImg gymImg : gym.getImgs()) {
                gymImgDtos.add(appConfig.modelMapper().map(gymImg, GymImgResponseDto.class));
            }

            GymResponseDto gymResponseDto = GymResponseDto.builder()
                    .address(gym.getAddress())
                    .id(gym.getId())
                    .name(gym.getName())
                    .introduce(gym.getIntroduce())
                    .onelineIntroduce(gym.getOnelineIntroduce())
                    .imgs(gymImgDtos)
                    .count(gym.getCurrentCount()).build();

            gymDtos.add(gymResponseDto);
        }
        return gymDtos;
    }


    //GYM 이름으로 조회 - 클라이언트가사용
    public List<GymResponseDto> findByName(String name) {
        List<Gym> gyms = gymRepository.findByNameContaining(name);
        List<GymResponseDto> gymDtos = new ArrayList<GymResponseDto>();

        for (Gym gym : gyms) {
            List<GymImgResponseDto> gymImgDtos = new ArrayList<GymImgResponseDto>();
            for (GymImg gymImg : gym.getImgs()) {
                gymImgDtos.add(appConfig.modelMapper().map(gymImg, GymImgResponseDto.class));
            }

            GymResponseDto gymResponseDto = GymResponseDto.builder()
                    .address(gym.getAddress())
                    .name(gym.getName())
                    .id(gym.getId())
                    .introduce(gym.getIntroduce())
                    .onelineIntroduce(gym.getOnelineIntroduce())
                    .imgs(gymImgDtos)
                    .count(gym.getCurrentCount()).build();
            gymDtos.add(gymResponseDto);
        }
        return gymDtos;
    }


    //GYM ID로 조회
    public GymResponseDto findById(Long id) throws Exception {
        Gym gym = gymRepository.findGymFetchJoin(id);
        List<GymImgResponseDto> gymImgDtos = new ArrayList<GymImgResponseDto>();
        for (GymImg gymImg : gym.getImgs()) {
            gymImgDtos.add(appConfig.modelMapper().map(gymImg, GymImgResponseDto.class));
        }

        GymResponseDto gymResponseDto = GymResponseDto.builder()
                .address(gym.getAddress())

                .current_count(gym.getCurrentCount())
                .id(gym.getId())
                .name(gym.getName())
                .introduce(gym.getIntroduce())
                .onelineIntroduce(gym.getOnelineIntroduce())
                .imgs(gymImgDtos)
                .count(gym.getCurrentCount()).build();

        return gymResponseDto;

    }

    public Long findByIdCount(Long id) throws Exception {
        Optional<Gym> byId = gymRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get().getCurrentCount();
        } else {
            throw new Exception();
        }
    }


    //현재 GYM 인원수 조회
    public Long currentCount(Long id) {
        Optional<Gym> byId = gymRepository.findById(id);
        return byId.get().getCurrentCount();
    }


    //현재 GYM 인원수 증가(1증가)
    @Transactional
    public void increaseCount(Long id) {
        Gym gym = gymRepository.findById(id).orElse(null);
        gym.increaseCount();
    }

    //현재 GYM 인원수 감소(1감소)
    @Transactional
    public void decreaseCount(Long id) {
        Gym gym = gymRepository.findById(id).orElse(null);
        gym.decreaseCount();
    }

    //현재 GYM 인원수 0으로 초기화
    @Transactional
    public void resetCount(Long id) {
        Gym gym = gymRepository.findById(id).orElse(null);
        gym.resetCount();
    }

    @Transactional
    public Long registerPrice(Long gymId, GymPriceDto gymPriceDto) {
        Optional<Gym> byId = gymRepository.findById(gymId);
        GymPrice gymPrice_builder = GymPrice.builder()
                .price(gymPriceDto.getPrice())
                .during(gymPriceDto.getDuring())
                .gym(byId.get())
                .build();

        GymPrice save = gymPriceRepository.save(gymPrice_builder);
        return save.getId();
    }


    @Transactional
    public Long registerTime(Long gymId, GymTimeDto gymTimeDto) {

        Optional<Gym> byId = gymRepository.findById(gymId);

        GymTime gymTime = GymTime.builder()
                .gym(byId.get())
                .monday(gymTimeDto.getMonday())
                .tuesday(gymTimeDto.getTuesday())
                .wednesday(gymTimeDto.getWednesday())
                .thursday(gymTimeDto.getThursday())
                .friday(gymTimeDto.getFriday())
                .saturday(gymTimeDto.getSaturday())
                .sunday(gymTimeDto.getSunday())
                .holiday(gymTimeDto.getHoliday())
                .closeDay(gymTimeDto.getCloseDay())
                .build();
        GymTime save = gymTimeRepository.save(gymTime);
        byId.get().register_time(save);
        return save.getId();
    }

    public GymTimeDto getTime(Long gymId) {
        Gym timeByGymId = gymRepository.findTimeByGymId(gymId);
        GymTime gymTime = timeByGymId.getGymTime();
        return appConfig.modelMapper().map(gymTime, GymTimeDto.class);
    }

    public List<GymPriceDto> getPrices(Long gymId) {
        List<GymPrice> prices = gymPriceRepository.findPriceByGymId(gymId);
        List<GymPriceDto> priceDtos = new ArrayList<GymPriceDto>();
        for (GymPrice gymPrice : prices) {
            priceDtos.add(appConfig.modelMapper().map(gymPrice, GymPriceDto.class));
        }
        return priceDtos;
    }


    @Transactional
    public Optional<Gym> updateGym(GymDto gymDto) {
        Optional<Gym> byId = gymRepository.findById(gymDto.getId());
        byId.get().update(appConfig.modelMapper().map(gymDto, Gym.class));
        return byId;
    }

    @Transactional
    public Optional<GymTime> updateTime(Long gymId, GymTimeDto gymTimeDto) {

        Optional<GymTime> byId = gymTimeRepository.findById(gymTimeDto.getId());
        byId.get().update(appConfig.modelMapper().map(gymTimeDto, GymTime.class));

        return byId;
    }


}
