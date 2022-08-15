package com.example.countdawnapi.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "candidat_note")
public class CandidateNote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @OneToOne
    @JoinColumn(name = "candidat_id")
    private Candidate candidate;
    @OneToOne
    @JoinColumn(name = "note_id")
    private Note note;
    @Transient
    private String status;
}
