package com.ohm.service;
import com.ohm.config.AppConfig;
import com.ohm.dto.CeoDto.CeoDto;
import com.ohm.dto.requestDto.ManagerRequestDto;
import com.ohm.entity.Ceo.Ceo;
import com.ohm.entity.Code;
import com.ohm.entity.Enum.Role;
import com.ohm.repository.ceo.CeoRepository;
import com.ohm.repository.gym.GymRepository;
import com.ohm.repository.manager.CodeRepository;
import com.ohm.s3.AmazonS3ResourceStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CeoService implements UserDetailsService {
    private final AmazonS3ResourceStorage amazonS3ResourceStorage;
    private final CeoRepository ceoRepository;
    private final GymRepository gymRepository;
    private final AppConfig appConfig;
    private final CodeRepository codeRepository;
    private final PasswordEncoder passwordEncoder;


    public boolean check_code(String code) {
        Optional<Code> code1 = codeRepository.findCode(code);
        if (code1.get() == null) {
            return false;
        } else {
            return true;
        }
    }

    //Manager 회원가입
    public CeoDto ceo_save(ManagerRequestDto managerDto) {
        if (ceoRepository.findByName(managerDto.getName()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 아이디.");
        }

//
        System.out.println("111");
        Ceo ceo = Ceo.builder()
                .name(managerDto.getName())
                .position(managerDto.getPosition())
                .password(passwordEncoder.encode(managerDto.getPassword()))
                .nickname(managerDto.getNickname())
                .profileUrl(managerDto.getProfile())
                .onelineIntroduce(managerDto.getOnelineIntroduce())
                .introduce(managerDto.getIntroduce())
                .role(Role.ROLE_CEO)
                .build();
        System.out.println("222");

        Ceo save = ceoRepository.save(ceo);
        System.out.println("333");
        return appConfig.modelMapper().map(save, CeoDto.class);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
