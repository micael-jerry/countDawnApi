package com.example.countdawnapi.Service;

import com.example.countdawnapi.model.Status;
import com.example.countdawnapi.repository.CandidateNoteRepository;
import com.example.countdawnapi.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatusService {

    private CandidateNoteRepository candidateNoteRepository;
    private StatusRepository statusRepository;

    public void createStatus(Status status) {
        statusRepository.save(status);
    }

    public Status getStatus() {
        try {
            return statusRepository.findById(1).get();
        } catch (Exception e) {
            Status status = new Status();
            statusRepository.save(status);
            return status;
        }
    }

    public Status updateStatus(Status status) {
        Status newStatus = update(statusRepository.findById(1).get(), status);
        statusRepository.save(newStatus);
        return newStatus;
    }

    public static Status update(Status oldStatus, Status status) {
        oldStatus.setAdmitted(status.getAdmitted());
        oldStatus.setPending(status.getPending());
        oldStatus.setRecaler(status.getRecaler());
        return oldStatus;
    }
}
