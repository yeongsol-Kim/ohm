package com.ohm.service;

import com.ohm.config.AppConfig;
import com.ohm.dto.CeoDto.CeoDto;
import com.ohm.dto.requestDto.ManagerRequestDto;
import com.ohm.dto.responseDto.GymImgResponseDto;
import com.ohm.dto.responseDto.GymResponseDto;
import com.ohm.entity.Ceo.Ceo;
import com.ohm.entity.Enum.Role;
import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Gym.GymImg;
import com.ohm.repository.ceo.CeoRepository;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.manager.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CeoService {


    private final ManagerRepository managerRepository;
    private final GymRepository gymRepository;
    private final CeoRepository ceoRepository;
    private final AppConfig appConfig;
    private final PasswordEncoder passwordEncoder;


    //ceoId로 ceo가 가지고있는 모든 gym 조회
    public List<GymResponseDto> findallGyms(Long ceoId) {
        List<Gym> gyms = gymRepository.findallGymsByCeoId(ceoId);
        List<GymResponseDto> gymDtos = new ArrayList<GymResponseDto>();

        for (Gym gym : gyms) {
            List<GymImgResponseDto> gymImgDtos = new ArrayList<GymImgResponseDto>();
            //프록시로 가지고 있다가 여기 시점에 쿼리를 날려 연관관계 조회
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


    //Ceo 회원가입
    public CeoDto ceoSave(ManagerRequestDto managerDto) {
        //아이디 동일성 검증 로직
        if (ceoRepository.findByUsername(managerDto.getUsername()).orElse(null) != null || managerRepository.findByUsername(managerDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 아이디입니다.");
        }

        Ceo ceo = Ceo.builder()
                .available(false)
                .username(managerDto.getUsername())
                .password(passwordEncoder.encode(managerDto.getPassword()))
                .nickname(managerDto.getNickname())
                .role(Role.ROLE_CEO)
                .build();

        Ceo save = ceoRepository.save(ceo);
        return appConfig.modelMapper().map(save, CeoDto.class);

    }


}
