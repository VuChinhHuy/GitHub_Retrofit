package com.example.githubsearch.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubsearch.Model.ListModelFollowers
import com.example.githubsearch.R

class RCVAdapterFollowers(private val context: Context,private val list : List<ListModelFollowers>) : RecyclerView.Adapter<RCVAdapterFollowers.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userFollower = list[position]
        holder.bind(userFollower)

    }
    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val textUserFollower = view.findViewById<TextView>(R.id.txtViewItem)
        private val imageFollower = view.findViewById<ImageView>(R.id.imgAvtItem)

        fun bind (model:ListModelFollowers) {
            textUserFollower.text = model.login
            Glide.with(context).load(model.avatar_url).into(imageFollower)
        }

    }
}