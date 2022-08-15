package com.example.countdawnapi.repository;

import com.example.countdawnapi.model.CandidateNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateNoteRepository extends JpaRepository<CandidateNote,Integer> {
}
