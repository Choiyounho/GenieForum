package com.soten.genieforum.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.soten.genieforum.data.api.auth.AuthApi
import com.soten.genieforum.data.api.auth.AuthFirestoreApi
import com.soten.genieforum.data.api.forum.ForumApi
import com.soten.genieforum.data.api.forum.ForumFirestoreApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ForumModule {

    @Provides
    @Singleton
    fun provideForumFirestore() = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return AuthFirestoreApi(provideFirebaseAuth(), provideForumFirestore())
    }

    @Provides
    @Singleton
    fun provideFirestoreApi(): ForumApi = ForumFirestoreApi(provideForumFirestore())

}