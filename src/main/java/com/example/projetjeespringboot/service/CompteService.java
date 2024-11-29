package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.repository.EnseignantRepository;
import com.example.projetjeespringboot.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class CompteService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EnseignantRepository enseignantRepository;

    // Valider le format de l'email
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Calculer l'âge à partir de la date de naissance
    public int calculateAge(Date dateNaissance) {
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(dateNaissance);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    // Vérifier si l'email existe déjà pour un étudiant
    public boolean isEmailExists(String email) {
        Long countEtudiant = etudiantRepository.countByEmail(email);
        Long countEnseignant = enseignantRepository.countByEmail(email);
        return countEtudiant > 0 || countEnseignant > 0;
    }

    // Créer un étudiant
    public void createEtudiant(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
    }

    // Créer un enseignant
    public void createEnseignant(Enseignant enseignant) {
        enseignantRepository.save(enseignant);
    }
}
