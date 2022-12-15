package com.myspring.service

interface MessageAsyncService {

    suspend fun latest(): List<MessageVM>

    suspend fun after(lastMessageId: String): List<MessageVM>

    suspend fun post(message: MessageVM)
}