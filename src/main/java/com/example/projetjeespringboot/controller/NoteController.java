package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Note;
import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.repository.NoteRepository;
import com.example.projetjeespringboot.repository.EtudiantRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @GetMapping("/voirNotes")
    public String voirNotes(
            HttpSession session,
            Model model) {
        String email = (String) session.getAttribute("email");
        Etudiant etudiant = etudiantRepository.findByEmail(email);

        if (etudiant == null) {
            return "redirect:/login";  // Rediriger vers la page de login si l'étudiant n'est pas trouvé
        }

        // Récupérer les notes de l'étudiant
        List<Note> notes = noteRepository.findByEtudiant(etudiant);

        // Organiser les notes par matière
        Map<String, List<Note>> notesParMatiere = notes.stream()
                .collect(Collectors.groupingBy(note -> note.getMatiere().getNom()));

        // Calcul des moyennes par matière
        Map<String, Double> moyennesParMatiere = notesParMatiere.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().stream()
                        .mapToDouble(Note::getNote)
                        .average().orElse(0)));

        // Calcul de la moyenne générale
        double moyenneGenerale = notes.stream().mapToDouble(Note::getNote).average().orElse(0);

        // Passer les données à la vue
        model.addAttribute("notes", notes);
        model.addAttribute("moyenneGenerale", moyenneGenerale);
        model.addAttribute("moyennesParMatiere", moyennesParMatiere);
        model.addAttribute("notesParMatiere", notesParMatiere);

        return "voirNotes";  // Vue Thymeleaf "voirNotes.html"
    }
}
