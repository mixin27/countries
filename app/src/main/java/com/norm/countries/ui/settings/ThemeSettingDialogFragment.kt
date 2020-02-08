package com.norm.countries.ui.settings

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.norm.countries.R
import com.norm.countries.utils.extensions.getViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

class ThemeSettingDialogFragment: AppCompatDialogFragment() {

    private lateinit var listAdapter: ArrayAdapter<ThemeHolder>

    private val viewModel by viewModels<SettingsViewModel> {
        getViewModelFactory()
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        listAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_single_choice)

        return MaterialAlertDialogBuilder(context)
            .setTitle(R.string.settings_theme_title)
            .setSingleChoiceItems(listAdapter, 0) { dialog, position ->
                val theme = requireNotNull(listAdapter.getItem(position)).theme
                viewModel.setTheme(theme)
                dialog.dismiss()
            }
            .create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.availableThemes.observe(this, Observer { themes ->
            listAdapter.clear()
            listAdapter.addAll(themes.map {
                ThemeHolder(it, getTitleForTheme(it))
            })
            updateSelectedItem(viewModel.theme.value)
        })

        viewModel.theme.observe(this, Observer(::updateSelectedItem))
    }

    private fun updateSelectedItem(selected: Theme?) {
        val selectedPosition = (0 until listAdapter.count).indexOfFirst { index ->
            listAdapter.getItem(index)?.theme == selected
        }
        (dialog as AlertDialog).listView.setItemChecked(selectedPosition, true)
    }

    private fun getTitleForTheme(theme: Theme) = when (theme) {
        Theme.LIGHT -> getString(R.string.settings_theme_light)
        Theme.DARK -> getString(R.string.settings_theme_dark)
        Theme.SYSTEM -> getString(R.string.settings_theme_system)
        Theme.BATTERY_SAVER -> getString(R.string.settings_theme_battery)
    }

    companion object {
        fun newInstance() = ThemeSettingDialogFragment()
    }

    private data class ThemeHolder(val theme: Theme, val title: String) {
        override fun toString(): String = title
    }
}