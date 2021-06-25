package com.example.github_retrofit.api_github

import com.example.github_retrofit.Model.UserModel
import com.example.githubsearch.Model.ListModelFollowers
import com.example.githubsearch.Model.ListModelItem
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubService {
    //-User
    @GET("/users/{userName}")
    suspend fun getUserDetail(@Path("userName") userName : String?): UserModel?

    //-Follower
    @GET("/users/{userName}/followers")
    suspend fun getListFollower(@Path("userName") userName : String?): List<ListModelFollowers>?

    //-Following
    @GET("/users/{userName}/following")
    suspend fun getListFollowing(@Path("userName") userName : String?): List<ListModelItem>?
}