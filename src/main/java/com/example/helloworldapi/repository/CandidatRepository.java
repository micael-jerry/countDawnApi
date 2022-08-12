package com.example.helloworldapi.repository;


import com.example.helloworldapi.model.Candidat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
}
