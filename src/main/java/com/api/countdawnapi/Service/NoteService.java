package com.api.countdawnapi.Service;

import com.api.countdawnapi.model.Note;

import java.util.List;

public class NoteService {
    public static Note moyenne(Note n) {
        Note note = n;
        note.setFrsAvg((note.getFrsContest() + note.getFrsTOB()) / 2);
        note.setMathAvg((note.getMathContest() + note.getMathTOB()) / 2);
        note.setGeneralAvg((note.getFrsAvg() + note.getMathAvg()) / 2);
        return note;
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

    public static float average(List<Note> noteList, Long count) {
        float countMoyenne = 0;
        for (Note note : noteList) {
            countMoyenne += note.getGeneralAvg();
        }
        return countMoyenne / count;
    }
}
