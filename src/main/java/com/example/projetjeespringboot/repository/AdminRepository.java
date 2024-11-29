package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    // Rechercher un admin par son email (clé primaire)
    Admin findByEmail(String email);
}
