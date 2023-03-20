package com.ohm.entity.Gym;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "gym_price")
public class GymPrice {

    @Id
    @GeneratedValue
    @Column(name = "gym_price_id")
    private Long id;

    @Column(name = "during")
    private String during;

    @Column(name = "price")
    private String price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @Builder
    public GymPrice(String during,String price,Gym gym){
        this.during = during;
        this.price = price;
        this.gym = gym;
    }
}
