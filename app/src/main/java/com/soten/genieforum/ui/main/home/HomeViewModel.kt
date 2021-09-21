package com.soten.genieforum.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.genieforum.data.api.forum.ForumApi
import com.soten.genieforum.domain.model.Forum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val forumApi: ForumApi,
) : ViewModel() {

    private val _age = MutableLiveData<Int>()
    val age: LiveData<Int> = _age

    private val _forumLiveData = MutableLiveData<List<Forum>>()
    val forumLiveData: LiveData<List<Forum>> = _forumLiveData

    init {
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        _forumLiveData.value = forumApi.getAllForum()
    }

    fun setAge(age: Int) = viewModelScope.launch {
        _age.value = age
    }


}