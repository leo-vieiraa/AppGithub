package com.example.appgithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appgithub.view.RepoListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, RepoListFragment.newInstance())
            .commitNow()
    }
}