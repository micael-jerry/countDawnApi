package com.example.helloworldapi.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "math_contest")
    private float mathContest;
    @Column(name = "frs_contest")
    private float frsContest;
    @Column(name = "math_tob")
    private float mathTOB;
    @Column(name = "frs_tob")
    private float frsTOB;
    @Column(name = "math_avg")
    private float mathAvg;
    @Column(name = "frs_avg")
    private float frsAvg;
    @Column(name = "general_avg")
    private float generalAvg;
}
