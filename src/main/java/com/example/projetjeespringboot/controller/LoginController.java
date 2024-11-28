package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Admin;
import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.service.AdminService;
import com.example.projetjeespringboot.service.EnseignantService;
import com.example.projetjeespringboot.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EnseignantService enseignantService;

    @Autowired
    private EtudiantService etudiantService;

    // Méthode GET pour afficher le formulaire de connexion
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Retourner la page login.html
    }

    // Méthode POST pour traiter la soumission du formulaire de connexion
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam("role") String role,
                        Model model,
                        HttpSession session) {

        // Validation de l'authentification via les différents services
        if ("admin".equals(role)) {
            Admin admin = adminService.validateLogin(email, password);
            if (admin != null) {
                session.setAttribute("email", email);
                session.setAttribute("role", role);
                return "admin";  // Page pour l'admin
            }
        } else if ("enseignant".equals(role)) {
            Enseignant enseignant = enseignantService.validateLogin(email, password);
            if (enseignant != null) {
                session.setAttribute("email", email);
                session.setAttribute("role", role);
                return "enseignant";  // Page pour l'enseignant
            }
        } else if ("etudiant".equals(role)) {
            Etudiant etudiant = etudiantService.validateLogin(email, password);
            if (etudiant != null) {
                session.setAttribute("email", email);
                session.setAttribute("role", role);
                return "etudiant";  // Page pour l'étudiant
            }
        }

        // Si l'authentification échoue, afficher un message d'erreur
        model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect.");
        return "login";  // Retourner à la page login
    }
}
