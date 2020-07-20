package com.brwakawa.android.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.brwakawa.android.todo.model.Post
import com.brwakawa.android.todo.R
import com.brwakawa.android.todo.viewHolder.PostViewHolder
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.Query

interface OnItemClickListener{
    fun onItemClick(post: Post, rootView: View)
}

 fun getAdapter(Query: Query, fragment: Fragment, itemClickListener: OnItemClickListener): FirebaseRecyclerAdapter<Post, PostViewHolder> {


    val options = FirebaseRecyclerOptions.Builder<Post>()
        .setLifecycleOwner(fragment)
        .setQuery(Query, Post::class.java)
        .build()

    return object : FirebaseRecyclerAdapter<Post, PostViewHolder>(options) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            return PostViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.task_items, parent, false))
        }

        override fun onBindViewHolder(viewHolder: PostViewHolder, position: Int, post: Post) {
            // Bind to ViewHolder
            viewHolder.bind(post,itemClickListener)
        }
    }
}