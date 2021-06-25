package com.example.github_retrofit.api_github

import com.example.github_retrofit.Model.UserModel
import com.example.githubsearch.Model.ListModelFollowers
import com.example.githubsearch.Model.ListModelItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GitHubDataRepository: GitHubRepository {
    private  var gitHubService : GitHubService? = null
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(getOkHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        gitHubService = retrofit.create(GitHubService::class.java)
    }

    private fun getOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =  HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
        val OkHttpClient = builder.build()
        return OkHttpClient
    }

    override suspend fun getUserDetail(userName: String): UserModel? {
        return gitHubService?.getUserDetail(userName = userName)
    }
    override suspend fun getListFollowerfromAPI(userName: String): List<ListModelFollowers>? {
        return gitHubService?.getListFollower(userName = userName)
    }

    override suspend fun getListFollowingfromAPI(userName: String): List<ListModelItem>? {
        return gitHubService?.getListFollowing(userName = userName)
    }
}