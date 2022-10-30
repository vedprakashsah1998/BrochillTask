package com.infinty8.brochilltask.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.infinty8.brochilltask.R
import com.infinty8.brochilltask.api.Status
import com.infinty8.brochilltask.databinding.FragmentPostTweetBinding
import com.infinty8.brochilltask.model.PostTweetModel
import com.infinty8.brochilltask.storage.AppPref
import com.infinty8.brochilltask.storage.PrefConstant
import com.infinty8.brochilltask.ui.viewmodel.PostTweetViewModel
import com.infinty8.brochilltask.utils.SnackbarUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostTweet : Fragment() {

    private val viewModel: PostTweetViewModel by viewModels()

    @Inject
    lateinit var appPref: AppPref

    private lateinit var binding: FragmentPostTweetBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPostTweetBinding.inflate(layoutInflater, container, false)
        binding.postTweetBtn.setOnClickListener {
            postTweet(it)
        }


        return binding.root
    }

    private fun postTweet(view: View) {
        val response =
            viewModel.postTweet(appPref.getValue(PrefConstant.appTokenKey, "").toString(),
                PostTweetModel(binding.outlinedEditTextAddTweet.text.toString()))

        response.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBarBase.progressBar.visibility = View.GONE
                    view.findNavController().navigate(R.id.action_postTweet_to_tweetList)

                }
                Status.ERROR -> {
                    binding.progressBarBase.progressBar.visibility = View.GONE
                    SnackbarUtils.showMessage(binding.root, it.message.toString())
                }
                Status.LOADING -> {
                    binding.progressBarBase.progressBar.visibility = View.VISIBLE
                }
            }
        }


    }

}