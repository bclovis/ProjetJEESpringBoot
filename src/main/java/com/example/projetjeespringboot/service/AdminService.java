package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Admin;
import com.example.projetjeespringboot.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    // Injecter le repository Admin pour accéder aux données
    @Autowired
    private AdminRepository adminRepository;

    // Méthode pour valider un admin par email et mot de passe
    public Admin validateLogin(String email, String password) {
        // Rechercher un admin par son email
        Admin admin = adminRepository.findByEmail(email);

        // Si un admin est trouvé et que le mot de passe correspond
        if (admin != null && admin.getMdp().equals(password)) {
            return admin; // Retourner l'admin si l'authentification réussit
        }

        return null; // Retourner null si l'authentification échoue
    }

    // Méthode pour ajouter un nouvel administrateur (si nécessaire)
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin); // Sauvegarder l'admin dans la base de données
    }

    // Méthode pour sauvegarder ou mettre à jour un admin
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Méthode pour récupérer un admin par email
    public Optional<Admin> getAdminByEmail(String email) {
        return adminRepository.findById(email);
    }

    // Méthode pour récupérer tous les admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Méthode pour supprimer un admin par email
    public void deleteAdminByEmail(String email) {
        adminRepository.deleteById(email);
    }
}
