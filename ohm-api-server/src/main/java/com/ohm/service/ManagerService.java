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
import com.ohm.entity.Code;
import com.ohm.entity.Manager.Manager;
import com.ohm.repository.manager.CodeRepository;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.manager.ManagerRepository;
import com.ohm.utils.SecurityUtils;
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
import java.util.stream.Collectors;

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
    public ManagerDto manager_save(ManagerRequestDto managerDto, Long gymId) {
        managerDto.setGym(gymRepository.findById(gymId).orElse(null));
        return saveManagerAndReturnDto(managerDto, Role.ROLE_MANAGER);
    }


    public ManagerDto trainer_save(ManagerRequestDto managerDto, Long gymId) {
        managerDto.setGym(gymRepository.findById(gymId).orElse(null));


        return saveManagerAndReturnDto(managerDto, Role.ROLE_TRAINER);
    }

    private ManagerDto saveManagerAndReturnDto(ManagerRequestDto managerDto, Role role) {
        if (ceoRepository.findByName(managerDto.getName()).orElse(null) != null || managerRepository.findByName(managerDto.getName()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 아이디입니다.");
        }

        Manager manager = Manager.builder()
                .name(managerDto.getName())
                .position(managerDto.getPosition())
                .password(passwordEncoder.encode(managerDto.getPassword()))
                .nickname(managerDto.getNickname())
                .profileUrl(managerDto.getProfile())
                .role(role)
                .onelineIntroduce(managerDto.getOnelineIntroduce())
                .introduce(managerDto.getIntroduce())
                .build();


        Manager savedManager = managerRepository.save(manager);
        return appConfig.modelMapper().map(savedManager, ManagerDto.class);
    }


    //현재 시큐리티에 담겨져있는 계정 권한 가져오는 메서드
    public ManagerDto getMyManagerWithAuthorities() {
        System.out.println("SecurityUtils.getCurrentUsername()");
        System.out.println(SecurityUtils.getCurrentUsername());
        return appConfig.modelMapper().map(SecurityUtils.getCurrentUsername().flatMap(managerRepository::findByName).get(), ManagerDto.class);
    }

    public ManagerDto getManagerInfo(Long id) {

//        Manager findmanager = managerRepository.findManagerFetchJoinGym(id);
        Optional<Manager> findmanager = managerRepository.findOneWithGymById(id);

        ManagerDto managerDto = ManagerDto.builder()
                .name(findmanager.get().getName())
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

    public List<TrainerResponseDto> trainer_findall(Long gymId) {
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
        //update생성자로 변경감지
        byId.get().update(updateDto);
        return byId;
    }


    //Gym을 save할때 manager와 연관관계를 맺어주는 메서드
//    public void register_gym(Long gymId, Long manager_id) {
//        managerRepository.registerByGymId(manager_id, gymId);
//
//    }


    //매니저 삭제
    public void delete(Long id) {
        Optional<Manager> byId = managerRepository.findById(id);
        managerRepository.delete(byId.get());
    }

    // ------------시큐리티에서 사용되는 메서드 --------------
    private User createUser(String username, Manager manager) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(manager.getRole().toString());
        return new User(manager.getName(), manager.getPassword(), Collections.singleton(grantedAuthority));
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return managerRepository.findOneWithAuthoritiesByName(username)
//                .map(user -> createUser(username, user))
//                .orElseThrow(() -> new UsernameNotFoundException(username + "DB에서 찾을수 없다."));
//    }

}
