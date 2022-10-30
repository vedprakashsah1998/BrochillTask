package com.infinty8.brochilltask.remote.datasource

import com.infinty8.brochilltask.model.PostTweetModel
import com.infinty8.brochilltask.network.RetroService
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/30/2022
 */
class PostTweetDataSource @Inject constructor(
    private val service: RetroService,
) {
    suspend fun postTweet(appToken: String, postTweetModel: PostTweetModel) =
        service.postTweet(appToken, postTweetModel)
}