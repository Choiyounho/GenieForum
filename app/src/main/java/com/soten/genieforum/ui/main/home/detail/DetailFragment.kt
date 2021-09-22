package com.soten.genieforum.ui.main.home.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.soten.genieforum.GenieForumApplication.Companion.appContext
import com.soten.genieforum.R
import com.soten.genieforum.adapter.CommentAdapter
import com.soten.genieforum.databinding.FragmentDetailBinding
import com.soten.genieforum.domain.model.Comment
import com.soten.genieforum.extensions.ToastMessage.INVALID_LOGIN
import com.soten.genieforum.extensions.toast
import com.soten.genieforum.ui.main.home.HomeFragment.Companion.KEY_FORUM_AGE
import com.soten.genieforum.ui.main.home.HomeFragment.Companion.KEY_FORUM_DESCRIPTION
import com.soten.genieforum.ui.main.home.HomeFragment.Companion.KEY_FORUM_ID
import com.soten.genieforum.ui.main.home.HomeFragment.Companion.KEY_FORUM_TITLE
import com.soten.genieforum.util.TimeUtil.createdTimeForId
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()

    private val commentAdapter by lazy { CommentAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
        observeData()
        initViews()
    }

    private fun observeData() {
        viewModel.commentLiveData.observe(viewLifecycleOwner) {
            commentAdapter.submitList(it)
        }
    }

    private fun bindView() = with(binding) {
        commentRecyclerView.adapter = commentAdapter
        commentRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.fetch(arguments?.get(KEY_FORUM_ID).toString())
    }

    private fun initViews() = with(binding) {
        arguments?.let { bundle ->
            detailTitle.text = bundle.get(KEY_FORUM_TITLE).toString()
            detailDescription.text = bundle.get(KEY_FORUM_DESCRIPTION).toString()
        }

        addButton.setOnClickListener {
            val currentUser = FirebaseAuth.getInstance().currentUser
            currentUser?.let {
                viewModel.addComment(
                    Comment(
                        forumId = arguments?.get(KEY_FORUM_ID).toString(),
                        description = binding.commentEditText.text.toString(),
                        userName = FirebaseAuth.getInstance().currentUser?.uid?.substring(0, 5),
                        createdAt = createdTimeForId()
                    ),
                    arguments?.get(KEY_FORUM_AGE).toString()
                )
                binding.commentEditText.text.clear()
            } ?: run {
                toast(INVALID_LOGIN)
            }

            lifecycleScope.launch {
                addButton.isClickable = false
                delay(1000L)
                viewModel.fetch(arguments?.get(KEY_FORUM_ID).toString())
                delay(3000L)
                addButton.isClickable = true
            }
        }

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}