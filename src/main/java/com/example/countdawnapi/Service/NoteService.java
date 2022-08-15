package com.example.countdawnapi.Service;

import com.example.countdawnapi.model.Note;
import com.example.countdawnapi.model.Stats;
import com.example.countdawnapi.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {
    private NoteRepository noteRepository;
    private StatusService statusService;

    public List<Note> getALlNote(int page, int size) {
        List<Note> noteList = noteRepository.findAll(PageRequest.of(page, size)).toList();
        List<Note> noteListReturn = new ArrayList<>();
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            noteListReturn.add(average(note));
        }
        return noteListReturn;
    }

    public Note postNote(Note note) {
        noteRepository.save(note);
        return average(note);
    }

    public static Note average(Note n) {
        Note note = n;
        note.setFrsAvg((note.getFrsContest() + note.getFrsTOB()) / 2);
        note.setMathAvg((note.getMathContest() + note.getMathTOB()) / 2);
        note.setGeneralAvg((note.getFrsAvg() + note.getMathAvg()) / 2);
        return note;
    }

    public Note putNote(int id, Note note) {
        Note newNote = updateNote(noteRepository.findById(id).get(), note);
        noteRepository.save(newNote);
        return newNote;
    }

    public Stats statistic() {
        Stats stats = new Stats();
        List<Note> noteList = noteRepository.findAll();
        noteList.replaceAll(NoteService::average);
        Long count = (long) noteList.size();
        stats.setCount(count);
        stats.setAverage(averageStat(noteList, count));
        stats.setPourcentage(pourcentage(noteList, count, statusService.getStatus().getAdmitted()));
        return stats;
    }

    public static Note updateNote(Note oldNote, Note note) {
        Note newNote = oldNote;
        if (note.getFrsContest() != -1) {
            newNote.setFrsContest(note.getFrsContest());
        }
        if (note.getMathContest() != -1) {
            newNote.setMathContest(note.getMathContest());
        }
        if (note.getFrsTOB() != -1) {
            newNote.setFrsTOB(note.getFrsTOB());
        }
        if (note.getMathTOB() != -1) {
            newNote.setMathTOB(note.getMathTOB());
        }
        return newNote;
    }

    public static float averageStat(List<Note> noteList, Long count) {
        float sumAvg = 0;
        for (Note note : noteList) {
            sumAvg += note.getGeneralAvg();
        }
        return sumAvg / count;
    }

    public static float pourcentage(List<Note> noteList, Long count, float minAvg) {
        float countAvg = 0;
        for (Note note : noteList) {
            if (note.getGeneralAvg() >= minAvg) {
                countAvg += 1;
            }
        }
        return (countAvg / count) * 100;
    }
}
