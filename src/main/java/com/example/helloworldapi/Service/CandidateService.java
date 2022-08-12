package com.example.helloworldapi.Service;

import com.example.helloworldapi.model.Candidate;

public class CandidateService {
    public static Candidate updateCandidate(Candidate oldCandidate, Candidate candidate){
        Candidate newCandidate = new Candidate();
        if(candidate.getName() != null){
            newCandidate.setName(candidate.getName());
        }
        if(candidate.getFirstName() != null){
            newCandidate.setFirstName(candidate.getFirstName());
        }
        if(candidate.getBachelor() != null){
            newCandidate.setBachelor(candidate.getBachelor());
        }
        return newCandidate;
    }
}
