package com.example.helloworldapi.Service;

import com.example.helloworldapi.model.Note;

public class NoteService {
    public static Note moyenne(Note n) {
        Note note = n;
        note.setFrsAvg((note.getFrsContest() + note.getFrsTOB()) / 2);
        note.setMathAvg((note.getMathContest() + note.getMathTOB()) / 2);
        note.setGeneralAvg((note.getFrsAvg() + note.getMathAvg()) / 2);
        return note;
    }
}
