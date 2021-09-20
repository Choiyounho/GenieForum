package com.soten.genieforum.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.genieforum.data.api.forum.ForumApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val forumApi: ForumApi
): ViewModel() {

    private val _age = MutableLiveData<Int>()
    val age: LiveData<Int> = _age

    fun setAge(age: Int) = viewModelScope.launch {
        _age.value = age
    }
}