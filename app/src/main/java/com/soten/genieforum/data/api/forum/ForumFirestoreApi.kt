package com.soten.genieforum.data.api.forum

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import com.soten.genieforum.data.api.ApiKey.COMMENT
import com.soten.genieforum.data.api.ApiKey.CREATED_AT
import com.soten.genieforum.data.api.ApiKey.FORUM10
import com.soten.genieforum.data.api.ApiKey.FORUM_ID
import com.soten.genieforum.domain.model.Comment
import com.soten.genieforum.domain.model.Forum
import kotlinx.coroutines.tasks.await

class ForumFirestoreApi(
    private val firestore: FirebaseFirestore,
) : ForumApi {

    override suspend fun getAllForum(key: String): List<Forum> =
        firestore.collection(key)
            .get()
            .await()
            .map { it.toObject() }

    override suspend fun getAllComments(forumId: String): List<Comment> {
        return try {
            firestore.collection(COMMENT)
                .whereEqualTo(FORUM_ID, forumId)
                .orderBy(CREATED_AT, Query.Direction.DESCENDING)
                .get()
                .await()
                .map {
                    it.toObject()
                }
        } catch (e: Exception) {
            listOf()
        }
    }


    override suspend fun addComment(comment: Comment, forumAge: String): Comment {
        val newCommentReference = firestore.collection(COMMENT).document()
        val forumReference = firestore.collection("Forums").document(comment.forumId!!)

        firestore.runTransaction { transaction ->
            val forum = transaction.get(forumReference).toObject<Forum>()!!

            val oldCommentCount = forum.commentCount?.toInt() ?: 0

            val newCommentCount = oldCommentCount + 1

            transaction.set(
                forumReference,
                forum.copy(
                    commentCount = newCommentCount.toString()
                )
            )

            transaction.set(
                newCommentReference,
                comment,
                SetOptions.merge()
            )

        }.await()

        return newCommentReference.get().await().toObject<Comment>()!!
    }
}