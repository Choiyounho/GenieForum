package com.soten.genieforum.data.api.forum

import com.google.firebase.firestore.FirebaseFirestore

class ForumFirestoreApi(
    private val firestore: FirebaseFirestore
) : ForumApi {

    override suspend fun getAllForum() {
        TODO("Not yet implemented")
    }
}