package com.myspring.common

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Reminder (
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val time: Long,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=null
)