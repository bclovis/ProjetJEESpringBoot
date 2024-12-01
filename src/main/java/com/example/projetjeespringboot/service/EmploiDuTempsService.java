package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.EmploiDuTemps;
import com.example.projetjeespringboot.model.ProfesseurMatiere;
import com.example.projetjeespringboot.model.Filiere;
import com.example.projetjeespringboot.repository.EmploiDuTempsRepository;
import com.example.projetjeespringboot.repository.ProfesseurMatiereRepository;
import com.example.projetjeespringboot.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmploiDuTempsService {

    @Autowired
    private EmploiDuTempsRepository emploiDuTempsRepository;

    @Autowired
    private ProfesseurMatiereRepository professeurMatiereRepository;

    @Autowired
    private FiliereRepository filiereRepository;

    // Méthode pour enregistrer un emploi du temps avec les vérifications
    public EmploiDuTemps save(EmploiDuTemps emploiDuTemps) throws Exception {
        // Vérification si le professeur enseigne la matière
        long count = professeurMatiereRepository.countByEnseignantEmailAndMatiereId(
                emploiDuTemps.getProfesseur().getEmail(),
                emploiDuTemps.getMatiere().getId()
        );
        if (count == 0) {
            throw new Exception("Le professeur sélectionné n'enseigne pas cette matière.");
        }

        // Vérification des conflits d'emploi du temps pour le même professeur
        count = emploiDuTempsRepository.countByJourAndHeureAndProfesseurEmail(
                emploiDuTemps.getJour(),
                emploiDuTemps.getHeure(),
                emploiDuTemps.getProfesseur().getEmail()
        );
        if (count > 0) {
            throw new Exception("Le professeur a déjà un cours programmé sur ce créneau.");
        }

        // Vérification des conflits d'emploi du temps pour la filière
        count = emploiDuTempsRepository.countByJourAndHeureAndFiliereNomAndSemestreAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqual(
                emploiDuTemps.getJour(),
                emploiDuTemps.getHeure(),
                emploiDuTemps.getFiliere().getNom(),
                emploiDuTemps.getSemestre(),
                emploiDuTemps.getSemaineDebut(),
                emploiDuTemps.getSemaineFin()
        );
        if (count > 0) {
            throw new Exception("Un cours existe déjà sur ce créneau pour cette filière.");
        }

        // Si toutes les vérifications passent, enregistrer le cours
        return emploiDuTempsRepository.save(emploiDuTemps);
    }

    // Méthode pour récupérer l'emploi du temps
    public Map<String, Map<String, String>> getEmploiDuTemps(String role, String email, int semaine, String filiereNom) {
        List<EmploiDuTemps> emploiDuTemps;
        Map<String, Map<String, String>> emploiParJourEtHeure = new HashMap<>();
        String filiere = filiereNom;

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

    // Méthode pour vérifier s'il y a un conflit pour la filière
    public boolean filiereAConflitDeCours(EmploiDuTemps emploiDuTemps) {
        long count = emploiDuTempsRepository.countByJourAndHeureAndFiliereNomAndSemestreAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqual(
                emploiDuTemps.getJour(),
                emploiDuTemps.getHeure(),
                emploiDuTemps.getFiliere().getNom(),
                emploiDuTemps.getSemestre(),
                emploiDuTemps.getSemaineDebut(),
                emploiDuTemps.getSemaineFin()
        );
        return count > 0; // Si count > 0, cela signifie qu'il y a un conflit pour cette filière
    }

    public boolean supprimerCours(String role, String email, String jour, String heure, Integer coursId) {
        // Vérifier que le cours existe
        Optional<EmploiDuTemps> emploiDuTempsOpt = emploiDuTempsRepository.findById(coursId);

        if (emploiDuTempsOpt.isPresent()) {
            EmploiDuTemps emploiDuTemps = emploiDuTempsOpt.get();

            // Vérifier si le cours correspond bien à l'utilisateur (optionnel)
            // Si tu as une logique basée sur l'email ou le rôle de l'utilisateur, ajoute-la ici

            // Supprimer le cours de la base de données
            emploiDuTempsRepository.delete(emploiDuTemps);
            return true;
        } else {
            return false; // Le cours n'a pas été trouvé
        }
    }


    // Méthode pour récupérer les IDs des cours dans l'emploi du temps
    public Map<String, Map<String, Integer>> getEmploiDuTempsId(String role, String email, int semaine, String filiereNom) {
        List<EmploiDuTemps> emploiDuTemps;
        Map<String, Map<String, Integer>> emploiIdParJourEtHeure = new HashMap<>();
        String filiere = filiereNom;

        emploiDuTemps = emploiDuTempsRepository.findByFiliereNomAndSemaineDebutLessThanEqualAndSemaineFinGreaterThanEqual(
                filiere, semaine, semaine);

        // Organiser les données par jour et heure, avec l'ID du cours
        for (EmploiDuTemps row : emploiDuTemps) {
            String jour = row.getJour();
            String heure = row.getHeure();
            Integer coursId = row.getId();  // Assumons que l'ID du cours est récupéré ici

            // Ajouter les données dans la map
            emploiIdParJourEtHeure.putIfAbsent(jour, new HashMap<>());
            emploiIdParJourEtHeure.get(jour).put(heure, coursId);
        }

        // Ajouter la pause de 12h à 14h pour chaque jour (aucun ID de cours pour la pause)
        for (String jour : List.of("Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi")) {
            emploiIdParJourEtHeure.putIfAbsent(jour, new HashMap<>());
            emploiIdParJourEtHeure.get(jour).put("12h-14h", null); // Pas d'ID pour la pause
        }

        return emploiIdParJourEtHeure;
    }

}
