package com.example.projetjeespringboot.controller;

import com.example.projetjeespringboot.model.Etudiant;
import com.example.projetjeespringboot.model.Note;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Date;  // Importer java.util.Date
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ReleveNotesGenerator {

    public static void genererReleve(ByteArrayOutputStream outputStream, Etudiant etudiant,
                                     Map<String, List<Note>> notesParMatiere, double moyenneGenerale) {
        try {
            // Vérifier si la date de naissance est valide
            if (etudiant.getDateNaissance() == null) {
                throw new IllegalArgumentException("La date de naissance de l'étudiant est null.");
            }

            // Initialisation du document PDF
            PdfWriter writer = new PdfWriter(outputStream); // Utilisation du ByteArrayOutputStream
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Format de la date de naissance au format français (dd/MM/yyyy)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateNaissanceFormatee = etudiant.getDateNaissance().format(formatter);  // Formate la date sans l'heure

            // Log pour s'assurer de la bonne conversion
            System.out.println("Date de naissance formatée: " + dateNaissanceFormatee);

            String rapport = NoteController.genererRapport(moyenneGenerale);

            // Titre et informations générales
            document.add(new Paragraph("Relevé de Notes").setBold().setFontSize(16));
            document.add(new Paragraph("Nom : " + etudiant.getNom()).setFontSize(12));
            document.add(new Paragraph("Prénom : " + etudiant.getPrenom()).setFontSize(12).setMarginBottom(10));  // Plus d'espace ici
            document.add(new Paragraph("Date de naissance : " + dateNaissanceFormatee).setFontSize(12).setMarginBottom(10));  // Espacement
            document.add(new Paragraph("Moyenne générale : " + moyenneGenerale + " / 20").setFontSize(12).setBold().setMarginBottom(20));  // Moyenne en gras
            document.add(new Paragraph(rapport).setFontSize(12).setMarginBottom(20));

            // Parcourir les matières et les notes
            for (Map.Entry<String, List<Note>> entry : notesParMatiere.entrySet()) {
                String matiere = entry.getKey();
                List<Note> notes = entry.getValue();

                // Moyenne par matière
                double moyenneMatiere = notes.stream()
                        .mapToDouble(Note::getNote)
                        .average()
                        .orElse(0);
                document.add(new Paragraph(matiere + " (Moyenne : " + moyenneMatiere + " / 20)").setBold().setMarginBottom(10));

                // Créer une table
                Table table = new Table(new float[]{1, 1});
                table.addHeaderCell("Note (/20)");
                table.addHeaderCell("Date");

                // Remplir la table avec les notes
                for (Note note : notes) {
                    // Si note.getDate() retourne un java.util.Date, utiliser toInstant()
                    Date date = note.getDate();
                    if (date != null) {
                        // Convertir java.util.Date en LocalDate
                        Instant instant = date.toInstant();
                        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

                        // Formatage de la date au format dd/MM/yyyy
                        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        table.addCell(String.valueOf(note.getNote()));
                        table.addCell(formattedDate);  // Ajout de la date formatée
                    }
                }
                document.add(table.setMarginBottom(20));
            }

            document.close();
            System.out.println("Relevé généré et écrit dans le flux.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
