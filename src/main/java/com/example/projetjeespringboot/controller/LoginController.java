package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Admin;
import com.example.projetjeespringboot.service.AdminService;
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

        // Validation de l'authentification via AdminService
        Admin admin = adminService.validateLogin(email, password);

        // Si l'utilisateur existe et les informations sont correctes
        if (admin != null) {
            // Ajouter des informations de session
            session.setAttribute("email", email);
            session.setAttribute("role", role);

            // Rediriger vers la page appropriée en fonction du rôle
            if ("admin".equals(role)) {
                return "admin";  // Page pour l'admin (admin.html)
            } else if ("enseignant".equals(role)) {
                return "enseignant_dashboard";  // Page pour l'enseignant
            } else {
                model.addAttribute("error", "Rôle non reconnu.");
                return "login";  // Retourner à la page login
            }
        }

        // Si l'authentification échoue, afficher un message d'erreur et retourner à la page de login
        model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect.");
        return "login";  // Retourner à la page login
    }
}
