package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.model.Matiere;
import com.example.projetjeespringboot.model.ProfesseurMatiere;
import com.example.projetjeespringboot.repository.EnseignantRepository;
import com.example.projetjeespringboot.repository.MatiereRepository;
import com.example.projetjeespringboot.repository.ProfesseurMatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AssocierProfesseurMatiereController {

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private ProfesseurMatiereRepository professeurMatiereRepository;

    // Afficher les professeurs, matières et associations
    @GetMapping("/associerProfesseurMatiere")
    public String afficherFormulaire(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        // Récupérer la liste des professeurs
        List<Enseignant> professeurs = enseignantRepository.findAll();
        model.addAttribute("professeurs", professeurs);

        // Récupérer la liste des matières
        List<Matiere> matieres = matiereRepository.findAll();
        model.addAttribute("matieres", matieres);

        // Récupérer toutes les associations (avec ou sans recherche)
        List<ProfesseurMatiere> associations = (keyword == null || keyword.isEmpty()) ? professeurMatiereRepository.findAll() : professeurMatiereRepository.findAssociations(keyword);
        model.addAttribute("associations", associations); // Liste des associations

        // Ajouter le mot-clé dans le modèle pour réutiliser dans le formulaire de recherche
        model.addAttribute("keyword", keyword);

        return "associerProfesseurMatiere"; // Retourne à la vue Thymeleaf
    }

    // Associer un professeur à une matière
    @PostMapping("/associerProfesseurMatiere")
    public String associerProfesseurMatiere(@RequestParam("professeur_email") String professeurEmail,
                                            @RequestParam("matiere_id") Integer matiereId,
                                            Model model) {

        if (professeurEmail == null || professeurEmail.isEmpty() ||
                matiereId == null || matiereId <= 0) {
            model.addAttribute("errorMessage", "Veuillez sélectionner un professeur et une matière.");
            return afficherFormulaire(null, model); // Afficher à nouveau avec le message d'erreur
        }

        // Vérifier si l'association existe déjà
        List<ProfesseurMatiere> existantes = professeurMatiereRepository.findByEnseignantEmailAndMatiereId(professeurEmail, matiereId);

        if (!existantes.isEmpty()) {
            model.addAttribute("errorMessage", "Cette association existe déjà !");
            return afficherFormulaire(null, model); // Afficher à nouveau avec le message d'erreur
        }

        // Créer une nouvelle association
        ProfesseurMatiere association = new ProfesseurMatiere();

        // On doit charger l'enseignant et la matière correspondants à partir de leur email et id
        Enseignant enseignant = enseignantRepository.findByEmail(professeurEmail); // Utilisez un repository pour Enseignant
        Matiere matiere = matiereRepository.findById(matiereId).orElse(null); // Vérifier si la matière existe

        if (enseignant != null && matiere != null) {
            association.setEnseignant(enseignant);  // Associez l'enseignant trouvé
            association.setMatiere(matiere);  // Associez la matière trouvée
            professeurMatiereRepository.save(association);
        } else {
            model.addAttribute("errorMessage", "Professeur ou matière introuvable.");
            return afficherFormulaire(null, model);
        }

        return "associerProfesseurMatiere"; // Rediriger vers la même page pour afficher les mises à jour
    }
}
