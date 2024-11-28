package com.example.projetjeespringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class LogoutController {

    // Méthode pour la déconnexion
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        // Invalider la session
        if (session != null) {
            session.invalidate();
        }
        // Rediriger vers la page de connexion
        return "redirect:/login";  // Redirige vers login.html
    }
}
