package com.udacity.asteroidradar.main.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.databinding.ListItemAsteroidBinding

internal class AsteroidViewHolder(
    private val binding: ListItemAsteroidBinding
) : ViewHolder(binding.root) {

    fun bind(item: Asteroid, clickListener: AsteroidListener) {
        binding.asteroid = item
        binding.clickListener = clickListener
        binding.asteroidIsHazardousIv.setImageResource(
            when {
                item.isPotentiallyHazardous -> R.drawable.ic_status_potentially_hazardous
                else -> R.drawable.ic_status_normal
            }
        )
    }

    companion object {
        fun from(parent: ViewGroup): AsteroidViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemAsteroidBinding.inflate(layoutInflater, parent, false)

            return AsteroidViewHolder(binding)
        }
    }
}
