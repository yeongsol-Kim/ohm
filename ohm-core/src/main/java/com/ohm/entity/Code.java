package com.ohm.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "code")
public class Code {

    @Id
    @GeneratedValue
    @Column(name = "code_id")
    private Long id;

    private String code;
}