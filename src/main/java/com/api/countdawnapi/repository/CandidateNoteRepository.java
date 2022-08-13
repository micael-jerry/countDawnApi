package com.api.countdawnapi.repository;

import com.api.countdawnapi.model.CandidateNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateNoteRepository extends JpaRepository<CandidateNote,Integer> {
    List<CandidateNote> findAllByStatus(String status);
}
