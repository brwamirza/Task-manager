package com.brwakawa.android.todo.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.brwakawa.android.todo.model.Post
import com.brwakawa.android.todo.R
import com.brwakawa.android.todo.adapter.OnItemClickListener
import com.brwakawa.android.todo.databinding.TaskItemsBinding

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var messageView: TextView = itemView.findViewById(R.id.post_Message)
    private val binding = TaskItemsBinding.bind(itemView)

    fun bind(post: Post,clickListener: OnItemClickListener) {
        messageView.text = post.message

        itemView.setOnClickListener {
            clickListener.onItemClick(post,binding.card)
        }
    }
}