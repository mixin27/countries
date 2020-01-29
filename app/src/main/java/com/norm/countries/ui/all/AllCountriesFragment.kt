package com.norm.countries.ui.all


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.norm.countries.R
import com.norm.countries.databinding.FragmentAllCountriesBinding

/**
 * A simple [Fragment] subclass.
 */
class AllCountriesFragment : Fragment() {

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

        val application = requireNotNull(this.activity).application
        val allViewModelFactory = AllViewModelFactory(application)
        val allViewModel = ViewModelProvider(viewModelStore, allViewModelFactory).get(AllViewModel::class.java)
        binding.viewModel = allViewModel

        binding.rvAllCountries.adapter = AllCountriesAdapter(AllCountriesAdapter.OnClickListener {

        })

        return binding.root
    }


}
