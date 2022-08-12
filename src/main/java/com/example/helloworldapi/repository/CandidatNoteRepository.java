package com.example.helloworldapi.repository;

import com.example.helloworldapi.model.CandidatNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatNoteRepository extends JpaRepository<CandidatNote,Integer> {
}
