package com.ohm.entity.Manager;

import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.entity.Gym.Gym;
import com.ohm.entity.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;


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

    @Column(name = "username")
    private String username;

    @CreatedDate
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @LastModifiedBy
    @Column(name = "last_modified_time")
    private LocalDateTime lastModifiedTime;

    @Column(name = "password")
    private String password;

    //프로필사진
    @Column(name = "profile_url")
    private String profileUrl;

    //프사이름
    @Column(name = "profile_origin_name")
    private String profileOriginName;

    //한줄소개
    @Column(name = "online_introduce")
    private String onelineIntroduce;

    //자기소개
    @Column(name = "introduce")
    private String introduce;

    @Column(name = "position")
    private String position;

    @Column(name = "show_profile")
    private boolean showProfile;

    //실제이름
    @Column(name = "nickname")
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    //프로필 노출여부 변경메서드
    public void change_showProfile(){
        this.showProfile = !this.showProfile;
    }

    //프로필 사진 등록 메서드
    public void register_profile(String profile, String profileOriginName) {
        this.profileUrl = profile;
        this.profileOriginName = profileOriginName;
    }


    //프로필 수정 메서드
    public void update(ManagerDto manager) {

        this.position = manager.getPosition();
        this.lastModifiedTime = LocalDateTime.now();
        this.nickname = manager.getNickname();
        this.onelineIntroduce = manager.getOnelineIntroduce();
        this.introduce = manager.getIntroduce();
    }

}
