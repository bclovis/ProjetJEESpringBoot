package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.*;
import com.example.projetjeespringboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AjouterCoursController {

    @Autowired
    private FiliereService filiereService;

    @Autowired
    private MatiereService matiereService;

    @Autowired
    private EnseignantService enseignantService;

    @Autowired
    private ProfesseurMatiereService professeurMatiereService;

    @Autowired
    private EmploiDuTempsService emploiDuTempsService;

    // Route pour afficher la page de création de cours
    @GetMapping("/ajouterCours")
    public String afficherPageAjoutCours(Model model) {
        // Création des listes de jours, heures et semaines
        List<String> jours = List.of("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi");
        List<String> heures = List.of("08h-10h", "10h-12h", "12h-14h", "14h-16h", "16h-18h");
        List<Integer> semaines = IntStream.rangeClosed(1, 36).boxed().collect(Collectors.toList());

        // Passer les listes au modèle
        model.addAttribute("jours", jours);
        model.addAttribute("heures", heures);
        model.addAttribute("semaines", semaines);

        // Passer les autres informations nécessaires
        model.addAttribute("filieres", filiereService.findAll());
        model.addAttribute("matieres", matiereService.findAll());
        model.addAttribute("enseignants", enseignantService.findAll());

        return "ajouterCours";  // retourne le nom de la vue
    }

    @PostMapping("/ajouterCours")
    public String ajouterCours(@RequestParam("filiere") String filiereNom,
                               @RequestParam("semestre") int semestre,
                               @RequestParam("matiere") int matiereId,
                               @RequestParam("professeur") String professeurEmail,
                               @RequestParam("jour") String jour,
                               @RequestParam("heure") String heure,
                               @RequestParam("semaine") int semaine,
                               Model model) {

        // Récupérer les objets nécessaires à partir des services
        Filiere filiere = filiereService.findByNom(filiereNom);
        Matiere matiere = matiereService.findById(matiereId);
        Enseignant professeur = enseignantService.findByEmail(professeurEmail);

        // Vérification si le professeur enseigne déjà cette matière
        if (!professeurMatiereService.professeurEnseigneMatiere(professeur, matiere)) {
            model.addAttribute("error", "Le professeur ne peut pas enseigner cette matière.");
            // Remettre les valeurs dans le modèle pour la réinitialisation
            return prepareModel(model, filiereNom, semestre, matiereId, professeurEmail, jour, heure, semaine);
        }

        // Créer l'emploi du temps
        EmploiDuTemps emploiDuTemps = new EmploiDuTemps();
        emploiDuTemps.setFiliere(filiere);
        emploiDuTemps.setSemestre(semestre);
        emploiDuTemps.setMatiere(matiere);
        emploiDuTemps.setProfesseur(professeur);
        emploiDuTemps.setJour(jour);
        emploiDuTemps.setHeure(heure);
        emploiDuTemps.setSemaineDebut(semaine);
        emploiDuTemps.setSemaineFin(semaine);

        // Vérification des conflits d'emploi du temps
        if (emploiDuTempsService.filiereAConflitDeCours(emploiDuTemps)) {
            model.addAttribute("error", "Un cours existe déjà sur ce créneau pour cette filière et ce semestre.");
            // Remettre les valeurs dans le modèle pour la réinitialisation
            return prepareModel(model, filiereNom, semestre, matiereId, professeurEmail, jour, heure, semaine);
        }

        try {
            // Sauvegarder l'emploi du temps
            emploiDuTempsService.save(emploiDuTemps);
            model.addAttribute("message", "Cours ajouté avec succès !");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        // Réinitialiser le formulaire après l'ajout réussi
        return prepareModel(model, "", 1, 0, "", "", "", 1);  // Réinitialiser les valeurs après succès
    }

    // Méthode auxiliaire pour remplir le modèle avec les valeurs nécessaires
    private String prepareModel(Model model, String filiereNom, int semestre, int matiereId, String professeurEmail, String jour, String heure, int semaine) {
        model.addAttribute("filieres", filiereService.findAll());
        model.addAttribute("matieres", matiereService.findAll());
        model.addAttribute("enseignants", enseignantService.findAll());
        model.addAttribute("jours", List.of("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"));
        model.addAttribute("heures", List.of("08h-10h", "10h-12h", "12h-14h", "14h-16h", "16h-18h"));
        model.addAttribute("semaines", IntStream.rangeClosed(1, 36).boxed().collect(Collectors.toList()));

        // Remettre les valeurs de sélection dans le formulaire
        model.addAttribute("filiere", filiereNom);
        model.addAttribute("semestre", semestre);
        model.addAttribute("matiere", matiereId);
        model.addAttribute("professeur", professeurEmail);
        model.addAttribute("jour", jour);
        model.addAttribute("heure", heure);
        model.addAttribute("semaine", semaine);

        return "ajouterCours";  // Retourne la page avec le modèle mis à jour
    }

}
