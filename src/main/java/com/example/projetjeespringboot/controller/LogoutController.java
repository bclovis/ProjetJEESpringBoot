package com.example.projetjeespringboot.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LogoutController {

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        // Invalider la session
        if (session != null) {
            session.invalidate();
        }

        // Rediriger vers la page de login
        redirectAttributes.addFlashAttribute("message", "Déconnexion réussie.");
        return "redirect:/login";  // Assurez-vous que le mappage de /login existe
    }
}
