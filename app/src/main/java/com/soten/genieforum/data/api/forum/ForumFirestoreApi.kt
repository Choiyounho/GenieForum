package com.soten.genieforum.data.api.forum

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.soten.genieforum.data.api.ApiKey.FORUM10
import com.soten.genieforum.domain.model.Forum
import kotlinx.coroutines.tasks.await

class ForumFirestoreApi(
    private val firestore: FirebaseFirestore,
) : ForumApi {

    override suspend fun getAllForum(): List<Forum> =
        firestore.collection(FORUM10)
            .get()
            .await()
            .map { it.toObject() }

}