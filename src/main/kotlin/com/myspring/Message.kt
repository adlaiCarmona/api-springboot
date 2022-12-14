package com.myspring

import javax.persistence.*

@Entity
class Message (
    @Column(nullable = false)
    val text: String,
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    val id: Int?=null
)