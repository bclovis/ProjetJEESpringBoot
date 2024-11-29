package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    Etudiant findByEmail(String email);

    Long countByEmail(String email);
}
