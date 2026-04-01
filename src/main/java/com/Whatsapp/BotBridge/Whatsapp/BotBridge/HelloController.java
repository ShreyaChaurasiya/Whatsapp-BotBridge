package com.Whatsapp.BotBridge.Whatsapp.BotBridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class HelloController {

    @Autowired
    private MessageRepository repository;


    @PostMapping("/webhook")
    public String receiveMessage(@RequestBody Message message) {


        repository.save(message);

        String userMessage = message.getContent().toLowerCase();


        if (userMessage.equals("hi")) {
            return "Hello";
        } else if (userMessage.equals("bye")) {
            return "Goodbye";
        } else {
            return "I don't understand";
        }
    }


    @PostMapping
    public Message saveMessage(@RequestBody Message message) {
        return repository.save(message);
    }


    @GetMapping
    public List<Message> getAllMessages() {
        return repository.findAll();
    }


    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable Long id, @RequestBody Message newMessage) {
        Message existingMessage = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        existingMessage.setContent(newMessage.getContent());

        return repository.save(existingMessage);
    }


    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable Long id) {
        repository.deleteById(id);
        return "Message deleted successfully";
    }
}