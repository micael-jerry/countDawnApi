package com.example.helloworldapi.repository;

import com.example.helloworldapi.model.CandidateNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateNoteRepository extends JpaRepository<CandidateNote,Integer> {
    List<CandidateNote> findAllByStatus(String status);
}
