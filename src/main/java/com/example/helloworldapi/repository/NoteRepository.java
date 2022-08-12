package com.example.helloworldapi.repository;

import com.example.helloworldapi.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note,Integer> {

}
