package com.example.countdawnapi.controller;

import com.example.countdawnapi.Service.StatusService;
import com.example.countdawnapi.model.Status;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/status")
public class StatusController {
    private StatusService statusService;

    @GetMapping(value = "")
    public Status status() {
        return statusService.getStatus();
    }

    @PutMapping(value = "")
    public Status updateStatus(@RequestBody(required = false) Status status) {
        return statusService.updateStatus(status);
    }
}
