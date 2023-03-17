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

    @OneToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

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


