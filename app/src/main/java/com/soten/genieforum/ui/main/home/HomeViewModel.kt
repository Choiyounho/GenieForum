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

    private val _forumLiveData = MutableLiveData<List<Forum>>()
    val forumLiveData: LiveData<List<Forum>> = _forumLiveData

    fun fetch(key: String) = viewModelScope.launch {
        _forumLiveData.value = forumApi.getAllForum(key)
    }

}