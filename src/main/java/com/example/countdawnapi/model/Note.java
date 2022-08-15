package com.example.countdawnapi.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
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
    @Transient
    private float mathAvg;
    @Transient
    private float frsAvg;
    @Transient
    private float generalAvg;

    public Note() {
        this.mathContest = -1;
        this.frsContest = -1;
        this.mathTOB = -1;
        this.frsTOB = -1;
    }
}
