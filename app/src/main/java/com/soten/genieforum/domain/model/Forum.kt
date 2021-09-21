package com.soten.genieforum.domain.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forum(
    @DocumentId
    val id: String? = null,
    val title: String? = null,
    val description: String? = null,
    val topic: String? = Topic.FREE.name,
    val userName: String? = null,
    val createdAt: String? = null,
    val comments: @RawValue ArrayList<Comment>? = arrayListOf(),
): Parcelable
