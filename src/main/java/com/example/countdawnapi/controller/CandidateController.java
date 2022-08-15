package com.example.countdawnapi.controller;

import com.example.countdawnapi.Service.CandidateService;
import com.example.countdawnapi.model.Candidate;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/candidates")
public class CandidateController {
    private CandidateService candidateService;

    @GetMapping(value = "")
    public List<Candidate> showAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return candidateService.getAllCandidate(page, size);
    }

    @PostMapping(value = "")
    public Candidate createCandidate(@RequestBody Candidate candidate) {
        return candidateService.postCandidate(candidate);
    }

    @GetMapping(value = "/{id}")
    public Candidate getCandidate(@PathVariable int id) {
        return candidateService.getCandidateById(id);
    }

    @PutMapping(value = "/{id}")
    public Candidate putCandidate(
            @PathVariable int id,
            @RequestBody Candidate candidate
    ) {
        return candidateService.putCandidateById(id, candidate);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCandidate(@PathVariable int id) {
        candidateService.deleteCandidateById(id);
    }
}
