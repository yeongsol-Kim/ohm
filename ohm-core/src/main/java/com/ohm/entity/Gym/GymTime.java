package com.ohm.entity.Gym;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class GymTime {

    @Id
    @GeneratedValue
    @Column(name = "gymtime_id")
    private Long id;

    //휴관일
    private String closeddays;

    private String sunday;

    private String saturday;

    //평일
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;

    //공휴일
    private String holiday;

    @OneToOne(mappedBy = "gymTime")
    private Gym gym;

    @Builder
    public GymTime(Gym gym,String CLOSEDDAYS,String SUNDAY,String SATURDAY,String monday,String tuesday,String wednesday,String thursday,String friday,String HOLIDAY){
        this.closeddays =CLOSEDDAYS;
        this.sunday = SUNDAY;
        this.gym = gym;
        this.saturday = SATURDAY;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.holiday = HOLIDAY;
    }

    public void update(GymTime gymTime){

        this.id = gymTime.getId();
        this.sunday = gymTime.sunday;
       // this.gym = gym;
        this.closeddays = gymTime.closeddays;
        this.saturday = gymTime.saturday;
        this.holiday = gymTime.holiday;
        this.monday = gymTime.monday;
        this.tuesday = gymTime.tuesday;
        this.wednesday = gymTime.wednesday;
        this.thursday = gymTime.thursday;
        this.friday = gymTime.friday;

    }




}


