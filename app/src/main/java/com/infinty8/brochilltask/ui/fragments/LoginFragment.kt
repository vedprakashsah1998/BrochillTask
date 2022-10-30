package com.infinty8.brochilltask.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.infinty8.brochilltask.api.Status
import com.infinty8.brochilltask.databinding.FragmentLoginBinding
import com.infinty8.brochilltask.model.LoginPostModel
import com.infinty8.brochilltask.storage.AppPref
import com.infinty8.brochilltask.storage.PrefConstant
import com.infinty8.brochilltask.ui.activity.HomeScreen
import com.infinty8.brochilltask.ui.viewmodel.LoginViewModel
import com.infinty8.brochilltask.utils.SnackbarUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var appPref: AppPref
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        binding.ctaLoginBtn.setOnClickListener {
            loginUser(binding.outlinedEditTextEmail.text.toString(),
                binding.outlinedEditTextPwd.text.toString())
        }
        return binding.root
    }

    private fun loginUser(email: String, password: String) =
        viewModel.login(LoginPostModel(email, password)).observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("statusCode:","codeRes: SUCCESS"+it.message)
                    binding.progressBarBase.progressBar.visibility = View.GONE
                    appPref.setValue(PrefConstant.appTokenKey, it.data?.body()?.token.toString())
                    startActivity(Intent(requireContext(), HomeScreen::class.java))

                }
                Status.ERROR -> {
                    Log.d("statusCode:","codeRes: ERROR"+it.message)

                    binding.progressBarBase.progressBar.visibility = View.GONE
                    SnackbarUtils.showMessage(binding.root, it.message.toString())

                }
                Status.LOADING -> {
                    binding.progressBarBase.progressBar.visibility = View.VISIBLE

                }
            }
        }

}