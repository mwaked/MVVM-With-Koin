package com.ok.enjoyer.ui.comments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ok.enjoyer.R
import com.ok.enjoyer.application.base.mvvm.MainThreadScope
import com.google.android.material.snackbar.Snackbar
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.ok.enjoyer.application.base.ui.BaseFragment
import com.ok.enjoyer.application.helpers.ui.LayoutRes
import com.ok.enjoyer.ui.comments.items.CommentItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_comments.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

@LayoutRes(R.layout.fragment_comments)
class CommentsFragment : BaseFragment() {

    private lateinit var commentsViewModel: CommentsViewModel
    private val uiScope = MainThreadScope()

    private val args: CommentsFragmentArgs by navArgs()

    private val itemAdapter = ItemAdapter<CommentItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    override fun bindView(savedInstanceState: Bundle?) {

        lifecycle.addObserver(uiScope)

        initAdapter()

        initViewModel()

    }

    private fun initViewModel() {
        commentsViewModel = getViewModel()
        commentsViewModel.commentsLiveData.observe(this, Observer { commentState ->
            if (commentState == null) {
                return@Observer
            }

            handleStates(commentState)
        })

        commentsViewModel.getComments(args.postId)
    }

    private fun initAdapter() {

        commentsRecyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = fastAdapter
        }
    }

    private fun handleStates(
        commentState: CommentsState?) {
        when (commentState) {
            is CommentsState.Loading -> {
                setUpdateLayoutVisibility(View.VISIBLE)
            }

            is CommentsState.Error -> {
                setUpdateLayoutVisibility(View.GONE)
                context?.let {
                    val message = commentState.message ?: getString(R.string.error)
                    Snackbar.make(activity!!.rootLayout, message, Snackbar.LENGTH_LONG).show()
                }
            }
            is CommentsState.PostsLoaded -> {
                setUpdateLayoutVisibility(View.GONE)
                commentState.comments.map { itemAdapter.add(CommentItem().withComment(it)) }
            }
        }
    }

    private fun setUpdateLayoutVisibility(value: Int) {
        activity!!.updateLayout.apply {
            visibility = value
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        commentsViewModel.onCleared()
    }

}
