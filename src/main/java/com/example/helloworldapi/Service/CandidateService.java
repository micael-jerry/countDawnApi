package com.example.helloworldapi.Service;

import com.example.helloworldapi.model.Candidate;

public class CandidateService {
    public static Candidate updateCandidate(Candidate oldCandidate, Candidate candidate){
        if(candidate.getName() != null){
            oldCandidate.setName(candidate.getName());
        }
        if(candidate.getFirstName() != null){
            oldCandidate.setFirstName(candidate.getFirstName());
        }
        if(candidate.getBachelor() != null){
            oldCandidate.setBachelor(candidate.getBachelor());
        }
        return oldCandidate;
    }
}
