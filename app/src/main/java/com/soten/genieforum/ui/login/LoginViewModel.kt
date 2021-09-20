package com.soten.genieforum.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.soten.genieforum.data.api.auth.AuthApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authApi: AuthApi
): ViewModel() {

    private val _authLiveData = MutableLiveData<FirebaseUser?>()
    val authLiveData: LiveData<FirebaseUser?> = _authLiveData

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        _authLiveData.value = authApi.getCurrentUser()
    }

    fun signUp(email: String, password: String) = viewModelScope.launch {
        authApi.signUpWithEmailPassword(email, password)
        _authLiveData.value = authApi.getCurrentUser()
    }

    fun signIn(email: String, password: String) = viewModelScope.launch {
        authApi.signInWithEmailPassword(email, password)
        _authLiveData.value = authApi.getCurrentUser()
    }

    fun getCurrentUser(): FirebaseUser? {
        return authApi.getCurrentUser()
    }

}