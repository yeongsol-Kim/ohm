package com.ohm.entity.Statistics;


import lombok.*;
import com.ohm.entity.Gym.Gym;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue
    @Column(name = "statistics_id")
    private Long id;

    @Column(name = "statistics_date")
    private LocalDate statisticsDate;

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

    @Column(name = "twenty_four")
    private Long twentyFour;

    @OneToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;


    // ========

    public void setTimeCount(int time, Long count) {

        switch (time) {
            case 0:
                twentyFour = count;
                break;
            case 1:
                one = count;
                break;
            case 2:
                two = count;
                break;
            case 3:
                three = count;
                break;
            case 4:
                four = count;
                break;
            case 5:
                five = count;
                break;
            case 6:
                six = count;
                break;
            case 7:
                seven = count;
                break;
            case 8:
                eight = count;
                break;
            case 9:
                nine = count;
                break;
            case 10:
                ten = count;
                break;
            case 11:
                eleven = count;
                break;
            case 12:
                twelve = count;
                break;
            case 13:
                thirteen = count;
                break;
            case 14:
                fourteen = count;
                break;
            case 15:
                fifteen = count;
                break;
            case 16:
                sixteen = count;
                break;
            case 17:
                seventeen = count;
                break;
            case 18:
                eighteen = count;
                break;
            case 19:
                nineteen = count;
                break;
            case 20:
                twenty = count;
                break;
            case 21:
                twentyOne = count;
                break;
            case 22:
                twentyTwo = count;
                break;
            case 23:
                twentyThree = count;
                break;
        }
    }
}
