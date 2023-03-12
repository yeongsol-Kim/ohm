package com.ohm.entity;

import com.ohm.entity.Manager.Manager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class PrincipalDetails implements UserDetails {

    private Manager user;
    private ArrayList<GrantedAuthority> authorities;

    public PrincipalDetails(Manager user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority("ROLE_CEO"));
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
        return user.getName();
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
