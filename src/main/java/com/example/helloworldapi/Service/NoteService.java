package com.example.helloworldapi.Service;

import com.example.helloworldapi.model.Note;

public class NoteService {
    public static Note moyenne(Note n) {
        Note note = n;
        note.setMoyenneFrs((note.getFrsConcours() + note.getFrsTOB()) / 2);
        note.setMoyenneMath((note.getMathConcours() + note.getMathTOB()) / 2);
        note.setMoyenneGeneral((note.getMoyenneFrs() + note.getMoyenneMath()) / 2);
        return note;
    }
}
