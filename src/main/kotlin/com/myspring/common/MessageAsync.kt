package com.myspring.common

import java.time.Instant
import javax.persistence.*

@Entity
class MessageAsync(
    @Column(nullable = false)
    val content: String,
    @Column(nullable = false)
    val contentType: ContentType,
    @Column(nullable = false)
    val sent: Instant,
    @Column(nullable = false)
    val username: String,
    val userAvatarImageLink: String,
    @Id var id: String? = null)

enum class ContentType {
    PLAIN, MARKDOWN
}