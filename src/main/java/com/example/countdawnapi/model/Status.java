package com.example.countdawnapi.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(nullable = false, updatable = false)
    private int id = 1;
    @Column(nullable = false)
    private float admitted;
    @Column(nullable = false)
    private float pending;
    @Column(nullable = false)
    private float recaler;

    public Status() {
        this.admitted = 10;
        this.pending = 8;
        this.recaler = 6;
    }
}
