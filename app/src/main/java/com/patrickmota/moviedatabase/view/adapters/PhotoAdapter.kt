package com.patrickmota.moviedatabase.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.patrickmota.moviedatabase.R
import com.patrickmota.moviedatabase.databinding.ItemPhotoBinding
import com.patrickmota.moviedatabase.model.ImageResponse
import com.patrickmota.moviedatabase.model.Backdrop

class PhotoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var items: ImageResponse

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PhotoViewHolder -> {
                holder.bind(items.backdrops[position])
            }
        }
    }

    override fun getItemCount(): Int = items.backdrops.size

    class PhotoViewHolder(
        itemView: ItemPhotoBinding
    ) : RecyclerView.ViewHolder(itemView.root) {

        private val binding = itemView

        fun bind (backdrop: Backdrop){
            val bannerUrl = "https://image.tmdb.org/t/p/w500/" + backdrop.filePath

            val requestOptions = RequestOptions()
                .placeholder(R.color.gray)
                .error(R.color.gray)

            Glide.with(binding.root.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(bannerUrl)
                .fitCenter()
                .into(binding.poster)
        }
    }
}