package com.example.helloworldapi.Service;

import com.example.helloworldapi.model.Note;

import java.util.List;

public class CandidatNoteService {
    public static float average(List<Note> noteList,Long count){
        float countMoyenne = 0;
        for(Note note : noteList){
            countMoyenne+=note.getMoyenneGeneral();
        }
        return countMoyenne/count;
    }
}
