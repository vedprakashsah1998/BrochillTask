package com.infinty8.brochilltask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.infinty8.brochilltask.R
import com.infinty8.brochilltask.databinding.FragmentTweetListBinding
import com.infinty8.brochilltask.storage.AppPref
import com.infinty8.brochilltask.ui.adapter.FetchTweetAdapter
import com.infinty8.brochilltask.ui.viewmodel.TweetViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TweetList : Fragment() {

    private lateinit var binding: FragmentTweetListBinding
    private lateinit var tweetAdapter: FetchTweetAdapter
    private val viewModel: TweetViewModel by viewModels()

    @Inject
    lateinit var appPref: AppPref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTweetListBinding.inflate(layoutInflater, container, false)
        tweetListStatus()
        setUpRecyclerView()
        binding.addTweet.setOnClickListener {
            it.findNavController().navigate(R.id.action_tweetList_to_postTweet)
        }
        return binding.root
    }

    private fun setUpRecyclerView() {
        tweetAdapter = FetchTweetAdapter(requireContext())
        binding.tweetRv.apply {
            adapter = tweetAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


    private fun tweetListStatus() = viewModel.tweetListModel.observe(viewLifecycleOwner) {
        tweetAdapter.differ.submitList(it)
    }
}