package com.ohm.entity.Ceo;


import com.ohm.dto.ManagerDto.ManagerDto;
import com.ohm.entity.Gym.Gym;
import com.ohm.entity.embedded.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ceo")
public class Ceo extends BaseTime {


    @Id
    @GeneratedValue()
    @Column(name = "ceo_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    //실제이름
    @Column(name = "nickname")
    private String nickname;

    @OneToMany(mappedBy = "ceo",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Gym> gyms = new ArrayList<>();


    // == 비즈니스 로직 ==


    public void update(ManagerDto manager) {
        this.username = manager.getName();
        this.nickname = manager.getNickname();
    }



}
