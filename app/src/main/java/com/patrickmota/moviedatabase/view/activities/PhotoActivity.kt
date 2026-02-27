package com.patrickmota.moviedatabase.view.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patrickmota.moviedatabase.R
import com.patrickmota.moviedatabase.databinding.ActivityPhotoBinding
import com.patrickmota.moviedatabase.view.adapters.PhotoAdapter
import com.patrickmota.moviedatabase.view.fragments.ConnectionErrorFragment
import com.patrickmota.moviedatabase.viewmodel.activities.PhotoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoActivity : AppCompatActivity() {

    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var binding: ActivityPhotoBinding

    private val photoViewModel: PhotoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_MovieDatabase)
        setContentView(binding.root)

        val extras: Bundle? = intent.extras
        val movieId = extras?.get("movieId")

        backToPreviousScreen()
        initUi(movieId.toString())
    }

    private fun backToPreviousScreen() {
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initUi(movieId: String) {
        photoViewModel.getImage(movieId)
        photoViewModel.imageResponse().observe(this) {

            val recyclerView: RecyclerView = binding.recyclerviewPhoto

            photoAdapter = PhotoAdapter()

            photoAdapter.items = it

            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = photoAdapter
            }
        }

        photoViewModel.error().observe(this){
            showConnectionError()
        }
    }

    private fun showConnectionError() {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentError, ConnectionErrorFragment())
            .commit()

        binding.recyclerviewPhoto.visibility = View.GONE

    }
}