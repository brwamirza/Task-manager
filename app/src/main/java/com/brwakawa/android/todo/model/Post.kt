package com.brwakawa.android.todo.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Post(
    var message: String? = null ,
    var taskStatus: String? = null,
    var id: String? = null
)