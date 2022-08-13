package com.api.countdawnapi.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "candidats")
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(length = 150,name = "first_name")
    private String firstName;
    @Column(nullable = false)
    private Boolean bachelor;
}
