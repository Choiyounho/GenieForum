package com.soten.genieforum.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.HomeItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soten.genieforum.R
import com.soten.genieforum.adapter.ForumAdapter
import com.soten.genieforum.databinding.FragmentHomeBinding
import com.soten.genieforum.domain.model.Topic
import com.soten.genieforum.ui.select.ContentSelectActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val viewModel by viewModels<HomeViewModel>()

    private val binding get() = _binding!!

    private lateinit var forumAdapter: ForumAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        val age = requireActivity().intent.getStringExtra(ContentSelectActivity.KEY)
        if (age != null) {
            viewModel.fetch(age)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        bindView()
        observeData()
    }

    private fun initView() {
        binding.scrollTopImage.setOnClickListener {
            binding.forumRecyclerView.scrollToPosition(0)
        }
    }

    private fun bindView() {
        forumAdapter = ForumAdapter {
            val forum = it.copy()
            val bundle = bundleOf(
                KEY_FORUM_USERNAME to forum.userName,
                KEY_FORUM_TITLE to forum.title,
                KEY_FORUM_DESCRIPTION to forum.description,
                KEY_FORUM_ID to forum.id
            )

            findNavController().navigate(R.id.toDetailFragment, bundle)
        }
        binding.forumRecyclerView.adapter = forumAdapter
        binding.forumRecyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.forumRecyclerView.itemAnimator = HomeItemAnimator().apply { addDuration = 400 }
    }

    private fun observeData() {
        viewModel.forumLiveData.observe(viewLifecycleOwner) {
            forumAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val KEY_FORUM_USERNAME =  "name"
        const val KEY_FORUM_TITLE = "title"
        const val KEY_FORUM_DESCRIPTION = "description"
        const val KEY_FORUM_ID = "id"
    }

}