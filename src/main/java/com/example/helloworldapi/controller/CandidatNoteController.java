package com.example.helloworldapi.controller;

import com.example.helloworldapi.Service.NoteService;
import com.example.helloworldapi.model.Candidate;
import com.example.helloworldapi.model.CandidateNote;
import com.example.helloworldapi.model.Note;
import com.example.helloworldapi.repository.CandidateNoteRepository;
import com.example.helloworldapi.repository.CandidateRepository;
import com.example.helloworldapi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/candidates-notes")
public class CandidatNoteController {
    @Autowired
    private CandidateNoteRepository candidateNoteRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping(value = "")
    public List<CandidateNote> showAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return candidateNoteRepository.findAll(PageRequest.of(page, size)).toList();
    }

    @PostMapping(value = "")
    public CandidateNote createCandidatNote(@RequestBody CandidateNote candidateNote) {
        Candidate candidate = candidateRepository.save(candidateNote.getCandidate());
        Note noteRequestBody = NoteService.moyenne(candidateNote.getNote());
        Note note = noteRepository.save(noteRequestBody);
        CandidateNote newCandidatNote = new CandidateNote();
        newCandidatNote.setCandidate(candidate);
        newCandidatNote.setNote(note);
        candidateNoteRepository.save(candidateNote);
        return candidateNote;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCandidatNote(@PathVariable int id) {
        candidateNoteRepository.deleteById(id);
    }

}
