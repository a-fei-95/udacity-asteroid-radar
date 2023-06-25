package com.udacity.asteroidradar.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.main.viewholder.AsteroidListener
import com.udacity.asteroidradar.main.viewholder.AsteroidViewHolder

internal class AsteroidAdapter(
    private val clickListener: AsteroidListener
) : ListAdapter<Asteroid, ViewHolder>(AsteroidDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AsteroidViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val asteroidItem = getItem(position) as Asteroid
        (holder as AsteroidViewHolder).bind(asteroidItem, clickListener)
    }
}
