package com.example.helloworldapi.repository;


import com.example.helloworldapi.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
