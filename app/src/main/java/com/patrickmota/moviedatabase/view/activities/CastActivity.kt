package com.patrickmota.moviedatabase.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.patrickmota.moviedatabase.R
import com.patrickmota.moviedatabase.databinding.ActivityCastBinding
import com.patrickmota.moviedatabase.view.fragments.CreditFragment

class CastActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCastBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_MovieDatabase)
        setContentView(binding.root)

        val extras: Bundle? = intent.extras
        val movieId = extras?.get("movieId")

        backToPreviousScreen()
        loadCastList(movieId.toString())
    }

    private fun backToPreviousScreen() {
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun loadCastList(movieId: String){
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, CreditFragment(movieId, true))
            .commit()
    }
}