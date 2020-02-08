package com.norm.countries.ui.all


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.norm.countries.R
import com.norm.countries.databinding.FragmentAllCountriesBinding
import com.norm.countries.utils.extensions.getViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class AllCountriesFragment : Fragment() {

    private val allViewModel by viewModels<AllViewModel> {
        getViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAllCountriesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_all_countries,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = allViewModel

        binding.rvAllCountries.adapter = AllCountriesAdapter(AllCountriesAdapter.OnClickListener {
            allViewModel.displayCountryDetails(it)
        })

        allViewModel.navigateToSelectedItem.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                this.findNavController().navigate(AllCountriesFragmentDirections.actionDestAllCountriesToCountryDetailsFragment(it))
                allViewModel.displayCountryDetailsComplete()
            }
        })

        return binding.root
    }


}
