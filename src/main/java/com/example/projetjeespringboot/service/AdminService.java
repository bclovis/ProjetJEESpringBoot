package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Admin;
import com.example.projetjeespringboot.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Méthode pour créer ou mettre à jour un administrateur
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Méthode pour obtenir un administrateur par email
    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepository.findById(email);
    }

    // Méthode pour obtenir tous les administrateurs
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Méthode pour supprimer un administrateur par email
    public void deleteAdminByEmail(String email) {
        adminRepository.deleteById(email);
    }
}
