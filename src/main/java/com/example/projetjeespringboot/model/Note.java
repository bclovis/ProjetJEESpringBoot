package com.example.projetjeespringboot.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "note", nullable = false)
    private float note;

    @ManyToOne
    @JoinColumn(name = "etudiant", nullable = false)
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "matiere", nullable = false)
    private Matiere matiere;

    @Column(name = "date", nullable = false)
    private Date date;

    // Constructeurs
    public Note() {
    }

    public Note(float note, Etudiant etudiant, Matiere matiere) {
        this.note = note;
        this.date = new Date(); // Définit la date actuelle par défaut
        this.etudiant = etudiant;
        this.matiere = matiere;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(LocalDate localDate) {
        // Conversion de LocalDate en java.util.Date
        if (localDate != null) {
            this.date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } else {
            this.date = null;
        }
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
}
