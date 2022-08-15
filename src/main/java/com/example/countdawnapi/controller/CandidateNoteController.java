package com.example.countdawnapi.controller;

import com.example.countdawnapi.Service.CandidateNoteService;
import com.example.countdawnapi.model.CandidateNote;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/candidates-notes")
public class CandidateNoteController {
    private CandidateNoteService candidateNoteService;

    @GetMapping(value = "")
    public List<CandidateNote> showAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam(name = "status", required = false) String status
    ) {
        return candidateNoteService.getAllCandidateNote(page, size, status);
    }

    @PostMapping(value = "")
    public CandidateNote createCandidateNote(@RequestBody CandidateNote candidateNote) {
        return candidateNoteService.postCandidateNote(candidateNote);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteCandidateNote(@PathVariable int id) {
        candidateNoteService.deleteCandidateNoteById(id);
    }

}
