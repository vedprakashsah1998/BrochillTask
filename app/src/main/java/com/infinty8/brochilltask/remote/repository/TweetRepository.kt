package com.infinty8.brochilltask.remote.repository

import com.infinty8.brochilltask.model.TweetResponse
import com.infinty8.brochilltask.network.RetroService
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/30/2022
 */
class TweetRepository @Inject constructor(private val apiService: RetroService) : BaseRepository() {
    suspend fun tweetList(searchQuery: String): MutableList<TweetResponse>? {
        return safeApiCall(
            call = { apiService.tweetsList(searchQuery) },
            error = "Error getting Tweet List"
        )
    }
}