package com.api.countdawnapi.Service;


import com.api.countdawnapi.model.CandidateNote;

import java.util.ArrayList;
import java.util.List;

public class CandidateNoteService {
    public List<CandidateNote> Admitted(List<CandidateNote> candidateNoteList, float minAvg){
        List<CandidateNote> admitted = new ArrayList<>();
        for(CandidateNote candidateNote : candidateNoteList){
            if(candidateNote.getNote().getGeneralAvg() >= minAvg){
                admitted.add(candidateNote);
            }
        }
        return admitted;
    }

    public static List<CandidateNote> updateStatus(List<CandidateNote> candidateNoteList,float admitted,float pending,float recaler){
        List<CandidateNote> newList = candidateNoteList;
        for(CandidateNote candidateNote : newList){
            if(candidateNote.getNote().getGeneralAvg() >= admitted){
                candidateNote.setStatus("admitted");
            } else if(candidateNote.getNote().getGeneralAvg() <= admitted && candidateNote.getNote().getGeneralAvg()>= recaler){
                candidateNote.setStatus("pending");
            } else {
                candidateNote.setStatus("recaler");
            }
        }
        return newList;
    }
}
