package com.example.android.taskManager.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.taskManager.model.Post
import com.example.android.taskManager.R
import com.example.android.taskManager.adapter.OnItemClickListener
import com.example.android.taskManager.databinding.TaskItemsBinding

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