package com.example.projetjeespringboot.service;

import com.example.projetjeespringboot.model.Message;
import com.example.projetjeespringboot.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void sendMessage(String sender, String recipient, String subject, String content) {
        Message message = new Message(sender, recipient, subject, content, LocalDateTime.now());
        messageRepository.save(message);
    }

    public List<Message> getMessagesForRecipient(String recipient) {
        return messageRepository.findByRecipientOrderBySentAtDesc(recipient);
    }
}
