package com.example.countdawnapi.Service;

import com.example.countdawnapi.model.Note;
import com.example.countdawnapi.model.Stats;
import com.example.countdawnapi.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {
    private NoteRepository noteRepository;

    public List<Note> getALlNote(int page, int size) {
        return noteRepository.findAll(PageRequest.of(page, size)).toList();
    }

    public Note postNote(Note note) {
        Note n = averageStat(note);
        noteRepository.save(n);
        return n;
    }

    public List<Note> updateAvg() {
        List<Note> oldListNote = noteRepository.findAll();
        for (Note oldNote : oldListNote) {
            Note note = averageStat(oldNote);
            noteRepository.save(note);
        }
        return noteRepository.findAll();
    }

    public static Note averageStat(Note n) {
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
        Long count = noteRepository.count();
        stats.setCount(count);
        stats.setAverage(averageStat(noteRepository.findAll(), count));
        stats.setPourcentage(pourcentage(noteRepository.findAll(), count, 10));
        return stats;
    }

    public static Note updateNote(Note oldNote, Note note) {
        Note newNote = oldNote;
        if (note.getFrsContest() != -1) {
            newNote.setFrsContest(note.getFrsContest());
            newNote.setFrsAvg((note.getFrsContest() + note.getFrsTOB()) / 2);
        }
        if (note.getMathContest() != -1) {
            newNote.setMathContest(note.getMathContest());
            newNote.setMathAvg((note.getMathContest() + note.getMathTOB()) / 2);
        }
        if (note.getFrsTOB() != -1) {
            newNote.setFrsTOB(note.getFrsTOB());
            newNote.setFrsAvg((note.getFrsContest() + note.getFrsTOB()) / 2);
        }
        if (note.getMathTOB() != -1) {
            newNote.setMathTOB(note.getMathTOB());
            newNote.setMathAvg((note.getMathContest() + note.getMathTOB()) / 2);
        }
        newNote.setGeneralAvg((note.getFrsAvg() + note.getMathAvg()) / 2);
        return newNote;
    }

    public static float averageStat(List<Note> noteList, Long count) {
        float countMoyenne = 0;
        for (Note note : noteList) {
            countMoyenne += note.getGeneralAvg();
        }
        return countMoyenne / count;
    }

    public static float pourcentage(List<Note> noteList, Long count, float minAvg) {
        float countMoyenne = 0;
        for (Note note : noteList) {
            if (note.getGeneralAvg() >= minAvg) {
                countMoyenne += 1;
            }
        }
        return (countMoyenne / count) * 100;
    }
}
