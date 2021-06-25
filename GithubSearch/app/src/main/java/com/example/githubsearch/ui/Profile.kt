package com.example.githubsearch.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.github_retrofit.api_github.GitHubDataRepository
import com.example.githubsearch.Model.ListModelFollowers
import com.example.githubsearch.Model.ListModelItem
import com.example.githubsearch.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Profile: Fragment() {
    private val githubDataRepository = GitHubDataRepository()
    private val list_Followers: ArrayList<ListModelFollowers> = ArrayList()
    private val list_Following: ArrayList<ListModelItem> = ArrayList()
    private lateinit var adapterFollower: RCVAdapterFollowers
    private lateinit var adapterFollowing: RCVAdapterFollowing

    companion object {
        fun newInstance() = Profile()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile,container,false)
        loadData(view)
        return view
    }
    private  fun loadData(view: View){
        CoroutineScope(Dispatchers.IO).launch {
            val userModel = githubDataRepository.getUserDetail("ToanMobile")
            withContext(Dispatchers.Main) {
                view.findViewById<TextView>(R.id.txtViewFullName).text = userModel?.name ?:""
                view.findViewById<TextView>(R.id.txtViewLogin).text = "@"+ userModel?.login ?:""
                view.findViewById<TextView>(R.id.txtViewFollowers).text = (userModel?.followers?.toString() ?:"") + "Followers"
                view.findViewById<TextView>(R.id.txtViewFollowing).text = (userModel?.following?.toString() ?:"") + "Following"
                view.findViewById<TextView>(R.id.txtViewRepos).text = (userModel?.public_repos?.toString() ?:"") + "Repos"
                view.findViewById<TextView>(R.id.txtViewBio).text = userModel?.bio?.toString() ?:""
                Glide.with(view).load(userModel?.avatar_url).into(view.findViewById(R.id.imgAvt))

            }
            val listFollowers = githubDataRepository.getListFollowerfromAPI("ToanMobile")!!
            withContext(Dispatchers.Main){
                adapterFollower = RCVAdapterFollowers(requireContext(),list_Followers)
                list_Followers.addAll(listFollowers)
                view.findViewById<RecyclerView>(R.id.rcvFollowers).layoutManager = LinearLayoutManager(requireContext())
                view.findViewById<RecyclerView>(R.id.rcvFollowers).adapter = adapterFollower
                    adapterFollower.notifyDataSetChanged()
            }

            val listFollowing = githubDataRepository.getListFollowingfromAPI("ToanMobile")!!
            withContext(Dispatchers.Main){
                adapterFollowing = RCVAdapterFollowing(requireContext(),list_Following)
                list_Following.addAll(listFollowing)
                view.findViewById<RecyclerView>(R.id.rcvFollowing).layoutManager = LinearLayoutManager(requireContext())
                view.findViewById<RecyclerView>(R.id.rcvFollowing).adapter = adapterFollowing
                adapterFollowing.notifyDataSetChanged()
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}