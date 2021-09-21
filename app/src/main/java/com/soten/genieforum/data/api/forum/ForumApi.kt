package com.soten.genieforum.data.api.forum

import com.soten.genieforum.domain.model.Forum

interface ForumApi {

    suspend fun getAllForum(): List<Forum>

}