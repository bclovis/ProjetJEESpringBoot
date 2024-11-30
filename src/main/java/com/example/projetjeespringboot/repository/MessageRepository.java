package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Récupérer tous les messages reçus par un utilisateur
    List<Message> findByRecipientOrderBySentAtDesc(String recipient);

    // Récupérer tous les messages envoyés par un utilisateur
    List<Message> findBySenderOrderBySentAtDesc(String sender);
}
