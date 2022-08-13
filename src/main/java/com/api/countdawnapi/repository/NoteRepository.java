package com.api.countdawnapi.repository;

import com.api.countdawnapi.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Integer> {

}
