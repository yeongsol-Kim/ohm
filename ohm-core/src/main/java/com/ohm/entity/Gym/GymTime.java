package com.ohm.entity.Gym;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "gym_time")
public class GymTime {

    @Id
    @GeneratedValue
    @Column(name = "gym_time_id")
    private Long id;

    //휴관일
    @Column(name = "close_day")
    private String closeDay;


    //평일
    @Column(name = "monday")
    private String monday;
    @Column(name = "tuesday")
    private String tuesday;
    @Column(name = "wednesday")
    private String wednesday;
    @Column(name = "thursday")
    private String thursday;
    @Column(name = "friday")
    private String friday;

    @Column(name = "saturday")
    private String saturday;
    @Column(name = "sunday")
    private String sunday;

    //공휴일

    @Column(name = "holiday")
    private String holiday;

    @OneToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    public void update(GymTime gymTime){

        this.id = gymTime.getId();
        this.sunday = gymTime.sunday;
        this.saturday = gymTime.saturday;
        this.holiday = gymTime.holiday;
        this.monday = gymTime.monday;
        this.tuesday = gymTime.tuesday;
        this.wednesday = gymTime.wednesday;
        this.thursday = gymTime.thursday;
        this.friday = gymTime.friday;

    }
}


