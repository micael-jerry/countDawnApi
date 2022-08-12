package com.example.helloworldapi.controller;

import com.example.helloworldapi.Service.CandidatService;
import com.example.helloworldapi.model.Candidate;
import com.example.helloworldapi.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/candidates")
public class CandidatController {
    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping(value = "")
    public List<Candidate> showAll(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        return candidateRepository.findAll(PageRequest.of(page, size)).toList();
    }

    @PostMapping(value = "")
    public Candidate createCandidat(@RequestBody Candidate candidate) {
        candidateRepository.save(candidate);
        return candidate;
    }

    @PutMapping(value = "/{id}")
    public Candidate putCandidat(
            @PathVariable int id,
            @RequestBody Candidate candidate
    ){
        Candidate oldCandidat = candidateRepository.findById(id).get();
        Candidate newCandidat = CandidatService.updateCandidat(oldCandidat,candidate);
        candidateRepository.save(newCandidat);
        return newCandidat;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCandidat(@PathVariable int id){
        candidateRepository.deleteById(id);
    }
}
