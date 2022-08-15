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

    public List<CandidateNote> getAllCandidateNote(int page, int size) {
        if (statusRepository.count() < 1) {
            Status status1 = new Status();
            statusService.createStatus(status1);
        }
        return updateStatus(
                averageInCandidateNote(candidateNoteRepository.findAll(PageRequest.of(page, size)).toList()),
                statusService.getStatus().getAdmitted(),
                statusService.getStatus().getPending(),
                statusService.getStatus().getRecaler()
        );
    }

    public CandidateNote postCandidateNote(CandidateNote candidateNote) {
        Candidate candidateRequestBody = candidateRepository.save(candidateNote.getCandidate());
        Note noteRequestBody = candidateNote.getNote();
        Note note = noteRepository.save(noteRequestBody);
        CandidateNote newCandidateNote = new CandidateNote();
        newCandidateNote.setCandidate(candidateRequestBody);
        newCandidateNote.setNote(note);
        candidateNoteRepository.save(newCandidateNote);
        return updateStatus(
                averageInCandidateNote(newCandidateNote),
                statusService.getStatus().getAdmitted(),
                statusService.getStatus().getPending(),
                statusService.getStatus().getRecaler()
        );
    }

    public void deleteCandidateNoteById(int id) {
        CandidateNote candidateNote = candidateNoteRepository.findById(id).get();
        candidateRepository.deleteById(candidateNote.getCandidate().getId());
        noteRepository.deleteById(candidateNote.getNote().getId());
        candidateNoteRepository.deleteById(id);
    }

    public List<CandidateNote> averageInCandidateNote(List<CandidateNote> candidateNoteList) {
        List<CandidateNote> candidateNoteList1 = new ArrayList<>();
        for (CandidateNote candidateNote : candidateNoteList) {
            Note note = NoteService.average(candidateNote.getNote());
            candidateNote.setNote(note);
            candidateNoteList1.add(candidateNote);
        }
        return candidateNoteList1;
    }

    public CandidateNote averageInCandidateNote(CandidateNote candidateNote) {
        Note note = NoteService.average(candidateNote.getNote());
        candidateNote.setNote(note);
        return candidateNote;
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

    public static CandidateNote updateStatus(CandidateNote candidateNote, float admitted, float pending, float recaler) {
        if (candidateNote.getNote().getGeneralAvg() >= admitted) {
            candidateNote.setStatus("admitted");
        } else if (candidateNote.getNote().getGeneralAvg() <= admitted && candidateNote.getNote().getGeneralAvg() >= pending) {
            candidateNote.setStatus("pending");
        } else {
            candidateNote.setStatus("recaler");
        }
        return candidateNote;
    }
}
