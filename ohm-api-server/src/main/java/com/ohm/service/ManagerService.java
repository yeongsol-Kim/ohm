package com.ohm.service;


import com.ohm.entity.Enum.Role;
import com.ohm.repository.ceo.CeoRepository;
import com.ohm.s3.AmazonS3ResourceStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ohm.config.AppConfig;
import com.ohm.dto.GymDto.GymDto;
import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.dto.requestDto.ManagerRequestDto;
import com.ohm.dto.responseDto.TrainerResponseDto;
import com.ohm.entity.Manager.Manager;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.manager.ManagerRepository;
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
public class ManagerService{

    private final CeoRepository ceoRepository;
    private final AmazonS3ResourceStorage amazonS3ResourceStorage;
    private final ManagerRepository managerRepository;
    private final GymRepository gymRepository;
    private final AppConfig appConfig;
    private final PasswordEncoder passwordEncoder;


    public void change_showProfile(Long managerId){
        Optional<Manager> byId = managerRepository.findById(managerId);
        byId.get().change_showProfile();
    }

    public void delete(Long managerId){
        Optional<Manager> byId = managerRepository.findById(managerId);
        managerRepository.delete(byId.get());
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

    public void profile_edit(Long managerId, MultipartFile multipartFile) throws Exception {
        Optional<Manager> byId = managerRepository.findById(managerId);
        String profileUrl = byId.get().getProfileUrl();

        if (profileUrl == null) {

        } else {
            amazonS3ResourceStorage.deleteObjectByKey(byId.get().getProfileUrl());
        }
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("yyyyMMdd");
        String current_date = now.format(dateTimeFormatter);
        String uuid_string = UUID.randomUUID().toString();


        String ext = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        //url,orignName
        byId.get().register_profile(current_date + File.separator + uuid_string + ext, multipartFile.getOriginalFilename());
        amazonS3ResourceStorage.upload(multipartFile, current_date, uuid_string + ext);


        byId.get().register_profile(current_date + File.separator + uuid_string + ext, multipartFile.getOriginalFilename());
    }

   // Manager 회원가입
    public ManagerDto managerSave(ManagerRequestDto managerDto, Long gymId) {
        //헬스장 정보 주입
        managerDto.setGym(gymRepository.findById(gymId).orElse(null));
        return saveManagerAndReturnDto(managerDto, Role.ROLE_MANAGER);
    }


    public ManagerDto trainerSave(ManagerRequestDto managerDto, Long gymId) {
        managerDto.setGym(gymRepository.findById(gymId).orElse(null));


        return saveManagerAndReturnDto(managerDto, Role.ROLE_TRAINER);
    }

    private ManagerDto saveManagerAndReturnDto(ManagerRequestDto managerDto, Role role) {
        if (ceoRepository.findByUsername(managerDto.getUsername()).orElse(null) != null || managerRepository.findByUsername(managerDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 아이디입니다.");
        }

        Manager manager = Manager.builder()
                .username(managerDto.getUsername())
                .gym(managerDto.getGym())
                .showProfile(true)
                .position(managerDto.getPosition())
                .password(passwordEncoder.encode(managerDto.getPassword()))
                .nickname(managerDto.getNickname())
                .profileUrl(managerDto.getProfile())
                .role(role)
                .onelineIntroduce("한줄소개")
                .introduce("자기 소개")
                .build();


        Manager savedManager = managerRepository.save(manager);
        return appConfig.modelMapper().map(savedManager, ManagerDto.class);
    }


    public ManagerDto getManagerInfo(Long id) {

        Optional<Manager> findmanager = managerRepository.findOneWithGymById(id);

        ManagerDto managerDto = ManagerDto.builder()
                .name(findmanager.get().getUsername())
                .gymDto(appConfig.modelMapper().map(findmanager.get().getGym(), GymDto.class))
                .id(findmanager.get().getId())
                .nickname(findmanager.get().getNickname())
                .introduce(findmanager.get().getIntroduce())
                .oneline_introduce(findmanager.get().getOnelineIntroduce())
                .profile(findmanager.get().getProfileUrl())
                .build();


        return managerDto;
    }



    //Id로 매니저 조회
    public TrainerResponseDto findByID(Long id) {
        Optional<Manager> byId = managerRepository.findById(id);
        return appConfig.modelMapper().map(byId.get(), TrainerResponseDto.class);
    }

    public List<TrainerResponseDto> managerFindall(Long gymId) {
        List<Optional<Manager>> managers = managerRepository.findall_byGymId(gymId);
        List<TrainerResponseDto> trainerResponseDtos = new ArrayList<TrainerResponseDto>();

        for (Optional<Manager> manager : managers) {
            trainerResponseDtos.add(appConfig.modelMapper().map(manager.get(), TrainerResponseDto.class));
        }

        return trainerResponseDtos;
    }


    //매니저 정보수정
    public Optional<Manager> update(ManagerDto updateDto) {
        Optional<Manager> byId = managerRepository.findById(updateDto.getId());
        byId.get().update(updateDto);
        return byId;
    }





}
