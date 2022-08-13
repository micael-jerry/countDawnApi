package com.example.countdawnapi.controller;

import com.example.countdawnapi.Service.NoteService;
import com.example.countdawnapi.repository.NoteRepository;
import com.example.countdawnapi.model.Note;
import com.example.countdawnapi.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping(value = "")
    public List<Note> showAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return noteRepository.findAll(PageRequest.of(page, size)).toList();
    }

    @PostMapping(value = "")
    public Note createNote(@RequestBody Note n) {
        Note note = NoteService.moyenne(n);
        noteRepository.save(note);
        return note;
    }

    @PutMapping(value = "/updateAvg")
    public List<Note> updateAvg() {
        List<Note> oldListNote = noteRepository.findAll();
        for (Note oldNote : oldListNote) {
            Note note = NoteService.moyenne(oldNote);
            noteRepository.save(note);
        }
        return noteRepository.findAll();
    }

    @PutMapping(value = "/update/{id}")
    public Note updateNote(@PathVariable int id, @RequestBody Note note) {
        Note newNote = NoteService.updateNote(noteRepository.findById(id).get(), note);
        noteRepository.save(newNote);
        return newNote;
    }

    @GetMapping(value = "/stats")
    public Stats count() {
        Stats stats = new Stats();
        Long count = noteRepository.count();
        stats.setCount(count);
        stats.setAverage(NoteService.average(noteRepository.findAll(), count));
        stats.setPourcentage(NoteService.pourcentage(noteRepository.findAll(),count,10));
        return stats;
    }
}
