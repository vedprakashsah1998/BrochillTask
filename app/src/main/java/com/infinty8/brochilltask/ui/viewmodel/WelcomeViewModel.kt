package com.infinty8.brochilltask.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.infinty8.brochilltask.api.Resource
import com.infinty8.brochilltask.remote.repository.WelcomeUserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/29/2022
 */

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val welcomeUserRepo: WelcomeUserRepo,
) : ViewModel() {
    fun welcomeUser(appToken: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = welcomeUserRepo.welcomeUser(appToken)
            emit(Resource.success(data = data))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}