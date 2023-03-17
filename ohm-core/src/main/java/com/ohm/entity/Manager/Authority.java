package com.ohm.entity.Manager;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

//인가에 사용되는 권한들을 DB로 관리하고자 생성한 엔티티이다.
//Manager는 인증에 사용되는 계정 엔티티입니다.
@Entity
@Getter
@NoArgsConstructor
@Table(name = "authority")
public class Authority {

    @Id
    @Column(name = "authority_name",length = 50)
    private String authorityName;

    @OneToMany(mappedBy = "authority")
    private Set<AccountAuthority> accountAuthorities;


    @Builder
    public Authority(String authorityName) {
        this.authorityName = authorityName;
    }
}