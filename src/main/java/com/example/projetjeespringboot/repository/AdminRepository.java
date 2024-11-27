package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    // Vous pouvez ajouter des méthodes de recherche personnalisées ici si nécessaire
}
