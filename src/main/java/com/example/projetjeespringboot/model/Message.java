package com.example.projetjeespringboot.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expediteur", nullable = false)
    private String expediteur;

    @Column(name = "destinataire", nullable = false)
    private String destinataire;

    @Column(name = "sujet", nullable = false)
    private String sujet;

    @Column(name = "contenu", nullable = false)
    private String contenu;

    @Column(name = "date_envoi", nullable = false)
    private LocalDateTime dateEnvoi;

    public Message() {}

    public Message(String expediteur, String destinataire, String sujet, String contenu, LocalDateTime dateEnvoi) {
        this.expediteur = expediteur;
        this.destinataire = destinataire;
        this.sujet = sujet;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
    }

    // Getters et setters
    // ...
}
