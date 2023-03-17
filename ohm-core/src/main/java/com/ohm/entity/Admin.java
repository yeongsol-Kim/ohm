package com.ohm.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import com.ohm.entity.Manager.Manager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//코무무 계정
@Entity
@Getter
public class Admin {

    @Id
    @GeneratedValue
    @Column(name = "admin_id")
    private Long id;

    private String name;

    private String password;

    private String nickname;

    private String email;
}
