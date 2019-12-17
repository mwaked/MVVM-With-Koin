package com.ok.enjoyer.ui.posts

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ok.enjoyer.R
import com.ok.enjoyer.application.base.mvvm.MainThreadScope
import com.google.android.material.snackbar.Snackbar
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.ok.enjoyer.application.base.ui.BaseFragment
import com.ok.enjoyer.application.helpers.ui.LayoutRes
import com.ok.enjoyer.ui.posts.items.PostItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

@LayoutRes(R.layout.fragment_posts)
class PostsFragment : BaseFragment(){

    private val uiScope = MainThreadScope()
    private lateinit var postsViewModel: PostsViewModel

    private val itemAdapter = ItemAdapter<PostItem>()
    private val fastAdapter = FastAdapter.with(itemAdapter)

    override fun bindView(savedInstanceState: Bundle?) {
        lifecycle.addObserver(uiScope)

        initAdapter()

        initViewModel()

    }

    private fun initAdapter() {
        postsRecyclerView?.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = fastAdapter
        }
        setAdapterItemClickListener()
    }

    private fun setAdapterItemClickListener() {
        fastAdapter.onClickListener = { _, _, item, _ ->
            findNavController().navigate(PostsFragmentDirections.actionPostsFragmentToCommentsFragment(item.postResponse.id))
            true
        }
    }

    private fun initViewModel(){
        postsViewModel = getViewModel()
        postsViewModel.postLiveData.observe(this, Observer { postState ->
            if (postState == null) {
                return@Observer
            }

            handleStates(postState)
        })

        postsViewModel.refreshPosts()
    }

    private fun handleStates(postState: PostsState?) {
        when (postState) {
            is PostsState.Loading -> setUpdateLayoutVisibility(View.VISIBLE)
            is PostsState.Error -> {
                setUpdateLayoutVisibility(View.GONE)
                context?.let {
                    val message = postState.message ?: getString(R.string.error)
                    Snackbar.make(activity!!.rootLayout, message, Snackbar.LENGTH_LONG).show()
                }
            }
            is PostsState.PostsLoaded -> {
                setUpdateLayoutVisibility(View.GONE)

                postState.posts.map { itemAdapter.add(PostItem().withPost(it)) }

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
        postsViewModel.onCleared()
    }
}
