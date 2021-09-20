package com.soten.genieforum.domain.model

data class Member(
    val id: String,
    val nickName: String = "익명${id.substring(0, 5)}",
    val point: Int = 0
)
