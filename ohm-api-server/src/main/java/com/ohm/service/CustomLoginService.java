package com.ohm.service;

import com.ohm.entity.Ceo.Ceo;
import com.ohm.entity.Manager.Manager;
import com.ohm.repository.ceo.CeoRepository;
import com.ohm.repository.manager.ManagerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.users.AbstractUser;
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
public class CustomLoginService implements UserDetailsService {

    private final CeoRepository ceoRepository;
    private final ManagerRepository managerRepository;


    //ceo로그인시
    private User createCeo(String username, Ceo ceo) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(ceo.getRole().toString());
        return new User(ceo.getName(), ceo.getPassword(), Collections.singleton(grantedAuthority));
    }

    //manager로그인시
    private User createManager(String username, Manager manager) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(manager.getRole().toString());
        return new User(manager.getName(), manager.getPassword(), Collections.singleton(grantedAuthority));
    }


    //로그인 통합
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Optional<Ceo> byName = ceoRepository.findByName(username);
            return createCeo(username,byName.get());
        }
        catch (Exception userException) {

            try {
                Manager manager = managerRepository.findByName(username).get();
                return createManager(username,manager);
            }
            catch (Exception adminException) {
                throw new UsernameNotFoundException("No user present with username : " + username);
            }
        }

    }
}
