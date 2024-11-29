package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Note;
import com.example.projetjeespringboot.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getNotesByEtudiant(String email) {
        return noteRepository.findByEtudiantEmail(email);
    }

    public Map<String, Double> calculerMoyenneParMatiere(List<Note> notes) {
        return notes.stream()
                .collect(Collectors.groupingBy(
                        note -> note.getMatiere().getNom(),
                        Collectors.averagingDouble(Note::getNote)
                ));
    }

    public double calculerMoyenneGenerale(List<Note> notes) {
        return notes.stream()
                .mapToDouble(Note::getNote)
                .average()
                .orElse(0);
    }
}
