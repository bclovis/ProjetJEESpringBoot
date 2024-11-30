package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Message;
import com.example.projetjeespringboot.model.Enseignant;
import com.example.projetjeespringboot.model.Etudiant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MessageController {

    private final EntityManager entityManager;

    public MessageController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping("/messagerie/send")
    public String sendMessage(
            HttpSession session,
            @RequestParam String recipient,
            @RequestParam String subject,
            @RequestParam String content,
            Model model
    ) {
        // Récupération de l'utilisateur connecté
        String sender = (String) session.getAttribute("userEmail");
        if (sender == null) {
            return "redirect:/login";
        }
        if (content == null || content.trim().isEmpty()) {
            model.addAttribute("errorMessage", "Le contenu ne peut pas être vide.");
            return "messagerie";
        }

        // Vérifier si le destinataire existe
        boolean emailExists = false;

        // Vérifier dans la table Etudiant
        TypedQuery<Etudiant> queryEtudiant = entityManager.createQuery(
                "SELECT e FROM Etudiant e WHERE e.email = :email", Etudiant.class);
        queryEtudiant.setParameter("email", recipient);
        emailExists = !queryEtudiant.getResultList().isEmpty();

        // Si non trouvé, vérifier dans la table Enseignant
        if (!emailExists) {
            TypedQuery<Enseignant> queryEnseignant = entityManager.createQuery(
                    "SELECT e FROM Enseignant e WHERE e.email = :email", Enseignant.class);
            queryEnseignant.setParameter("email", recipient);
            emailExists = !queryEnseignant.getResultList().isEmpty();
        }

        if (!emailExists) {
            session.setAttribute("confirmationMessage", "L'email du destinataire n'existe pas.");
            return "messagerie";
        }

        // Création et sauvegarde du message
        Message message = new Message(sender, recipient, subject, content, LocalDateTime.now());
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(message);
            entityManager.getTransaction().commit();
            session.setAttribute("confirmationMessage", "Le message a été envoyé avec succès à " + recipient + ".");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            session.setAttribute("confirmationMessage", "Erreur lors de l'envoi du message. Veuillez réessayer.");
        }

        return "messagerie";
    }

    @GetMapping("/messagerie")
    public String getMessages(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") int page) {
        // Récupération de l'utilisateur connecté
        String role = (String) session.getAttribute("role");
        model.addAttribute("role", role);
        String email = (String) session.getAttribute("email");
        System.out.println(email);
        if (email == null) {
            return "redirect:/login";
        }

        // Pagination
        int messagesPerPage = 5;
        int firstResult = (page - 1) * messagesPerPage;

        // Récupérer les messages paginés
        TypedQuery<Message> query = entityManager.createQuery(
                "SELECT m FROM Message m WHERE m.recipient = :email ORDER BY m.sentAt DESC", Message.class);
        query.setParameter("email", email);
        query.setFirstResult(firstResult);
        query.setMaxResults(messagesPerPage);

        List<Message> messages = query.getResultList();

        // Compter le nombre total de messages
        TypedQuery<Long> countQuery = entityManager.createQuery(
                "SELECT COUNT(m) FROM Message m WHERE m.recipient = :email", Long.class);
        countQuery.setParameter("email", email);
        long totalMessages = countQuery.getSingleResult();

        // Ajout des données au modèle
        String confirmationMessage = (String) session.getAttribute("confirmationMessage");
        if (confirmationMessage != null) {
            model.addAttribute("confirmationMessage", confirmationMessage);
            session.removeAttribute("confirmationMessage");
        }

        model.addAttribute("messages", messages);
        model.addAttribute("role", role);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", (int) Math.ceil((double) totalMessages / messagesPerPage));

        return "messagerie"; // Nom de la vue Thymeleaf
    }
}
