package com.soten.genieforum.domain.model

data class Member(
    val id: String,
    val nickName: String = id.substring(0, 5),
    val point: Int = 0
)
