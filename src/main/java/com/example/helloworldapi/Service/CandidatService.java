package com.example.helloworldapi.Service;

import com.example.helloworldapi.model.Candidat;

public class CandidatService {
    public static Candidat updateCandidat(Candidat oldCandidat,Candidat candidat){
        Candidat newCandidat = new Candidat();
        if(candidat.getNom() != null){
            newCandidat.setNom(candidat.getNom());
        }
        if(candidat.getPrenom() != null){
            newCandidat.setPrenom(candidat.getPrenom());
        }
        if(candidat.getBachelier() != null){
            newCandidat.setBachelier(candidat.getBachelier());
        }
        return newCandidat;
    }
}
