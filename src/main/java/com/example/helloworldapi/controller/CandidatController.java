package com.example.helloworldapi.controller;

import com.example.helloworldapi.Service.CandidatService;
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
    public List<Candidat> showAll() {
        return candidatRepository.findAll();
    }

    @PostMapping(value = "")
    public Candidat createCandidat(@RequestBody Candidat candidat) {
        candidatRepository.save(candidat);
        return candidat;
    }

    @PutMapping(value = "/{id}")
    public Candidat putCandidat(
            @PathVariable int id,
            @RequestBody Candidat candidat
    ){
        Candidat oldCandidat = candidatRepository.findById(id).get();
        Candidat newCandidat = CandidatService.updateCandidat(oldCandidat,candidat);
        candidatRepository.save(newCandidat);
        return newCandidat;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCandidat(@PathVariable int id){
        candidatRepository.deleteById(id);
    }
}
