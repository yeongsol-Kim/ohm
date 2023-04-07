package com.ohm.service;

import com.ohm.entity.Manager.Manager;
import com.ohm.entity.PrincipalDetails;
import com.ohm.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager user = managerRepository.findByUsername(username).orElseThrow();
        if(user != null) {
            return new PrincipalDetails(user);
        }
        return null;
    }
}
