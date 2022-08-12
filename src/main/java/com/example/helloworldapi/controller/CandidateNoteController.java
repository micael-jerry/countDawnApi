package com.example.helloworldapi.controller;

import com.example.helloworldapi.Service.CandidateNoteService;
import com.example.helloworldapi.Service.NoteService;
import com.example.helloworldapi.model.Candidate;
import com.example.helloworldapi.model.CandidateNote;
import com.example.helloworldapi.model.Note;
import com.example.helloworldapi.model.Status;
import com.example.helloworldapi.repository.CandidateNoteRepository;
import com.example.helloworldapi.repository.CandidateRepository;
import com.example.helloworldapi.repository.NoteRepository;
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
            @RequestParam("size") int size
    ) {
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
    public Status updateStatus(@RequestBody Status status){
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
