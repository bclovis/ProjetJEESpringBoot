package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.EmploiDuTemps;
import com.example.projetjeespringboot.repository.EmploiDuTempsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmploiDuTempsService {

    @Autowired
    private EmploiDuTempsRepository emploiDuTempsRepository;

    public Map<String, Map<String, String>> getEmploiDuTemps(String role, String email, int semaine) {
        List<EmploiDuTemps> emploiDuTemps;
        Map<String, Map<String, String>> emploiParJourEtHeure = new HashMap<>();

        if ("etudiant".equals(role)) {
            emploiDuTemps = emploiDuTempsRepository.findByFiliereNomAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqual(
                    "nomDeFiliere", semaine, semaine); // Remplacez "nomDeFiliere" par le bon nom
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

        return emploiParJourEtHeure;
    }
}
