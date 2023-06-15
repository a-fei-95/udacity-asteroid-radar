package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.main.adapter.AsteroidAdapter
import com.udacity.asteroidradar.main.adapter.AsteroidListener

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var asteroidListener: AsteroidListener
    private val asteroidAdapter by lazy { AsteroidAdapter(asteroidListener) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)

        with(binding) {
            lifecycleOwner = this@MainFragment
            viewModel = this@MainFragment.viewModel
            asteroidRecycler.adapter = asteroidAdapter
        }

        asteroidListener = AsteroidListener { asteroid ->
            viewModel.onAsteroidClicked(asteroid)
        }

        // TODO: test data
        asteroidAdapter.submitList(viewModel.asteroids)

        viewModel.navigateToAsteroidDetail.observe(viewLifecycleOwner) { asteroid ->
            asteroid?.let {
                findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroid))
                viewModel.onAsteroidDetailsNavigated()
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
