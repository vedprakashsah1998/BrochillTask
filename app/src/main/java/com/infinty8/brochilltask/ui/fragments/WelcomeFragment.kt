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
import com.infinty8.brochilltask.databinding.FragmentWelcomeBinding
import com.infinty8.brochilltask.storage.AppPref
import com.infinty8.brochilltask.storage.PrefConstant
import com.infinty8.brochilltask.ui.viewmodel.WelcomeViewModel
import com.infinty8.brochilltask.utils.SnackbarUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WelcomeFragment : Fragment() {


    private lateinit var binding: FragmentWelcomeBinding
    private val viewModel: WelcomeViewModel by viewModels()

    @Inject
    lateinit var appPref: AppPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(layoutInflater, container, false)
        binding.startBtnAction.setOnClickListener {
            if (appPref.getValue(PrefConstant.appTokenKey, "").isNullOrEmpty() || appPref.getValue(
                    PrefConstant.appTokenKey,
                    "").isNullOrBlank()
            ) {
                it.findNavController().navigate(R.id.action_welcomeFragment_to_signUpFragments)

            } else {
                welcomeUser(it)
            }
        }

        return binding.root
    }

    private fun welcomeUser(view: View) =
        viewModel.welcomeUser(appPref.getValue(PrefConstant.appTokenKey, "").toString())
            .observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.LOADING -> {
                        binding.progressBarBase.progressBar.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        binding.progressBarBase.progressBar.visibility = View.GONE

                        view.findNavController()
                            .navigate(R.id.action_welcomeFragment_to_loginFragment)

                    }
                    Status.ERROR -> {
                        binding.progressBarBase.progressBar.visibility = View.GONE
                        SnackbarUtils.showMessage(binding.root, it.message.toString())

                    }
                }
            }

}