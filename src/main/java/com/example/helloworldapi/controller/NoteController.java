package com.example.helloworldapi.controller;

import com.example.helloworldapi.model.Note;
import com.example.helloworldapi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;
    @GetMapping(value = "")
    public List<Note> showAll(){
        return noteRepository.findAll();
    }

    @PostMapping(value = "")
    public Note createNote(@RequestBody Note note){
        noteRepository.save(note);
        return note;
    }
}
