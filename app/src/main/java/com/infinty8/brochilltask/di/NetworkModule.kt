package com.infinty8.brochilltask.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.infinty8.brochilltask.BuildConfig.BASE_URL
import com.infinty8.brochilltask.network.RetroService
import com.infinty8.brochilltask.remote.datasource.LoginDataSource
import com.infinty8.brochilltask.remote.datasource.PostTweetDataSource
import com.infinty8.brochilltask.remote.datasource.RegisterDataSource
import com.infinty8.brochilltask.remote.datasource.WelcomeDataSource
import com.infinty8.brochilltask.remote.repository.LoginRepo
import com.infinty8.brochilltask.remote.repository.PostTweetRepo
import com.infinty8.brochilltask.remote.repository.RegisterRepo
import com.infinty8.brochilltask.remote.repository.WelcomeUserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit.Builder): RetroService = retrofit
        .build()
        .create(RetroService::class.java)

    /**
     * Datasource of app in module
     */
    @Provides
    fun provideRemoteDataSource(service: RetroService) =
        RegisterDataSource(service)

    @Provides
    fun provideRemoteDataSourceLogin(service: RetroService) = LoginDataSource(service)

    @Provides
    fun providesRemoteDataSourceWelcome(service: RetroService) = WelcomeDataSource(service)

    @Provides
    fun providesRemoteDataSourcePostTweet(service: RetroService) = PostTweetDataSource(service)

    /**
     * Repository of app in module
     */
    @Provides
    fun provideSignupRepository(
        signupDataSource: RegisterDataSource,
    ) = RegisterRepo(signupDataSource)

    @Provides
    fun providesLoginRepo(loginDataSource: LoginDataSource) = LoginRepo(loginDataSource)

    @Provides
    fun providesWelcomeRepo(welcomeDataSource: WelcomeDataSource) =
        WelcomeUserRepo(welcomeDataSource)

    @Provides
    fun providesTweetRepo(postTweetDataSource: PostTweetDataSource) =
        PostTweetRepo(postTweetDataSource)

}