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

    // Méthode principale pour ajouter une note et envoyer une notification
    public void ajouterNote(String emailProf, String nomMatiere, String emailEtudiant, float noteValue) {
        // Vérification si la matière existe
        Matiere matiereOpt = matiereRepository.findByNom(nomMatiere);
        if (matiereOpt.isEmpty()) {
            throw new IllegalArgumentException("La matière '" + nomMatiere + "' n'existe pas.");
        }
        Matiere matiere = matiereOpt.get();

        // Vérification si l'étudiant existe
        Etudiant etudiantOpt = etudiantRepository.findByEmail(emailEtudiant);
        if (etudiantOpt.isEmpty()) {
            throw new IllegalArgumentException("L'étudiant avec l'email '" + emailEtudiant + "' n'existe pas.");
        }
        Etudiant etudiant = etudiantOpt.get();

        // Création d'une nouvelle note
        Note note = new Note();
        note.setNote(noteValue);
        note.setEtudiant(etudiant);
        note.setMatiere(matiere);
        note.setDate(LocalDateTime.now().toLocalDate()); // Ajouter la date actuelle
        noteRepository.save(note);

        // Envoi de la notification
        envoyerNotification(emailProf, emailEtudiant, nomMatiere, noteValue);
    }

    // Méthode privée pour envoyer une notification
    private void envoyerNotification(String emailProf, String emailEtudiant, String nomMatiere, float noteValue) {
        // Création du message de notification
        Message message = new Message();
        message.setSender(emailProf);
        message.setRecipient(emailEtudiant);
        message.setSubject("Nouvelle Note : " + nomMatiere);
        message.setContent("Bonjour,\n\nVous avez reçu une nouvelle note pour la matière " +
                nomMatiere + " : " + noteValue + "/20.\n\nCordialement,\nL'équipe pédagogique.");
        message.setSentAt(LocalDateTime.now());

        // Sauvegarde du message dans la base de données
        messageRepository.save(message);
    }
}
