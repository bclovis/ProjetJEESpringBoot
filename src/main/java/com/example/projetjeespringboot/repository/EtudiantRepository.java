package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
}
