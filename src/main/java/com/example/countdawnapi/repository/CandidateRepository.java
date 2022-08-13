package com.example.countdawnapi.repository;


import com.example.countdawnapi.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
