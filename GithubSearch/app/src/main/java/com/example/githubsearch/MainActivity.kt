package com.example.githubsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.githubsearch.ui.Profile
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnSearch).setOnClickListener{
            supportFragmentManager.beginTransaction().replace(R.id.fragment_result_search,Profile.newInstance()).commit()
        }
    }
}