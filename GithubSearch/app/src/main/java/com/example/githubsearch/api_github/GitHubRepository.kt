package com.example.github_retrofit.api_github

import com.example.github_retrofit.Model.UserModel
import com.example.githubsearch.Model.ListModelFollowers
import com.example.githubsearch.Model.ListModelItem

interface GitHubRepository {
    suspend fun getUserDetail(userName:String):UserModel?
    //Follower
    suspend fun getListFollowerfromAPI(userName : String) : List<ListModelFollowers>?

    //Following
    suspend fun getListFollowingfromAPI(userName : String) : List<ListModelItem>?
}