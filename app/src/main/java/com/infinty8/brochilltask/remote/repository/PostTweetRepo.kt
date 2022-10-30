package com.infinty8.brochilltask.remote.repository

import com.infinty8.brochilltask.model.PostTweetModel
import com.infinty8.brochilltask.remote.datasource.PostTweetDataSource
import javax.inject.Inject

/**
 * @author Ved Prakash
 * @created 10/30/2022
 */
class PostTweetRepo @Inject constructor(
    private val postTweetDataSource: PostTweetDataSource,
) {
    suspend fun postTweetData(appToken: String, postTweetModel: PostTweetModel) =
        postTweetDataSource.postTweet(appToken, postTweetModel)
}