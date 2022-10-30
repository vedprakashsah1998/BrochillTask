package com.infinty8.brochilltask.model

import com.google.gson.annotations.SerializedName

/**
 * @author Ved Prakash
 * @created 10/30/2022
 */
data class PostTweetModel(
    @SerializedName("tweet") val tweet: String,
)