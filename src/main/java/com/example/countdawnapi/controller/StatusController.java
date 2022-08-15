package com.example.countdawnapi.controller;

import com.example.countdawnapi.Service.CandidateNoteService;
import com.example.countdawnapi.Service.StatusService;
import com.example.countdawnapi.model.CandidateNote;
import com.example.countdawnapi.model.Status;
import com.example.countdawnapi.repository.CandidateNoteRepository;
import com.example.countdawnapi.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/status")
public class StatusController {
    private StatusRepository statusRepository;
    private StatusService statusService;

    @GetMapping(value = "")
    public Status getStatus() {
        return statusRepository.findById(1).get();
    }

    @PutMapping(value = "")
    public Status updateStatus(@RequestBody(required = false) Status status) {
        return statusService.updateAloneStatus(status);
    }

    @PutMapping(value = "/all")
    public Status updateAllStatus(@RequestBody(required = false) Status status) {
        return statusService.updateAllStatus(status);
    }
}
