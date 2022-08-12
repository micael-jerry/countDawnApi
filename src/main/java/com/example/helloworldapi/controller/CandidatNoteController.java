package com.example.helloworldapi.controller;

import com.example.helloworldapi.Service.NoteService;
import com.example.helloworldapi.model.Candidat;
import com.example.helloworldapi.model.CandidatNote;
import com.example.helloworldapi.model.Note;
import com.example.helloworldapi.repository.CandidatNoteRepository;
import com.example.helloworldapi.repository.CandidatRepository;
import com.example.helloworldapi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/candidats-notes")
public class CandidatNoteController {
    @Autowired
    private CandidatNoteRepository candidatNoteRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping(value = "")
    public List<CandidatNote> showAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return candidatNoteRepository.findAll(PageRequest.of(page, size)).toList();
    }

    @PostMapping(value = "")
    public CandidatNote createCandidatNote(@RequestBody CandidatNote candidatNote) {
        Candidat candidat = candidatRepository.save(candidatNote.getCandidat());
        Note noteRequestBody = NoteService.moyenne(candidatNote.getNote());
        Note note = noteRepository.save(noteRequestBody);
        CandidatNote newCandidatNote = new CandidatNote();
        newCandidatNote.setCandidat(candidat);
        newCandidatNote.setNote(note);
        candidatNoteRepository.save(candidatNote);
        return candidatNote;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCandidatNote(@PathVariable int id) {
        candidatNoteRepository.deleteById(id);
    }

}
