package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.DemandeFiliere;
import com.example.projetjeespringboot.service.DemandeFiliereService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/gererDemandes")
public class GererDemandeFiliereController {

    private final DemandeFiliereService demandeFiliereService;

    public GererDemandeFiliereController(DemandeFiliereService demandeFiliereService) {
        this.demandeFiliereService = demandeFiliereService;
    }

    @GetMapping
    public String showDemandes(Model model,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "recherche", required = false) String keyword) {
        List<DemandeFiliere> demandes = demandeFiliereService.findDemandes(page, keyword);
        int totalPages = demandeFiliereService.getTotalPages(keyword);

        model.addAttribute("demandes", demandes);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("recherche", keyword);

        return "gererDemandes"; // Nom du fichier HTML Thymeleaf à utiliser
    }

    @PostMapping
    public String handleAction(@RequestParam("action") String action,
                               @RequestParam("demandeId") int demandeId,
                               @RequestParam(value = "commentaire", required = false) String commentaire,
                               HttpServletRequest request) {

        String keyword = request.getParameter("recherche");
        demandeFiliereService.handleAction(action, demandeId, commentaire);

        return "redirect:/gererDemandes" + (keyword != null ? "?recherche=" + keyword : "");
    }
}
