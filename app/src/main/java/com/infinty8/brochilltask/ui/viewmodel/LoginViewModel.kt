package com.infinty8.brochilltask.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.infinty8.brochilltask.api.Resource
import com.infinty8.brochilltask.model.LoginPostModel
import com.infinty8.brochilltask.remote.repository.LoginRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/29/2022
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepo: LoginRepo,
) : ViewModel() {

    fun login(loginPostModel: LoginPostModel) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = loginRepo.loginUser(loginPostModel)
            if (data.code() == 400) {
                emit(Resource.error(data = null, message = "Invalid Credentials"))
            } else {
                emit(Resource.success(data = data))

            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}