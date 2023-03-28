package com.android.rickandmorty.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.android.rickandmorty.R
import com.android.rickandmorty.databinding.ActivityMainBinding
import com.android.navigation.NavigateToFlow
import com.android.navigation.NavigationFlow
import com.android.navigation.Navigator

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigateToFlow {

    private val navigator: Navigator = Navigator()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.setGraph(com.android.navigation.R.navigation.main_nav_graph)
        navigator.navController = navController
    }

    override fun navigateToFlow(flow: NavigationFlow) {
        navigator.navigateToFlow(flow)
    }
}