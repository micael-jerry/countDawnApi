package com.example.helloworldapi.controller;

import com.example.helloworldapi.model.Candidat;
import com.example.helloworldapi.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
