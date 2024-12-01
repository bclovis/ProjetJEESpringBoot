package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.service.EnseignantService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping
public class EnseignantController {

@Autowired
private EnseignantService enseignantService;

private static final int PAGE_SIZE = 20;

@GetMapping("/enseignant")
public String afficherPageEnseignant(HttpSession session, Model model) {
    String role = (String) session.getAttribute("role");
    model.addAttribute("role", role);
    return "enseignant";
}

@GetMapping("/gererEnseignants")
public String gererEnseignant(
    @RequestParam(value = "page", defaultValue = "1") int page,
    @RequestParam(value = "recherche", required = false) String recherche,Model model) {

            // Récupération des étudiants et du nombre total de pages
            List<Enseignant> enseignants = enseignantService.getEnseignants(page, PAGE_SIZE, recherche);
            int totalPages = enseignantService.getTotalPages(PAGE_SIZE, recherche);

            // Ajout des données au modèle pour la vue
            model.addAttribute("enseignants", enseignants);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("recherche", recherche);

            return "gererEnseignants";
        }

        @PostMapping("/enseignants/modifier")
        public String afficherFormulaireModification(@RequestParam("email") String email, Model model) {
            Enseignant enseignant = enseignantService.getEnseignantByEmail(email);
            if (enseignant == null) {
                return "redirect:/gererEnseignants?error=Enseignant%20introuvable";
            }
            model.addAttribute("enseignant", enseignant);
            return "modifierEnseignant"; // Nom de la vue (HTML) dans `resources/templates`
        }

        @PostMapping("/enseignants/saveChanges")
        public String enregistrerModification(@RequestParam("email") String email,
                                              @RequestParam("nom") String nom,
                                              @RequestParam("prenom") String prenom,
                                              @RequestParam("dateNaissance") String dateNaissanceStr,
                                              @RequestParam("mdp") String mdp,
                                              Model model) {

            // Conversion de la date de naissance depuis String
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dateNaissance = sdf.parse(dateNaissanceStr);

                // Mise à jour des informations de l'étudiant
                Enseignant enseignant = new Enseignant(email, nom, prenom, dateNaissance, mdp);
                boolean modificationRéussie = enseignantService.modifierEnseignant(enseignant);

                if (modificationRéussie) {
                    model.addAttribute("success", "Étudiant modifié avec succès.");
                    model.addAttribute("enseignant", enseignant);
                    return "modifierEnseignant";
                } else {
                    model.addAttribute("error", "Erreur lors de la modification de l'étudiant.");
                    model.addAttribute("enseignant", enseignant);
                    return "modifierEnseignant"; // Retourne avec message d'erreur
                }
            } catch (ParseException e) {
                model.addAttribute("error", "Erreur de format de date.");
                return "modifierEnseignant";
            }
        }

        @PostMapping("/enseignants/supprimer")
        public String supprimerEnseignant(@RequestParam("email") String email, Model model) {
            boolean suppressionReussie = enseignantService.supprimerEnseignant(email);
            if (suppressionReussie) {
                model.addAttribute("success", "Enseignant supprimé avec succès.");
            } else {
                model.addAttribute("error", "Erreur lors de la suppression de l'enseignant.");
            }
            return "redirect:/gererEnseignants"; // Redirection vers la page de gestion
        }
    }

