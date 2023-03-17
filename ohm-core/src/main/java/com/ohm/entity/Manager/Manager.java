package com.ohm.entity.Manager;

import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.entity.Admin;
import com.ohm.entity.Gym.Gym;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


// ROLE은 MANAGER,TRAINER 두개로 구분
//MANAGER는 헬스장 총 책임자(사장)
//TRAINER는 헬스장 소속 트레이너(직원)
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "manager")
public class Manager {


    @Id
    @GeneratedValue()
    @Column(name = "manager_id")
    private Long id;

    private String name;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedBy
    private LocalDateTime lastModifiedTime;

    private String password;

    //프로필사진
    private String profileUrl;

    //프사이름
    private String profileOriginName;

    //한줄소개
    private String onelineIntroduce;

    //자기소개
    private String introduce;

    private String position;

    //실제이름
    private String nickname;


//    @ManyToMany
//    @JoinTable( // JoinTable은 테이블과 테이블 사이에 별도의 조인 테이블을 만들어 양 테이블간의 연관관계를 설정 하는 방법
//            name = "account_authority",
//            joinColumns = {@JoinColumn(name = "manager_id", referencedColumnName = "manager_id")},
//            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
//    private Set<Authority> authorities;

    //    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AccountAuthority> authorities = new ArrayList<>();;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    public void addAuthority(Authority authority) {
        AccountAuthority accountAuthority = AccountAuthority.builder()
                .authority(authority)
                .manager(this)
                .build();
        authorities.add(accountAuthority);
        accountAuthority.setManager(this);
    }

    public void register_profile(String profile, String profileOriginName) {
        this.profileUrl = profile;
        this.profileOriginName = profileOriginName;
    }


    public void update(ManagerDto manager) {

        this.position = manager.getPosition();
        this.name = manager.getName();
        this.lastModifiedTime = LocalDateTime.now();
        this.nickname = manager.getNickname();
        this.onelineIntroduce = manager.getOnelineIntroduce();
        this.introduce = manager.getIntroduce();
    }

}