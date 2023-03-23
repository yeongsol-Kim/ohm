package com.ohm.entity.Statistics;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.ohm.entity.Gym.Gym;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Statistics {

    @Id
    @GeneratedValue
    @Column(name = "statistics_id")
    private Long id;

    @Column(name = "one")
    private double one;

    @Column(name = "two")
    private double two;

    @Column(name = "three")
    private double three;

    @Column(name = "four")
    private double four;

    @Column(name = "four")
    private double five;

    @Column(name = "six")
    private double six;

    @Column(name = "seven")
    private double seven;

    @Column(name = "eight")
    private double eight;

    @Column(name = "nine")
    private double nine;

    @Column(name = "ten")
    private double ten;

    @Column(name = "eleven")
    private double eleven;

    @Column(name = "twelve")
    private double twelve;

    @Column(name = "thirteen")
    private double thirteen;

    @Column(name = "fourteen")
    private double fourteen;

    @Column(name = "fifteen")
    private double fifteen;

    @Column(name = "sixteen")
    private double sixteen;

    @Column(name = "seventeen")
    private double seventeen;

    @Column(name = "eighteen")
    private double eighteen;

    @Column(name = "nineteen")
    private double nineteen;

    @Column(name = "twenty")
    private double twenty;

    @Column(name = "twenty_one")
    private double twentyOne;

    @Column(name = "twenty_two")
    private double twentyTwo;

    @Column(name = "twenty_three")
    private double twentyThree;

    @Column(name = "twenty_four")
    private double twentyFour;

    @OneToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @Builder
    public Statistics(Gym gym) {
        this.gym = gym;
        this.one = 0;
        this.two = 0;
        this.three = 0;
        this.four = 0;
        this.five = 0;
        this.six = 0;
        this.seven = 0;
        this.eight = 0;
        this.nine = 0;
        this.ten = 0;
        this.eleven = 0;
        this.twelve = 0;
        this.thirteen = 0;
        this.fourteen = 0;
        this.fifteen = 0;
        this.sixteen = 0;
        this.seventeen = 0;
        this.eighteen = 0;
        this.nineteen = 0;
        this.twenty = 0;
        this.twentyOne = 0;
        this.twentyTwo = 0;
        this.twentyThree = 0;
        this.twentyFour = 0;


    }


}
