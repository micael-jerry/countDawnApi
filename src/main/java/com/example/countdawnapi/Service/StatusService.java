package com.example.countdawnapi.Service;

import com.example.countdawnapi.model.CandidateNote;
import com.example.countdawnapi.model.Status;
import com.example.countdawnapi.repository.CandidateNoteRepository;
import com.example.countdawnapi.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatusService {

    private CandidateNoteRepository candidateNoteRepository;
    private StatusRepository statusRepository;

    public void createStatus(Status status) {
        statusRepository.save(status);
    }

    public Status getStatus(){
        try {
            return statusRepository.findById(1).get();
        } catch (Exception e) {
            Status status = new Status();
            status.setAdmitted(10);
            status.setPending(8);
            status.setRecaler(6);
            statusRepository.save(status);
            return status;
        }
    }

    public Status updateAllStatus(Status status) {
        Status newStatus = statusRepository.findById(1).get();
        if (status != null) {
            newStatus = updateStatus(statusRepository.findById(1).get(), status);
            statusRepository.save(newStatus);
        }
        List<CandidateNote> candidateNoteList = candidateNoteRepository.findAll();
        List<CandidateNote> newCandidateNoteList = CandidateNoteService.updateStatus(
                candidateNoteList,
                newStatus.getAdmitted(),
                newStatus.getPending(),
                newStatus.getRecaler()
        );
        candidateNoteRepository.saveAll(newCandidateNoteList);
        return newStatus;
    }

    public void initialStatus(Status newStatus) {
        List<CandidateNote> candidateNoteList = candidateNoteRepository.findAll();
        List<CandidateNote> newCandidateNoteList = CandidateNoteService.updateStatus(
                candidateNoteList,
                newStatus.getAdmitted(),
                newStatus.getPending(),
                newStatus.getRecaler()
        );
        candidateNoteRepository.saveAll(newCandidateNoteList);
    }

    public Status updateAloneStatus(Status status) {
        Status newStatus = updateStatus(statusRepository.findById(1).get(), status);
        statusRepository.save(newStatus);
        return newStatus;
    }

    public static Status updateStatus(Status oldStatus, Status status) {
        oldStatus.setAdmitted(status.getAdmitted());
        oldStatus.setPending(status.getPending());
        oldStatus.setRecaler(status.getRecaler());
        return oldStatus;
    }
}
