package com.example.netblinkapp

import ProfilFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.netblinkapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        replaceFragment(HomeFragment())

        activityMainBinding.bottomNavigation.setOnItemSelectedListener {

            when(it.itemId){

                R.id.navigation_home -> replaceFragment(HomeFragment())
                R.id.navigation_chat -> replaceFragment(ChatFragment())
                R.id.navigation_profile -> replaceFragment(ProfilFragment())

                else ->{
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: HomeFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_container,fragment)
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: ChatFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_container,fragment)
        fragmentTransaction.commit()
    }

    private fun replaceFragment(fragment: ProfilFragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_container,fragment)
        fragmentTransaction.commit()
    }
}