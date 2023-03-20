package com.ohm.entity.Ceo;


import com.ohm.dto.ManagerDto.ManagerDto;

import com.ohm.entity.Admin;
import com.ohm.entity.Enum.Role;
import com.ohm.entity.Gym.Gym;

import com.ohm.entity.embedded.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ceo")
public class Ceo extends BaseTime {


    @Id
    @GeneratedValue()
    @Column(name = "ceo_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    //프로필사진
    @Column(name = "profile_url")
    private String profileUrl;


    private String position;


    //실제이름
    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "ceo",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Gym> gyms = new ArrayList<>();


    public void register_profile(String profile, String profileOriginName) {
        this.profileUrl = profile;

    }


    public void update(ManagerDto manager) {
        this.username = manager.getUsername();
        this.nickname = manager.getNickname();
    }


//    @Builder
//    public Ceo(String position, Gym gym, String name, String profileOrignName, String password, String nickname, String profile, String oneline_introduce, String introduce, Integer age, String email, Set<Authority> authorities) {
//        this.name = name;
//        this.position = position;
//        this.profileOrignName = profileOrignName;
//        this.createdTime = LocalDateTime.now();
//        this.password = password;
//        this.nickname = nickname;
//        this.profileUrl = profile;
//        this.onelineIntroduce = oneline_introduce;
//        this.introduce = introduce;
//    }



}
