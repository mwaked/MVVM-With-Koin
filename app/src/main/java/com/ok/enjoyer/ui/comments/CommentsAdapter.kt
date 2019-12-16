package com.ok.enjoyer.ui.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ok.enjoyer.R
import com.ok.enjoyer.data.remote.models.response.CommentResponse
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentsAdapter(private var listOfComments: List<CommentResponse>) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfComments.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bindView(listOfComments[position])
    }

    fun updateComments(comments: List<CommentResponse>) {
        listOfComments = comments
        notifyDataSetChanged()
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(comment: CommentResponse) {
            itemView.apply {
                name.text = comment.name
                commentBody.text = comment.body
                email.text = comment.email
            }
        }
    }
}

