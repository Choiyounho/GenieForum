package com.soten.genieforum.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.soten.genieforum.data.api.AuthApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authApi: AuthApi
): ViewModel() {

    private val _authLiveData = MutableLiveData<FirebaseUser?>()
    val authLiveData get() = _authLiveData

    fun signUp(email: String, password: String) = viewModelScope.launch {
        authApi.signUpWithEmailPassword(email, password)

        _authLiveData.value = authApi.getCurrentUser()
    }

}