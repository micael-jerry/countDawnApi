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
    @Column(name = "math_concours")
    private float mathConcours;
    @Column(name = "frs_concours")
    private float frsConcours;
    @Column(name = "math_tob")
    private float mathTOB;
    @Column(name = "frs_tob")
    private float frsTOB;
}
