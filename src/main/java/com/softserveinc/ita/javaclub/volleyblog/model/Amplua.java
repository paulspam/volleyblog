package com.softserveinc.ita.javaclub.volleyblog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "amplua")
public class Amplua {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amplua_id")
    private Integer ampluaId;

    @Column(name = "amplua_name")
    private String ampluaName;
}
