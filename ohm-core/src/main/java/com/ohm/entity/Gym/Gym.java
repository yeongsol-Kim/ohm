package com.ohm.entity.Gym;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ohm.entity.Ceo.Ceo;
import lombok.*;
import com.ohm.entity.Manager.Manager;
import com.ohm.entity.Post.Post;
import com.ohm.entity.Question.Question;
import com.ohm.entity.Statistics.Statistics;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "gym")
public class Gym{

    @Id
    @GeneratedValue
    @Column(name = "gym_id")
    private Long id;

    //헬스장이름
    @Column(name = "name")
    private String name;

    //헬스장주소
    @Column(name = "address")
    private String address;

    //헬스장 총인원
    private int count;

    //한줄소개
    @Column(name = "oneline_introduce")
    private String onelineIntroduce;


    //트레이너가 가입시 해당 code로 인증후 어느 헬스장인지 식별
    private int code;

    //헬스장 소개 문구
    @Column(name = "introduce")
    private String introduce;


    //헬스장 면적수
    @Column(name = "area")
    private String area;

    @Column(name = "trainer_count")
    private int trainerCount;

    //헬스장 현재 인원
    @Column(name = "current_count")
    private Long currentCount;

    //헬스장 사진
    @JsonIgnore
    @OneToMany(mappedBy = "gym",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private List<GymImg> imgs;


    @OneToOne(mappedBy = "gym",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private Statistics statistics;


    @OneToOne(mappedBy = "gym",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private GymTime gymTime;

    @JsonIgnore
    @OneToMany(mappedBy = "gym",cascade = CascadeType.PERSIST,orphanRemoval = true)
    private final List<Manager> managers = new ArrayList<Manager>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ceo_id")
    private Ceo ceo;


    @JsonIgnore
    @OneToMany(mappedBy = "gym",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private final List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "gym",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<GymPrice> prices;

    @OneToMany(mappedBy = "gym",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Question> questions;


    public void register_time(GymTime gymTime){
        this.gymTime = gymTime;

    }

    public void update(Gym gym){
        this.name = gym.getName();
        this.id = gym.getId();
        this.area = gym.getArea();
        this.onelineIntroduce = gym.getOnelineIntroduce();
        this.trainerCount = gym.getTrainerCount();
        this.address = gym.getAddress();
        this.introduce = gym.getIntroduce();
        this.count = gym.getCount();
        this.code = gym.getCode();
    }

    public Long increaseCount() {
        this.currentCount = this.currentCount + 1;
        return currentCount;
    }

    public Long decreaseCount() {
        if (this.currentCount > 0) this.currentCount = this.currentCount - 1;
        return currentCount;
    }

    public Long resetCount() {
        this.currentCount = 0L;
        return currentCount;
    }

}
