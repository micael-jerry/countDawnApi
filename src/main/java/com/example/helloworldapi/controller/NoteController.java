package com.example.helloworldapi.controller;

import com.example.helloworldapi.model.Note;
import com.example.helloworldapi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping(value = "")
    public List<Note> showAll() {
//        List<Note> noteList = noteRepository.findAll();
//        for (Note note : noteList) {
//            note.setMoyenneFrs((note.getFrsConcours() + note.getFrsTOB()) / 2);
//            note.setMoyenneMath((note.getMathConcours() + note.getMathTOB()) / 2);
//            note.setMoyenneGeneral((note.getMoyenneFrs()+note.getMoyenneMath())/2);
//        }
        return noteRepository.findAll();
    }

    @PostMapping(value = "")
    public Note createNote(@RequestBody Note note) {
        note.setMoyenneFrs((note.getFrsConcours() + note.getFrsTOB()) / 2);
        note.setMoyenneMath((note.getMathConcours() + note.getMathTOB()) / 2);
        note.setMoyenneGeneral((note.getMoyenneFrs() + note.getMoyenneMath()) / 2);
        noteRepository.save(note);
        return note;
    }

    @PutMapping(value = "/updateMoyenne")
    public List<Note> updateMoyenne() {
        List<Note> oldListNote = noteRepository.findAll();
        for (Note oldNote : oldListNote) {
            oldNote.setMoyenneFrs((oldNote.getFrsConcours() + oldNote.getFrsTOB()) / 2);
            oldNote.setMoyenneMath((oldNote.getMathConcours() + oldNote.getMathTOB()) / 2);
            oldNote.setMoyenneGeneral((oldNote.getMoyenneFrs() + oldNote.getMoyenneMath()) / 2);
            noteRepository.save(oldNote);
        }
        return noteRepository.findAll();
    }
}
