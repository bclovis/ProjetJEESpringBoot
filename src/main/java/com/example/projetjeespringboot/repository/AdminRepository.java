package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {

    Admin findByEmail(String email);
}
