package com.myspring.common

import kotlinx.serialization.Serializable

@Serializable
data class Echo(
    val text: String,
    val times: Int
)
