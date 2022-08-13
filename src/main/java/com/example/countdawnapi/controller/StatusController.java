package com.example.countdawnapi.controller;

import com.example.countdawnapi.model.Status;
import com.example.countdawnapi.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private StatusRepository statusRepository;
    @GetMapping(value = "")
    public List<Status> getStatus(){
        return statusRepository.findAll();
    }
}
