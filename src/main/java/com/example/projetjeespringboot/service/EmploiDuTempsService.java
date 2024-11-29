package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.EmploiDuTemps;
import com.example.projetjeespringboot.model.Etudiant;  // Assurez-vous d'importer votre modèle Etudiant
import com.example.projetjeespringboot.repository.EmploiDuTempsRepository;
import com.example.projetjeespringboot.repository.EtudiantRepository;  // Le repository pour l'Etudiant
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmploiDuTempsService {

    @Autowired
    private EmploiDuTempsRepository emploiDuTempsRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;  // Le repository pour accéder aux étudiants

    public Map<String, Map<String, String>> getEmploiDuTemps(String role, String email, int semaine) {
        List<EmploiDuTemps> emploiDuTemps;
        Map<String, Map<String, String>> emploiParJourEtHeure = new HashMap<>();
        String filiere = "";  // Variable pour stocker la filière de l'étudiant

        if ("etudiant".equals(role)) {
            // Récupérer l'étudiant par son email
            Etudiant etudiant = etudiantRepository.findByEmail(email);
            if (etudiant != null) {
                filiere = etudiant.getFiliere().toString();  // Récupérer la filière de l'étudiant
            }

            // Utilisation de la filière récupérée dans la requête
            emploiDuTemps = emploiDuTempsRepository.findByFiliereNomAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqual(
                    filiere, semaine, semaine);  // Utiliser la filière dynamique de l'étudiant
        } else if ("enseignant".equals(role)) {
            emploiDuTemps = emploiDuTempsRepository.findByProfesseurEmailAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqual(
                    email, semaine, semaine);
        } else {
            throw new IllegalArgumentException("Rôle utilisateur non valide : " + role);
        }

        // Organiser les données par jour et heure
        for (EmploiDuTemps row : emploiDuTemps) {
            String jour = row.getJour();
            String heure = row.getHeure();
            String cours = row.getMatiere().getNom();
            String professeur = row.getProfesseur().getNom();

            String contenu = cours + " (" + professeur + ")";
            emploiParJourEtHeure.putIfAbsent(jour, new HashMap<>());
            emploiParJourEtHeure.get(jour).put(heure, contenu);
        }

        // Ajouter la pause de 12h à 14h
        for (String jour : List.of("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi")) {
            emploiParJourEtHeure.putIfAbsent(jour, new HashMap<>());
            emploiParJourEtHeure.get(jour).put("12h-14h", "Pause");
        }

        System.out.println(emploiParJourEtHeure);
        return emploiParJourEtHeure;
    }
}
