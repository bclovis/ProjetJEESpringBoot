package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Integer> {
    Filiere findByNom(String nom);
}
