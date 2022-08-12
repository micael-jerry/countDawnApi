package com.example.helloworldapi.controller;

import com.example.helloworldapi.model.CandidatNote;
import com.example.helloworldapi.repository.CandidatNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/candidats-notes")
public class CandidatNoteController {
    @Autowired
    private CandidatNoteRepository candidatNoteRepository;

    @GetMapping(value = "")
    public List<CandidatNote> showAll() {
        return candidatNoteRepository.findAll();
    }

    @PostMapping(value = "")
    public CandidatNote createCandidatNote(@RequestBody CandidatNote candidatNote) {
        candidatNoteRepository.save(candidatNote);
        return candidatNote;
    }

}
