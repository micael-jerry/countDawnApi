package com.example.countdawnapi.repository;

import com.example.countdawnapi.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Integer> {

}
