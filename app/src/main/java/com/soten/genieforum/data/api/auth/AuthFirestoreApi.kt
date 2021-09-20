package com.soten.genieforum.data.api.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.soten.genieforum.data.api.ApiKey.USER
import com.soten.genieforum.domain.model.Member
import com.soten.genieforum.extensions.ToastMessage.FAIL_LOGIN
import com.soten.genieforum.extensions.ToastMessage.FAIL_REGISTER_USER
import com.soten.genieforum.extensions.ToastMessage.INVALID_INPUT
import com.soten.genieforum.extensions.ToastMessage.INVALID_USER
import com.soten.genieforum.extensions.ToastMessage.SUCCESS_LOGIN
import com.soten.genieforum.extensions.toast
import kotlinx.coroutines.tasks.await

class AuthFirestoreApi constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) : AuthApi {

    override suspend fun signInWithEmailPassword(email: String, password: String): FirebaseUser? {
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    toast(SUCCESS_LOGIN)
                }
                .addOnFailureListener {
                    toast(FAIL_LOGIN)
                }.await()
        } catch (e: IllegalArgumentException) {
            toast(INVALID_USER)
        }

        return auth.currentUser
    }

    override suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser? {
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    firestore.collection(USER)
                        .document(auth.currentUser?.uid.orEmpty())
                        .set(
                            Member(
                                id = auth.currentUser?.uid.orEmpty()
                            )
                        )
                }
                .addOnFailureListener {
                    toast(FAIL_REGISTER_USER)
                }.await()
        } catch (e: IllegalArgumentException) {
            toast(INVALID_INPUT)
        }

            return auth.currentUser
        }

        override fun signOut(): FirebaseUser? {
            auth.signOut()
            return auth.currentUser
        }

        override fun getCurrentUser(): FirebaseUser? = auth.currentUser
    }