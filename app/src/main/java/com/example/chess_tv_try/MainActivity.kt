package com.example.chess_tv_try

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.chess_tv_try.fragments.FavoriteFragment
import com.example.chess_tv_try.fragments.MainPageFragment
import com.example.chess_tv_try.fragments.ProfileFragment
import com.example.chess_tv_try.fragments.StreamsFragment
import kotlinx.android.synthetic.main.activity_main.*

private val mainPageFragment = MainPageFragment()
private val streamsFragment = StreamsFragment()
private val favoriteFragment = FavoriteFragment()
private val profileFragment = ProfileFragment()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(mainPageFragment)
                R.id.stream -> replaceFragment(streamsFragment)
                R.id.favorite -> replaceFragment(favoriteFragment)
                R.id.profile -> replaceFragment(profileFragment)
            }
            true
        }
    }

    fun setActionBarTitle(title: String?) {
        supportActionBar!!.title = title
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}