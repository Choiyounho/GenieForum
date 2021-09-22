package com.soten.genieforum.ui.main.home.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.genieforum.data.api.forum.ForumApi
import com.soten.genieforum.domain.model.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val forumApi: ForumApi,
) : ViewModel() {

    private val _commentLiveData = MutableLiveData<List<Comment>>()
    val commentLiveData: LiveData<List<Comment>> = _commentLiveData

    fun fetch(forumId: String) = viewModelScope.launch {
        _commentLiveData.value = forumApi.getAllComments(forumId)
    }

    fun addComment(comment: Comment, forumAge: String) = viewModelScope.launch {
        forumApi.addComment(comment, forumAge)
    }

}