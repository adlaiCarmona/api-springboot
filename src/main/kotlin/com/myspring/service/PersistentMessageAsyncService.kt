package com.myspring.service

import com.myspring.asDomainObject
import com.myspring.mapToViewModel
import com.myspring.repository.MessageAsyncRepository
import org.springframework.stereotype.Service

@Service
class PersistentMessageService(val messageRepository: MessageAsyncRepository) : MessageAsyncService {

    override suspend fun latest(): List<MessageVM> =
        messageRepository.findLatest()
            .mapToViewModel()

    override suspend fun after(messageId: String): List<MessageVM> =
        messageRepository.findLatest(messageId)
            .mapToViewModel()

    override suspend fun post(message: MessageVM) {
        messageRepository.save(message.asDomainObject())
    }
}