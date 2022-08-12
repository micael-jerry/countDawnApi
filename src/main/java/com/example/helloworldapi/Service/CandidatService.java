package com.example.helloworldapi.Service;

import com.example.helloworldapi.model.Candidate;

public class CandidatService {
    public static Candidate updateCandidat(Candidate oldCandidat, Candidate candidate){
        Candidate newCandidat = new Candidate();
        if(candidate.getName() != null){
            newCandidat.setName(candidate.getName());
        }
        if(candidate.getFirstName() != null){
            newCandidat.setFirstName(candidate.getFirstName());
        }
        if(candidate.getBachelor() != null){
            newCandidat.setBachelor(candidate.getBachelor());
        }
        return newCandidat;
    }
}
