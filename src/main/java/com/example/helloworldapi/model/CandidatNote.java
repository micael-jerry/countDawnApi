package com.example.helloworldapi.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "candidat_note")
public class CandidatNote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @OneToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;
    @OneToOne
    @JoinColumn(name = "note_id")
    private Note note;
}
