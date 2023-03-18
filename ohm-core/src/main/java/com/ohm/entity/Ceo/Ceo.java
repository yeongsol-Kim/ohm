package com.ohm.entity.Ceo;


import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.entity.Admin;
import com.ohm.entity.Enum.Role;
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


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ceo")
public class Ceo {


    @Id
    @GeneratedValue()
    @Column(name = "ceo_id")
    private Long id;

    private String name;

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedBy
    private LocalDateTime lastModifiedTime;

    private String password;

    //프로필사진
    private String profileUrl;


    //한줄소개
    private String onelineIntroduce;

    //자기소개
    private String introduce;

    private String position;

    //실제이름
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "ceo",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<Gym> gyms = new ArrayList<>();

    public void register_profile(String profile, String profileOrignName) {
        this.profileUrl = profile;
    }


    public void update(ManagerDto manager) {

        this.position = manager.getPosition();
        this.name = manager.getName();
        this.lastModifiedTime = LocalDateTime.now();
        this.nickname = manager.getNickname();
        this.onelineIntroduce = manager.getOnelineIntroduce();
        this.introduce = manager.getIntroduce();
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
