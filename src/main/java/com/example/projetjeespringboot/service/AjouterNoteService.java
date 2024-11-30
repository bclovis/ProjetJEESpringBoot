package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.model.Matiere;
import com.example.projetjeespringboot.model.Message;
import com.example.projetjeespringboot.model.Note;
import com.example.projetjeespringboot.repository.EtudiantRepository;
import com.example.projetjeespringboot.repository.MatiereRepository;
import com.example.projetjeespringboot.repository.MessageRepository;
import com.example.projetjeespringboot.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AjouterNoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private MessageRepository messageRepository;

    public void ajouterNote(String emailProf, String nomMatiere, String emailEtudiant, float noteValue) throws Exception {
        // Vérifier si la matière existe
        Matiere matiereOpt = matiereRepository.findByNom(nomMatiere);
        if (matiereOpt.isEmpty()) {
            throw new Exception("La matière '" + nomMatiere + "' est introuvable.");
        }
        Matiere matiere = matiereOpt.get();

        // Vérifier si l'étudiant existe
        Etudiant etudiantOpt = etudiantRepository.findByEmail(emailEtudiant);
        if (etudiantOpt.isEmpty()) {
            throw new Exception("L'étudiant avec l'email '" + emailEtudiant + "' est introuvable.");
        }
        Etudiant etudiant = etudiantOpt.get();

        // Créer une nouvelle note
        Note nouvelleNote = new Note(noteValue, etudiant, matiere);
        noteRepository.save(nouvelleNote);

        // Créer et enregistrer une notification
        String contenuMessage = String.format("Vous avez reçu une nouvelle note : %.2f en %s.", noteValue, nomMatiere);
        Message message = new Message(emailProf, emailEtudiant, "Nouvelle Note", contenuMessage, LocalDateTime.now());
        messageRepository.save(message);
    }
}
