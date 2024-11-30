package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.EmploiDuTemps;
import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.model.Filiere;
import com.example.projetjeespringboot.repository.EmploiDuTempsRepository;
import com.example.projetjeespringboot.repository.EtudiantRepository;
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
    private EtudiantRepository etudiantRepository;

    public Map<String, Map<String, String>> getEmploiDuTemps(String role, String email, int semaine, String filiereNom) {
        List<EmploiDuTemps> emploiDuTemps;
        Map<String, Map<String, String>> emploiParJourEtHeure = new HashMap<>();
        String filiere = filiereNom;  // On prend directement la filière passée en paramètre, sinon on récupère celle de l'étudiant

        emploiDuTemps = emploiDuTempsRepository.findByFiliereNomAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqual(
                filiere, semaine, semaine);

        // Organiser les données par jour et heure
        for (EmploiDuTemps row : emploiDuTemps) {
            String jour = row.getJour();
            String heure = row.getHeure();
            String matiere = row.getMatiere().getNom();
            String professeur = row.getProfesseur().getNom();

            String contenu = matiere + " (" + professeur + ")";
            emploiParJourEtHeure.putIfAbsent(jour, new HashMap<>());
            emploiParJourEtHeure.get(jour).put(heure, contenu);
        }

        // Ajouter la pause de 12h à 14h pour chaque jour
        for (String jour : List.of("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi")) {
            emploiParJourEtHeure.putIfAbsent(jour, new HashMap<>());
            emploiParJourEtHeure.get(jour).put("12h-14h", "Pause");
        }

        return emploiParJourEtHeure;
    }
}
