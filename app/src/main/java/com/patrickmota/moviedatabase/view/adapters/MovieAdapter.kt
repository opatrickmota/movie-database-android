package com.patrickmota.moviedatabase.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.patrickmota.moviedatabase.R
import com.patrickmota.moviedatabase.databinding.ItemMovieBinding
import com.patrickmota.moviedatabase.model.Movie

class MovieAdapter(private val onItemClicked : (Movie) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                holder.bind(items[position], onItemClicked)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setDataSet(movies: List<Movie>){
        this.items = movies
    }

    class MovieViewHolder(
        itemView: ItemMovieBinding
    ) : RecyclerView.ViewHolder(itemView.root) {
        private lateinit var movie: Movie
        private val binding = itemView

        fun bind(movie: Movie, onItemClicked: (Movie) -> Unit) {
            this.movie = movie

            binding.title.text = movie.title
            binding.date.text = movie.releaseDate
            binding.genre.text = movie.genres?.get(0)?.name
            binding.popularity.text = movie.popularity.toString()

            val bannerUrl = "https://image.tmdb.org/t/p/w500/" + movie.posterPath

            val requestOptions = RequestOptions()
                .placeholder(R.color.gray)
                .error(R.color.gray)

            Glide.with(binding.root.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(bannerUrl)
                .fitCenter()
                .into(binding.image)

            binding.root.setOnClickListener{
                onItemClicked(movie)
            }
        }
    }

}