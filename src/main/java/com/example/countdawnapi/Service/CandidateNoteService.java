package com.example.countdawnapi.Service;


import com.example.countdawnapi.model.Candidate;
import com.example.countdawnapi.model.CandidateNote;
import com.example.countdawnapi.model.Note;
import com.example.countdawnapi.model.Status;
import com.example.countdawnapi.repository.CandidateNoteRepository;
import com.example.countdawnapi.repository.CandidateRepository;
import com.example.countdawnapi.repository.NoteRepository;
import com.example.countdawnapi.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CandidateNoteService {
    private CandidateNoteRepository candidateNoteRepository;
    private StatusRepository statusRepository;
    private StatusService statusService;
    private CandidateRepository candidateRepository;
    private NoteRepository noteRepository;

    public List<CandidateNote> getAllCandidateNote(int page, int size, String status) {
        if (statusRepository.count() < 1) {
            Status status1 = new Status();
            status1.setAdmitted(10);
            status1.setPending(8);
            status1.setRecaler(6);
            statusService.createStatus(status1);
            statusService.initialStatus(status1);
        }
        if (status != null) {
            return candidateNoteRepository.findAllByStatus(status);
        }
        return candidateNoteRepository.findAll(PageRequest.of(page, size)).toList();
    }

    public CandidateNote postCandidateNote(CandidateNote candidateNote) {
        Status status = statusRepository.findById(1).get();
        Candidate candidateRequestBody = candidateRepository.save(candidateNote.getCandidate());

        Note noteRequestBody = NoteService.averageStat(candidateNote.getNote());
        Note note = noteRepository.save(noteRequestBody);

        CandidateNote newCandidateNote = new CandidateNote();

        newCandidateNote.setCandidate(candidateRequestBody);
        newCandidateNote.setNote(note);


        newCandidateNote = updateStatus(
                newCandidateNote,
                status.getAdmitted(),
                status.getPending(),
                status.getRecaler()
        );
        candidateNoteRepository.save(newCandidateNote);
        return newCandidateNote;
    }

    public void deleteCandidateNoteById(int id){
        CandidateNote candidateNote = candidateNoteRepository.findById(id).get();
        candidateRepository.deleteById(candidateNote.getCandidate().getId());
        noteRepository.deleteById(candidateNote.getNote().getId());
        candidateNoteRepository.deleteById(id);
    }

    public static List<CandidateNote> updateStatus(List<CandidateNote> candidateNoteList, float admitted, float pending, float recaler) {
        List<CandidateNote> newList = candidateNoteList;
        for (CandidateNote candidateNote : newList) {
            if (candidateNote.getNote().getGeneralAvg() >= admitted) {
                candidateNote.setStatus("admitted");
            } else if (candidateNote.getNote().getGeneralAvg() <= admitted && candidateNote.getNote().getGeneralAvg() >= pending) {
                candidateNote.setStatus("pending");
            } else {
                candidateNote.setStatus("recaler");
            }
        }
        return newList;
    }

    public CandidateNote updateStatus(CandidateNote candidateNote, float admitted, float pending, float recaler) {
        if (candidateNote.getNote().getGeneralAvg() >= admitted) {
            candidateNote.setStatus("admitted");
        } else if (candidateNote.getNote().getGeneralAvg() <= admitted && candidateNote.getNote().getGeneralAvg() >= pending) {
            candidateNote.setStatus("pending");
        } else if (candidateNote.getNote().getGeneralAvg() < recaler) {
            candidateNote.setStatus("recaler");
        }
        return candidateNote;
    }
}
