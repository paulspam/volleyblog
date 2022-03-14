package com.softserveinc.ita.javaclub.volleyblog.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "city")
    private String city;

    @Column(name = "team_rating")
    private Integer teamRating;
}
