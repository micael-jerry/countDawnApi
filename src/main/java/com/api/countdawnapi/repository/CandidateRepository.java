package com.api.countdawnapi.repository;


import com.api.countdawnapi.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
