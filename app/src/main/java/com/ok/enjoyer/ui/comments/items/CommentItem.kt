package com.ok.enjoyer.ui.comments.items

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.ok.enjoyer.R
import com.ok.enjoyer.data.remote.models.response.CommentResponse
import kotlinx.android.synthetic.main.item_comment.view.*

open class CommentItem : AbstractItem<CommentItem.ViewHolder>() {

    lateinit var commentResponse: CommentResponse

    fun withComment(commentResponse: CommentResponse): CommentItem{
        this.commentResponse = commentResponse
        return this
    }

    override val type: Int
        get() = R.id.item_comment

    override val layoutRes: Int
        get() = R.layout.item_comment

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : FastAdapter.ViewHolder<CommentItem>(view) {

        override fun bindView(item: CommentItem, payloads: MutableList<Any>) {
            val post = item.commentResponse
            itemView.commentBody.text =  post.body
            itemView.name.text =  post.name
            itemView.email.text =  post.email
        }

        override fun unbindView(item: CommentItem) {
            itemView.commentBody.text =  null
            itemView.name.text =  null
            itemView.email.text =  null
        }
    }
}