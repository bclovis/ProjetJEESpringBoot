package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Note;
import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.repository.NoteRepository;
import com.example.projetjeespringboot.repository.EtudiantRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.projetjeespringboot.controller.ReleveNotesGenerator.genererReleve;

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
        moyenneGenerale = Math.round(moyenneGenerale * 100.0) / 100.0;
        String rapport = genererRapport(moyenneGenerale);

        // Passer les données à la vue
        model.addAttribute("notes", notes);
        model.addAttribute("moyenneGenerale", moyenneGenerale);
        model.addAttribute("moyennesParMatiere", moyennesParMatiere);
        model.addAttribute("notesParMatiere", notesParMatiere);
        model.addAttribute("rapport", rapport);

        return "voirNotes";  // Vue Thymeleaf "voirNotes.html"
    }

    // Nouvelle méthode pour télécharger le relevé de notes en PDF
    @GetMapping("/downloadReleve")
    public ResponseEntity<byte[]> downloadReleve(HttpSession session, HttpServletResponse response) {
        String email = (String) session.getAttribute("email");
        Etudiant etudiant = etudiantRepository.findByEmail(email);

        if (etudiant == null) {
            return ResponseEntity.status(404).build(); // Étudiant non trouvé
        }

        // Récupérer les notes de l'étudiant
        List<Note> notes = noteRepository.findByEtudiant(etudiant);

        // Organiser les notes par matière
        Map<String, List<Note>> notesParMatiere = notes.stream()
                .collect(Collectors.groupingBy(note -> note.getMatiere().getNom()));

        // Calcul de la moyenne générale
        double moyenneGenerale = notes.stream().mapToDouble(Note::getNote).average().orElse(0);
        moyenneGenerale = Math.round(moyenneGenerale * 100.0) / 100.0;

        // Créer un ByteArrayOutputStream pour contenir le PDF
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Générer le relevé de notes dans le ByteArrayOutputStream
        ReleveNotesGenerator.genererReleve(outputStream, etudiant, notesParMatiere, moyenneGenerale);

        // Préparer les en-têtes de la réponse pour indiquer qu'il s'agit d'un fichier PDF
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "releve_notes.pdf");

        // Retourner le fichier PDF sous forme de tableau de bytes
        return ResponseEntity.ok()
                .headers(headers)
                .body(outputStream.toByteArray());
    }


    static String genererRapport(double moyenneGenerale) {
        if (moyenneGenerale >= 17) {
            return "Des résultats remarquables. Le travail et l'engagement sont exemplaires. Il faut continuer sur cette voie pour maintenir ce niveau d'excellence.";
        } else if (moyenneGenerale >= 14) {
            return "Très bon travail, continuez à maintenir ce niveau d'effort et de motivation pour atteindre des performances exceptionnelles.";
        } else if (moyenneGenerale >= 10) {
            return "Les résultats sont globalement bons, mais il est possible d'atteindre un niveau encore plus élevé avec plus de rigueur et d'engagement.";
        } else if (moyenneGenerale >= 7) {
            return "Des progrès sont attendus. Une implication plus constante et un travail plus méthodique permettront d'atteindre des résultats plus satisfaisants.";
        } else {
            return "Les résultats sont en dessous des attentes. Il est essentiel de prendre des initiatives pour mieux maîtriser les matières abordées.";
        }
    }
}
