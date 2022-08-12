package com.example.helloworldapi.repository;

import com.example.helloworldapi.model.CandidateNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateNoteRepository extends JpaRepository<CandidateNote,Integer> {
}
