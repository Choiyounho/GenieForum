package com.soten.genieforum.ui.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soten.genieforum.data.api.forum.ForumApi
import com.soten.genieforum.domain.model.Forum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddForumViewModel @Inject constructor(
    private val forumApi: ForumApi
): ViewModel() {

    fun addForum(forumAge: String, forum: Forum) = viewModelScope.launch {
        forumApi.addForum(forumAge, forum)
    }


}