package com.soten.genieforum.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.soten.genieforum.data.api.AuthApi
import com.soten.genieforum.data.api.AuthFirestoreApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        return AuthFirestoreApi(provideFirebaseAuth())
    }

}