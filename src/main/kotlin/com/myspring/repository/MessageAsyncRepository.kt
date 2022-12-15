package com.myspring.repository

import com.myspring.common.MessageAsync
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param

interface MessageAsyncRepository : CoroutineCrudRepository<MessageAsync, String> {

    // language=SQL
    @Query("""
        SELECT * FROM (
            SELECT * FROM MessageAsync
            ORDER BY "SENT" DESC
            LIMIT 10
        ) ORDER BY "SENT"
    """,
        nativeQuery = true)
    suspend fun findLatest(): List<MessageAsync>

    // language=SQL
    @Query("""
        SELECT * FROM (
            SELECT * FROM MessageAsync
            WHERE SENT > (SELECT SENT FROM MessageAsync WHERE ID = :id)
            ORDER BY "SENT" DESC 
        ) ORDER BY "SENT"
    """,
        nativeQuery = true)
    suspend fun findLatest(@Param("id") id: String): List<MessageAsync>
}