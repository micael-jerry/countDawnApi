package com.example.countdawnapi.controller;

import com.example.countdawnapi.Service.StatusService;
import com.example.countdawnapi.model.Status;
import com.example.countdawnapi.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {
    @Autowired
    private StatusRepository statusRepository;
    @GetMapping(value = "")
    public Status getStatus(){
        return statusRepository.findById(1).get();
    }

    @PutMapping(value = "")
    public Status updateStatus(@RequestBody(required = false) Status status) {
        Status newStatus = StatusService.updateStatus(statusRepository.findById(1).get(), status);
        statusRepository.save(newStatus);
        return newStatus;
    }
}
