package com.api.countdawnapi.controller;

import com.api.countdawnapi.Service.CandidateNoteService;
import com.api.countdawnapi.Service.NoteService;
import com.api.countdawnapi.model.Candidate;
import com.api.countdawnapi.model.CandidateNote;
import com.api.countdawnapi.model.Status;
import com.api.countdawnapi.repository.CandidateNoteRepository;
import com.api.countdawnapi.repository.CandidateRepository;
import com.api.countdawnapi.repository.NoteRepository;
import com.api.countdawnapi.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/candidates-notes")
public class CandidateNoteController {
    @Autowired
    private CandidateNoteRepository candidateNoteRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping(value = "")
    public List<CandidateNote> showAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "status", required = false) String status
    ) {
        if (status != null) {
            return candidateNoteRepository.findAllByStatus(status);
        }
        return candidateNoteRepository.findAll(PageRequest.of(page, size)).toList();
    }

    @PostMapping(value = "")
    public CandidateNote createCandidateNote(@RequestBody CandidateNote candidateNote) {
        Candidate candidateRequestBody = candidateRepository.save(candidateNote.getCandidate());
        Note noteRequestBody = NoteService.moyenne(candidateNote.getNote());
        Note note = noteRepository.save(noteRequestBody);
        CandidateNote newCandidateNote = new CandidateNote();
        newCandidateNote.setCandidate(candidateRequestBody);
        newCandidateNote.setNote(note);
        candidateNoteRepository.save(candidateNote);
        return candidateNote;
    }

    @PutMapping(value = "/status")
    public Status updateStatus(@RequestBody Status status) {
        List<CandidateNote> candidateNoteList = candidateNoteRepository.findAll();
        List<CandidateNote> newCandidateNoteList = CandidateNoteService.updateStatus(
                candidateNoteList,
                status.getAdmitted(),
                status.getPending(),
                status.getRecaler()
        );
        candidateNoteRepository.saveAll(newCandidateNoteList);
        return status;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteCandidateNote(@PathVariable int id) {
        CandidateNote candidateNote = candidateNoteRepository.findById(id).get();
        candidateRepository.deleteById(candidateNote.getCandidate().getId());
        noteRepository.deleteById(candidateNote.getNote().getId());
        candidateNoteRepository.deleteById(id);
    }

}
