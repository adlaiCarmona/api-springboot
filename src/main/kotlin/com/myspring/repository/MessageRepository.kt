package com.myspring.repository

import com.myspring.common.Message
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MessageRepository : CrudRepository<Message, String> {
    @Query("select m from Message m")
    fun findMessages(): List<Message>
}