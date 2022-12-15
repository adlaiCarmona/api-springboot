package com.myspring.service

import com.myspring.common.Message
import com.myspring.repository.MessageRepository
import org.springframework.stereotype.Service

@Service
class MessageService(val db: MessageRepository) {
    fun findMessages(): List<Message> = db.findMessages()

    fun post(message: Message){
        db.save(message)
    }
}