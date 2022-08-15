package com.example.countdawnapi.controller;

import com.example.countdawnapi.Service.NoteService;
import com.example.countdawnapi.repository.NoteRepository;
import com.example.countdawnapi.model.Note;
import com.example.countdawnapi.model.Stats;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;
    private NoteService noteService;

    @GetMapping(value = "")
    public List<Note> showAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return noteService.getALlNote(page, size);
    }

    @PostMapping(value = "")
    public Note createNote(@RequestBody Note n) {
        return noteService.postNote(n);
    }

    @PutMapping(value = "/updateAvg")
    public List<Note> updateAvg() {
        return noteService.updateAvg();
    }

    @PutMapping(value = "/update/{id}")
    public Note updateNote(@PathVariable int id, @RequestBody Note note) {
        return noteService.putNote(id, note);
    }

    @GetMapping(value = "/stats")
    public Stats stats() {
        return noteService.statistic();
    }
}
