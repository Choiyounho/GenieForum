package com.soten.genieforum.data.api.forum

import com.soten.genieforum.domain.model.Comment
import com.soten.genieforum.domain.model.Forum

interface ForumApi {

    suspend fun getAllForum(key: String): List<Forum>

    suspend fun getAllComments(commentId: String): List<Comment>

    suspend fun addComment(comment: Comment): Comment

}