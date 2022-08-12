package com.example.helloworldapi.controller;

import com.example.helloworldapi.model.Candidat;
import com.example.helloworldapi.model.CandidatNote;
import com.example.helloworldapi.model.Note;
import com.example.helloworldapi.repository.CandidatNoteRepository;
import com.example.helloworldapi.repository.CandidatRepository;
import com.example.helloworldapi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CandidatNote> showAll() {
        return candidatNoteRepository.findAll();
    }

    @PostMapping(value = "")
    public CandidatNote createCandidatNote(@RequestBody CandidatNote candidatNote) {
        Candidat candidat = candidatRepository.save(candidatNote.getCandidat());
        Note note = noteRepository.save(candidatNote.getNote());
        CandidatNote newCandidatNote = new CandidatNote();
        newCandidatNote.setCandidat(candidat);
        newCandidatNote.setNote(note);
        candidatNoteRepository.save(candidatNote);
        return candidatNote;
    }

}
