package com.soten.genieforum.domain.model

data class Forum(
    val id: String,
    val title: String,
    val description: String,
    val userName: String,
    val comment: Comment
)
