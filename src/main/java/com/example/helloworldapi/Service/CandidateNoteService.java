package com.example.helloworldapi.Service;


import com.example.helloworldapi.model.CandidateNote;

import java.util.ArrayList;
import java.util.List;

public class CandidateNoteService {
    public List<CandidateNote> Admitted(List<CandidateNote> candidateNoteList,float minAvg){
        List<CandidateNote> admitted = new ArrayList<>();
        for(CandidateNote candidateNote : candidateNoteList){
            if(candidateNote.getNote().getGeneralAvg() >= minAvg){
                admitted.add(candidateNote);
            }
        }
        return admitted;
    }
}
