package com.example.helloworldapi.controller;

import com.example.helloworldapi.model.Candidat;
import com.example.helloworldapi.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/candidats")
public class CandidatController {
    @Autowired
    private CandidatRepository candidatRepository;

    @GetMapping(value = "")
    public List<Candidat> showAll(){
        return candidatRepository.findAll();
    }

    @PostMapping(value = "")
    public Candidat createCandidat(@RequestBody Candidat candidat) {
        candidatRepository.save(candidat);
        return candidat;
    }
}
