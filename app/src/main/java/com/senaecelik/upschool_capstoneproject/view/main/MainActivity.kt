package com.senaecelik.upschool_capstoneproject.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.senaecelik.upschool_capstoneproject.R
import com.senaecelik.upschool_capstoneproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityMainBinding
  //  private lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.lifecycleOwner = this
        dataBinding.mainActivity = this


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView= dataBinding.bottomNavigationView
        setupWithNavController(bottomNavigationView, navController)

        dataBinding.homeFloatButton.setOnClickListener {
            navController.navigateUp() // to clear previous navigation history
            navController.navigate(R.id.homeFragment)
        }


    }


}
