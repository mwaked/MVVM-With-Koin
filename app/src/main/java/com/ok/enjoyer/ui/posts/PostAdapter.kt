package com.ok.enjoyer.ui.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ok.enjoyer.R
import com.ok.enjoyer.data.remote.models.response.PostResponse
import kotlinx.android.synthetic.main.item_post.view.*

class PostAdapter(private var listOfPosts: List<PostResponse>, private val postClickListener: OnPostClickListener) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfPosts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = listOfPosts[position]
        holder.bindView(post)
        holder.itemView.setOnClickListener {
            postClickListener.postClicked(post)
        }
    }

    fun updatePosts(posts: List<PostResponse>) {
        listOfPosts = posts
        notifyDataSetChanged()
    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(post: PostResponse) {
            itemView.apply {
                postTitle.text = post.title
                postBody.text = post.body
            }
        }
    }

    interface OnPostClickListener {
        fun postClicked(post: PostResponse)
    }

}