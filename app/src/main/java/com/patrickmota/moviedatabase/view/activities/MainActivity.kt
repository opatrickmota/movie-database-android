package com.patrickmota.moviedatabase.view.activities

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.patrickmota.moviedatabase.R
import com.patrickmota.moviedatabase.view.adapters.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.patrickmota.moviedatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setTheme(R.style.Theme_MovieDatabase)
        setContentView(binding.root)

        searchMovie()

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, index ->
            tab.text = when (index) {
                0 -> {
                    "Now Playing"
                }
                1 -> {
                    "Coming Soon"
                }
                else -> throw Resources.NotFoundException("Position not found")
            }
        }.attach()
    }

    private fun searchMovie(){
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.search -> {
                    Toast.makeText(this, "Ainda não é possível buscar por filme", Toast.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }
    }
}