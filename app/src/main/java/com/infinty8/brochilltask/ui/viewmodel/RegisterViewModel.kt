package com.infinty8.brochilltask.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.infinty8.brochilltask.api.Resource
import com.infinty8.brochilltask.model.RegisterPostBody
import com.infinty8.brochilltask.remote.repository.RegisterRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/29/2022
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val signupRepository: RegisterRepo
) : ViewModel() {
    fun signUp(registerPostBody: RegisterPostBody) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = signupRepository.registerUser(registerPostBody)
            emit(Resource.success(data = data))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}