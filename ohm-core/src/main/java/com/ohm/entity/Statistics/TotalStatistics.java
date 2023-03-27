package com.ohm.entity.Statistics;


import com.ohm.entity.Gym.Gym;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "total_statistics")
public class TotalStatistics {

    @Id
    @GeneratedValue
    @Column(name = "total_statistics_id")
    private Long id;

    @Column(name = "zero")
    private Long zero;

    @Column(name = "one")
    private Long one;

    @Column(name = "two")
    private Long two;

    @Column(name = "three")
    private Long three;

    @Column(name = "four")
    private Long four;

    @Column(name = "five")
    private Long five;

    @Column(name = "six")
    private Long six;

    @Column(name = "seven")
    private Long seven;

    @Column(name = "eight")
    private Long eight;

    @Column(name = "nine")
    private Long nine;

    @Column(name = "ten")
    private Long ten;

    @Column(name = "eleven")
    private Long eleven;

    @Column(name = "twelve")
    private Long twelve;

    @Column(name = "thirteen")
    private Long thirteen;

    @Column(name = "fourteen")
    private Long fourteen;

    @Column(name = "fifteen")
    private Long fifteen;

    @Column(name = "sixteen")
    private Long sixteen;

    @Column(name = "seventeen")
    private Long seventeen;

    @Column(name = "eighteen")
    private Long eighteen;

    @Column(name = "nineteen")
    private Long nineteen;

    @Column(name = "twenty")
    private Long twenty;

    @Column(name = "twenty_one")
    private Long twentyOne;

    @Column(name = "twenty_two")
    private Long twentyTwo;

    @Column(name = "twenty_three")
    private Long twentyThree;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    public void update(Statistics statistics){
        if(statistics.getZero() == 0L){
            this.zero = this.zero;
        }if(statistics.getZero() != 0L){
            this.zero = (this.zero + statistics.getZero())/2;
        }
        if(statistics.getOne() == 0L){
            this.one = this.one;
        }if(statistics.getOne() != 0L){
            this.one = (this.one + statistics.getOne())/2;
        }
        if(statistics.getTwo() == 0L){
            this.two = this.two;
        }if(statistics.getTwo() != 0L){
            this.two = (this.two + statistics.getTwo())/2;
        }
        if(statistics.getThree() == 0L){
            this.three = this.three;
        }if(statistics.getThree() != 0L){
            this.three = (this.three + statistics.getThree())/2;
        }
        if(statistics.getFour() == 0L){
            this.four = this.four;
        }if(statistics.getFour() != 0L){
            this.four = (this.four + statistics.getFour())/2;
        }
        if(statistics.getFive() == 0L){
            this.five = this.five;
        }if(statistics.getFive() != 0L){
            this.five = (this.five + statistics.getFive())/2;
        }
        if(statistics.getSix() == 0L){
            this.six = this.six;
        }if(statistics.getSix() != 0L){
            this.six = (this.six + statistics.getSix())/2;
        }
        if(statistics.getSeven() == 0L){
            this.seven = this.seven;
        }if(statistics.getSeven() != 0L){
            this.seven = (this.seven + statistics.getSeven())/2;
        }
        if(statistics.getEight() == 0L){
            this.eight = this.eight;
        }if(statistics.getEight() != 0L){
            this.eight = (this.eight + statistics.getEight())/2;
        }
        if(statistics.getNine() == 0L){
            this.nine = this.nine;
        }if(statistics.getNine() != 0L){
            this.nine = (this.nine + statistics.getNine())/2;
        }
        if(statistics.getTen() == 0L){
            this.ten = this.ten;
        }if(statistics.getTen() != 0L){
            this.ten = (this.ten + statistics.getTen())/2;
        }
        if(statistics.getEleven() == 0L){
            this.eleven = this.eleven;
        }if(statistics.getEleven() != 0L){
            this.eleven = (this.eleven + statistics.getEleven())/2;
        }
        if(statistics.getTwelve() == 0L){
            this.twelve = this.twelve;
        }if(statistics.getTwelve() != 0L){
            this.twelve = (this.twelve + statistics.getTwelve())/2;
        }
        if(statistics.getThirteen() == 0L){
            this.thirteen = this.one;
        }if(statistics.getThirteen() != 0L){
            this.thirteen = (this.thirteen + statistics.getThirteen())/2;
        }
        if(statistics.getFourteen() == 0L){
            this.fourteen = this.fourteen;
        }if(statistics.getFourteen() != 0L){
            this.fourteen = (this.fourteen + statistics.getFourteen())/2;
        }
        if(statistics.getFifteen() == 0L){
            this.fifteen = this.fifteen;
        }if(statistics.getFifteen() != 0L){
            this.fifteen = (this.fifteen + statistics.getFifteen())/2;
        }
        if(statistics.getSixteen() == 0L){
            this.sixteen = this.sixteen;
        }if(statistics.getSixteen() != 0L){
            this.sixteen = (this.sixteen + statistics.getSixteen())/2;
        }
        if(statistics.getSeventeen() == 0L){
            this.seventeen = this.seventeen;
        }if(statistics.getSeventeen() != 0L){
            this.seventeen = (this.seventeen + statistics.getSeventeen())/2;
        }
        if(statistics.getEighteen() == 0L){
            this.eighteen = this.eighteen;
        }if(statistics.getEighteen() != 0L){
            this.eighteen = (this.eighteen + statistics.getEighteen())/2;
        }
        if(statistics.getNineteen() == 0L){
            this.nineteen = this.nineteen;
        }if(statistics.getNineteen() != 0L){
            this.nineteen = (this.nineteen + statistics.getNineteen())/2;
        }
        if(statistics.getTwenty() == 0L){
            this.twenty = this.twenty;
        }if(statistics.getTwenty() != 0L){
            this.twenty = (this.twenty + statistics.getTwenty())/2;
        }
        if(statistics.getTwentyOne() == 0L){
            this.twentyOne = this.twentyOne;
        }if(statistics.getTwentyOne() != 0L){
            this.twentyOne = (this.twentyOne + statistics.getTwentyOne())/2;
        }
        if(statistics.getTwentyTwo() == 0L){
            this.twentyTwo = this.twentyTwo;
        }if(statistics.getTwentyTwo() != 0L){
            this.twentyTwo = (this.twentyTwo + statistics.getTwentyTwo())/2;
        }
        if(statistics.getTwentyThree() == 0L){
            this.twentyThree = this.twentyThree;
        }if(statistics.getTwentyThree() != 0L){
            this.twentyThree = (this.twentyThree + statistics.getTwentyThree())/2;
        }


    }
}
