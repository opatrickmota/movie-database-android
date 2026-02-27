package com.patrickmota.moviedatabase.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.patrickmota.moviedatabase.databinding.ItemCreditBinding
import com.patrickmota.moviedatabase.model.CreditResponse
import com.patrickmota.moviedatabase.model.Cast

class CreditAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var items: CreditResponse

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemCreditBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CreditsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CreditsViewHolder -> {
                holder.bind(items.cast[position])
            }
        }
    }

    override fun getItemCount(): Int = items.cast.size

    class CreditsViewHolder(
        itemView: ItemCreditBinding
    ) : RecyclerView.ViewHolder(itemView.root) {
        private lateinit var cast: Cast
        private val binding = itemView

        fun bind(cast: Cast) {
            this.cast = cast

            binding.castName.text = cast.name
            binding.castPaperName.text = cast.character

            val bannerUrl = "https://image.tmdb.org/t/p/w500" + cast.profilePath

            Glide.with(binding.root.context)
                .load(bannerUrl)
                .fitCenter()
                .into(binding.castImage)
        }
    }

}