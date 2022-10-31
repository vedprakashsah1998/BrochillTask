package com.infinty8.brochilltask.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.infinty8.brochilltask.api.Resource
import com.infinty8.brochilltask.model.PostTweetModel
import com.infinty8.brochilltask.remote.repository.PostTweetRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/30/2022
 */
@HiltViewModel
class PostTweetViewModel @Inject constructor(
    private val postTweetRepo: PostTweetRepo,
) : ViewModel() {
    fun postTweet(appToken: String, postTweetModel: PostTweetModel) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val data = postTweetRepo.postTweetData(appToken, postTweetModel)
            if (data.code() == 400) {
                emit(Resource.error(data = null, message = "Tweet is required"))
            } else {
                emit(Resource.success(data = data))
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}