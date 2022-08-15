package com.example.lesttalk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.lesttalk.databasse.TalkDatabase
import com.example.lesttalk.databasse.TalkDoa
import com.example.lesttalk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initFragment()
    }

    private fun initFragment() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_controler) as NavHostFragment
        navHostFragment.navController
    }
}