package com.ohm.service;

import com.ohm.config.AppConfig;
import com.ohm.dto.responseDto.AdminResponseDto;
import com.ohm.entity.Ceo.Ceo;
import com.ohm.entity.Manager.Manager;
import com.ohm.repository.ceo.CeoRepository;
import com.ohm.repository.manager.ManagerRepository;
import com.ohm.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CustomAdminService implements UserDetailsService {

    private final CeoRepository ceoRepository;
    private final ManagerRepository managerRepository;
    private final AppConfig appConfig;

    //매니저 삭제
    public void delete() {
        Optional<String> currentUsername = SecurityUtils.getCurrentUsername();
        try {
            Optional<Ceo> ceo = ceoRepository.findByUsername(currentUsername.get());
            ceoRepository.delete(ceo.get());
            return;
        }catch (Exception userException){

            try {
                Optional<Manager> manager = managerRepository.findByUsername(currentUsername.get());
                managerRepository.delete(manager.get());
                return;
            }
            catch (Exception adminException) {
                throw new UsernameNotFoundException("No user present with username : ");
            }
        }
    }

    //현재 시큐리티에 담겨져있는 계정 권한 가져오는 메서드
    public AdminResponseDto getMyManagerWithAuthorities() {
        Optional<String> currentUsername = SecurityUtils.getCurrentUsername();

        try {
          //  Optional<Ceo> byUsername = ceoRepository.findByUsername(currentUsername.get());
            return appConfig.modelMapper().map(currentUsername.flatMap(ceoRepository::findByUsername).get(), AdminResponseDto.class);
        }catch (Exception userException){

            try {
         //       Optional<Manager> byUsername = managerRepository.findByUsername(currentUsername.get());
                return appConfig.modelMapper().map(currentUsername.flatMap(managerRepository::findByUsername).get(), AdminResponseDto.class);
            }
            catch (Exception adminException) {
                throw new UsernameNotFoundException("No user present with username : ");
            }
        }

    }
    //ceo로그인시
    private User createCeo(String username, Ceo ceo) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(ceo.getRole().toString());
        return new User(ceo.getUsername(), ceo.getPassword(), Collections.singleton(grantedAuthority));
    }

    //manager로그인시
    private User createManager(String username, Manager manager) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(manager.getRole().toString());
        return new User(manager.getUsername(), manager.getPassword(), Collections.singleton(grantedAuthority));
    }


    //로그인 통합
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Optional<Ceo> byName = ceoRepository.findByUsername(username);
            return createCeo(username,byName.get());
        }
        catch (Exception userException) {

            try {
                Manager manager = managerRepository.findByUsername(username).get();
                return createManager(username,manager);
            }
            catch (Exception adminException) {
                throw new UsernameNotFoundException("No user present with username : " + username);
            }
        }

    }
}
