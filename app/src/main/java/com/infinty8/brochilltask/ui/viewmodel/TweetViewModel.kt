package com.infinty8.brochilltask.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infinty8.brochilltask.api.NetworkResult
import com.infinty8.brochilltask.model.TweetResponse
import com.infinty8.brochilltask.remote.repository.TweetRepository
import com.infinty8.brochilltask.storage.AppPref
import com.infinty8.brochilltask.storage.PrefConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/30/2022
 */
@HiltViewModel
class TweetViewModel @Inject constructor(
    private val tweetRepository: TweetRepository,
    private val appPref: AppPref,
) : ViewModel() {

    val tweetListModel = MutableLiveData<MutableList<TweetResponse>?>()

    init {
        fetchAllTweets(appPref.getValue(PrefConstant.appTokenKey, "").toString())
    }

    private fun fetchAllTweets(appToken: String) = viewModelScope.launch {
        val tweet = tweetRepository.tweetList(appToken)
        tweetListModel.postValue(tweet)

    }
}