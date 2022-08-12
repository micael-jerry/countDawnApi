package com.example.helloworldapi.controller;

import com.example.helloworldapi.Service.NoteService;
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
    public List<Note> showAll() {
        return noteRepository.findAll();
    }

    @PostMapping(value = "")
    public Note createNote(@RequestBody Note n) {
        Note note = NoteService.moyenne(n);
        noteRepository.save(note);
        return note;
    }

    @PutMapping(value = "/updateMoyenne")
    public List<Note> updateMoyenne() {
        List<Note> oldListNote = noteRepository.findAll();
        for (Note oldNote : oldListNote) {
            Note note = NoteService.moyenne(oldNote);
            noteRepository.save(note);
        }
        return noteRepository.findAll();
    }
}
