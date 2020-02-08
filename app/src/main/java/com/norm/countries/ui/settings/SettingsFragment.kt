package com.norm.countries.ui.settings


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.norm.countries.R
import com.norm.countries.databinding.FragmentSettingsBinding
import com.norm.countries.utils.extensions.getViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    private val settingsViewModel by viewModels<SettingsViewModel> {
        getViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSettingsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_settings,
            container,
            false
        )
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = settingsViewModel

        settingsViewModel.navigateToThemeSelector.observe(viewLifecycleOwner, Observer {
            ThemeSettingDialogFragment.newInstance().show(parentFragmentManager, null)
        })

        return binding.root
    }


}
