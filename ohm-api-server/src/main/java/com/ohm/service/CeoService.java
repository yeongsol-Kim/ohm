package com.ohm.service;
import com.ohm.config.AppConfig;
import com.ohm.dto.CeoDto.CeoDto;
import com.ohm.dto.GymDto.GymDto;
import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.dto.requestDto.ManagerRequestDto;
import com.ohm.dto.responseDto.GymImgResponseDto;
import com.ohm.dto.responseDto.GymResponseDto;
import com.ohm.entity.Ceo.Ceo;
import com.ohm.entity.Code;
import com.ohm.entity.Enum.Role;
import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Gym.GymImg;
import com.ohm.entity.Manager.Manager;
import com.ohm.repository.ceo.CeoRepository;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.manager.CodeRepository;
import com.ohm.repository.manager.ManagerRepository;
import com.ohm.s3.AmazonS3ResourceStorage;
import com.ohm.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CeoService  {


    private final ManagerRepository managerRepository;
    private final AmazonS3ResourceStorage amazonS3ResourceStorage;
    private final GymRepository gymRepository;
    private final CeoRepository ceoRepository;
    private final AppConfig appConfig;
    private final CodeRepository codeRepository;
    private final PasswordEncoder passwordEncoder;

    public List<GymResponseDto> findall_gyms(Long ceoId){


        List<Gym> gyms = gymRepository.findallGymsByCeoId(ceoId);

        List<GymResponseDto> gymDtos = new ArrayList<GymResponseDto>();

        System.out.println(gyms.size());
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
                    .oneline_introduce(gym.getOnelineIntroduce())
                    .imgs(gymImgDtos)
                    .count(gym.getCurrentCount()).build();

            gymDtos.add(gymResponseDto);
        }
        System.out.println(gymDtos.size());
        return gymDtos;
    }

    public boolean check_code(String code) {
        Optional<Code> code1 = codeRepository.findCode(code);
        if (code1.get() == null) {
            return false;
        } else {
            return true;
        }
    }

    public void profile_save(Long managerId, MultipartFile multipartFile) throws Exception {
        Optional<Manager> byId = managerRepository.findById(managerId);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyyMMdd");
        String current_date = now.format(dateTimeFormatter);
        String uuid_string = UUID.randomUUID().toString();


        String ext = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        //url,orignName
        byId.get().register_profile(current_date + File.separator + uuid_string + ext, multipartFile.getOriginalFilename());
        amazonS3ResourceStorage.upload(multipartFile, current_date, uuid_string + ext);
    }

    //Manager 회원가입
    public CeoDto ceo_save(ManagerRequestDto managerDto) {
        if (ceoRepository.findByUsername(managerDto.getUsername()).orElse(null) != null || managerRepository.findByUsername(managerDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 아이디.");
        }

//
        Ceo ceo = Ceo.builder()
                .username(managerDto.getUsername())
                .password(passwordEncoder.encode(managerDto.getPassword()))
                .nickname(managerDto.getNickname())
                .role(Role.ROLE_CEO)


                .build();

        Ceo save = ceoRepository.save(ceo);
        return appConfig.modelMapper().map(save, CeoDto.class);

    }

    //현재 시큐리티에 담겨져있는 계정 권한 가져오는 메서드
//    public CeoDto getMyManagerWithAuthorities() {
//        return appConfig.modelMapper().map(SecurityUtils.getCurrentUsername().flatMap(ceoRepository::findByUsername).get(), CeoDto.class);
//    }


}
