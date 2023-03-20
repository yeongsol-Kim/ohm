package com.ohm.entity;

import com.ohm.entity.Manager.Manager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
public class PrincipalDetails implements UserDetails {

    private Manager user;

    public PrincipalDetails(Manager user) {
        this.user = user;
    }

    public Long getGymId() {
        return user.getGym().getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();


//        log.info("aaaa");
//        for(AccountAuthority a : user.getAuthorities()) {
////            log.info(a.getAuthority().getAuthorityName());
        collection.add(new SimpleGrantedAuthority(user.getRole().toString()));
//        }
//        log.info("dddd");

//        user.getAuthorities().stream().map(a ->
//                collection.add(new SimpleGrantedAuthority(a.getAuthority().getAuthorityName()))
//        );

        return collection;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
//        user.getAuthorities().stream().map(s -> authList.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    System.out.println(s.getAuthorityName());
//                    return s.getAuthorityName();
//                }
//            })
//        );
//
//        return authList;
//    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
