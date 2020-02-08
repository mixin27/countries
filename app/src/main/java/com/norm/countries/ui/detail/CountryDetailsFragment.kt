package com.norm.countries.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.norm.countries.R
import com.norm.countries.databinding.FragmentCountryDetailsBinding
import com.norm.countries.utils.extensions.getViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class CountryDetailsFragment : Fragment() {

    private val args: CountryDetailsFragmentArgs by navArgs()

    private val detailsViewModel by viewModels<CountryDetailsViewModel> {
        getViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCountryDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_country_details,
            container,
            false
        )
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = detailsViewModel

        detailsViewModel.country.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                binding.tv.text = it.name
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        detailsViewModel.getCountry(args.selectedCountry.name)
    }

}
