package com.myspring.controller

import com.myspring.common.Message
import com.myspring.service.MessageService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/message")
class MessageController(val service: MessageService){
    @GetMapping
    fun index(): List<Message> = service.findMessages()

    @PostMapping
    fun post(@RequestBody message: Message){
        service.post(message)
    }
}