package com.soten.genieforum.data.api.auth

import com.google.firebase.auth.FirebaseUser

interface AuthApi {

    suspend fun signInWithEmailPassword(email: String, password: String): FirebaseUser?

    suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser?

    fun signOut(): FirebaseUser?

    fun getCurrentUser(): FirebaseUser?

}