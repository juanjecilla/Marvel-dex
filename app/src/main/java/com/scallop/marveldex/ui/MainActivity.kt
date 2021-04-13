package com.scallop.marveldex.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.scallop.marveldex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mNavHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setUpNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(mBinding.navHostFragment.id)
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }

    private fun setUpNavigation() {
        with(mBinding) {
            mNavHostFragment = supportFragmentManager
                .findFragmentById(navHostFragment.id) as NavHostFragment

            setSupportActionBar(toolbar)
            setupActionBarWithNavController(mNavHostFragment.navController)
        }
    }
}