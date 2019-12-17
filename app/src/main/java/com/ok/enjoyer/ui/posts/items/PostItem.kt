package com.ok.enjoyer.ui.posts.items

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.ok.enjoyer.R
import com.ok.enjoyer.data.remote.models.response.PostResponse
import kotlinx.android.synthetic.main.item_post.view.*

open class PostItem : AbstractItem<PostItem.ViewHolder>() {

    lateinit var postResponse: PostResponse

    fun withPost(postResponse: PostResponse): PostItem{
        this.postResponse = postResponse
        return this
    }

    override val type: Int
        get() = R.id.item_post

    override val layoutRes: Int
        get() = R.layout.item_post

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<PostItem>(view) {

        override fun bindView(item: PostItem, payloads: MutableList<Any>) {
            val post = item.postResponse
            itemView.postTitle.text =  post.title
            itemView.postBody.text =  post.body
        }

        override fun unbindView(item: PostItem) {
            itemView.postTitle.text = null
            itemView.postBody.text = null
        }
    }
}