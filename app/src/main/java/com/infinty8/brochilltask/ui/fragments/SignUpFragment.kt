package com.infinty8.brochilltask.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.infinty8.brochilltask.api.Status
import com.infinty8.brochilltask.databinding.FragmentSignUpFragmentBinding
import com.infinty8.brochilltask.model.RegisterPostBody
import com.infinty8.brochilltask.storage.AppPref
import com.infinty8.brochilltask.storage.PrefConstant
import com.infinty8.brochilltask.ui.activity.HomeScreen
import com.infinty8.brochilltask.ui.viewmodel.RegisterViewModel
import com.infinty8.brochilltask.utils.SnackbarUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragments : Fragment() {
    private lateinit var binding: FragmentSignUpFragmentBinding
    private val viewModel: RegisterViewModel by viewModels()

    @Inject
    lateinit var appPref: AppPref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpFragmentBinding.inflate(layoutInflater, container, false)

        binding.signUpBtn.setOnClickListener {
            signUp()
        }

        return binding.root
    }

    private fun signUp() = viewModel.signUp(
        RegisterPostBody(
            binding.outlinedEditTextFirstName.text.toString(),
            binding.outlinedEditTextLastName.text.toString(),
            binding.outlinedEditTextEmail.text.toString(),
            binding.outlinedEditTextPwd.text.toString()
        )
    ).observe(viewLifecycleOwner) {
        when (it.status) {
            Status.SUCCESS -> {
                binding.progressBarBase.progressBar.visibility = View.GONE
                appPref.setValue(PrefConstant.appTokenKey, it.data?.body()?.token.toString())
                startActivity(Intent(requireContext(), HomeScreen::class.java))
                requireActivity().finish()
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