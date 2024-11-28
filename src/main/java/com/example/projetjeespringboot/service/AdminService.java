package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Admin;
import com.example.projetjeespringboot.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin saveAdmin(Admin admin)
    {
        return adminRepository.save(admin);
    }

    /*
    public Admin updateAdmin(Admin admin, String adminId)
    {
        //
    }
    */

    public void deleteAdminById(String adminId)
    {
        adminRepository.deleteById(adminId);
    }

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
}
