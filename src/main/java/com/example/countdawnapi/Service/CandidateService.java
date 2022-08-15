package com.example.countdawnapi.Service;

import com.example.countdawnapi.model.Candidate;
import com.example.countdawnapi.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public List<Candidate> getAllCandidate(int page, int size) {
        return candidateRepository.findAll(PageRequest.of(page, size)).toList();
    }

    public Candidate postCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
        return candidate;
    }

    public Candidate getCandidateById(int id) {
        return candidateRepository.findById(id).get();
    }

    public Candidate putCandidateById(int id, Candidate candidate) {
        Candidate oldCandidate = candidateRepository.findById(id).get();
        Candidate newCandidate = updateCandidate(oldCandidate, candidate);
        candidateRepository.save(newCandidate);
        return newCandidate;
    }

    public void deleteCandidateById(int id) {
        candidateRepository.deleteById(id);
    }

    public static Candidate updateCandidate(Candidate oldCandidate, Candidate candidate) {
        if (candidate.getName() != null) {
            oldCandidate.setName(candidate.getName());
        }
        if (candidate.getFirstName() != null) {
            oldCandidate.setFirstName(candidate.getFirstName());
        }
        if (candidate.getBachelor() != null) {
            oldCandidate.setBachelor(candidate.getBachelor());
        }
        return oldCandidate;
    }
}
