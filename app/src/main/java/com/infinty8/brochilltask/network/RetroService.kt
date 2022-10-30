package com.infinty8.brochilltask.network

import com.infinty8.brochilltask.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RetroService {
    @POST("/register")
    suspend fun registerCall(
        @Body registerPostBody: RegisterPostBody,
    ): Response<RegisterModelResponse>

    @POST("/login")
    suspend fun loginUser(@Body loginPostModel: LoginPostModel): Response<RegisterModelResponse>

    @GET("/welcome")
    suspend fun welcomeUser(@Header("x-api-key") appToken: String): Response<WelcomeUserResponse>

    @GET("/tweets")
    suspend fun tweetsList(@Header("x-api-key") appToken: String): Response<MutableList<TweetResponse>>

    @POST("/tweets")
    suspend fun postTweet(
        @Header("x-api-key") appToken: String,
        @Body postTweetModel: PostTweetModel,
    ): Response<TweetResponse>
}