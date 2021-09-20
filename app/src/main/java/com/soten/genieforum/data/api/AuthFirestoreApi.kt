package com.soten.genieforum.data.api

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import dagger.internal.InjectedFieldSignature

class AuthFirestoreApi constructor(
    private val auth: FirebaseAuth
): AuthApi {

    override suspend fun signInWithEmailPassword(email: String, password: String): FirebaseUser? {
        auth.signInWithEmailAndPassword(email, password)

        return auth.currentUser
    }

    override suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser? {
        auth.createUserWithEmailAndPassword(email, password)

        return auth.currentUser
    }

    override fun signOut(): FirebaseUser? {
        auth.signOut()

        return auth.currentUser
    }

    override fun getCurrentUser(): FirebaseUser? = auth.currentUser
}