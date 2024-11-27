package com.example.projetjeespringboot.repository;

import com.example.projetjeespringboot.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
