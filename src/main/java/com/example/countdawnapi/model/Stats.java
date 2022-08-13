package com.example.countdawnapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Stats {
    private Long count;
    private float average;
    private float pourcentage;
}
