package com.udacity.asteroidradar.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)

        val selectedAsteroid = DetailFragmentArgs.fromBundle(requireArguments()).selectedAsteroid

        with(binding) {
            lifecycleOwner = this@DetailFragment
            asteroid = selectedAsteroid
            detailsImage.contentDescription = getString(
                when (selectedAsteroid.isPotentiallyHazardous) {
                    true -> R.string.details_image_potentially_hazardous_cd
                    false -> R.string.details_image_non_hazardous_cd
                }
            )
            helpButton.setOnClickListener {
                displayAstronomicalUnitExplanationDialog()
            }
        }

        return binding.root
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }
}
