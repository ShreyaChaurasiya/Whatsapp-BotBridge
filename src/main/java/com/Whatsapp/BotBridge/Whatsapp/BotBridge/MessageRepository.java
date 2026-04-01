package com.Whatsapp.BotBridge.Whatsapp.BotBridge;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}