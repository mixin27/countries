package com.norm.countries.ui.main

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.norm.countries.R
import com.norm.countries.ThemedActivityDelegateImpl
import com.norm.countries.data.SharedPreferenceStorage
import com.norm.countries.databinding.ActivityMainBinding
import com.norm.countries.domain.settings.ObserveThemeModeUseCase
import com.norm.countries.utils.ViewModelFactory
import com.norm.countries.utils.extensions.updateForTheme
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val preferenceStorage = SharedPreferenceStorage(this)
    private val observeThemeModeUseCase = ObserveThemeModeUseCase(preferenceStorage, Dispatchers.Default)
    private val themedActivityDelegate = ThemedActivityDelegateImpl(observeThemeModeUseCase)
    private val viewModelFactory = MainViewModelFactory(themedActivityDelegate)
    private val viewModel by viewModels<MainActivityViewModel> {
        viewModelFactory
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.theme.observe(this, Observer(::updateForTheme))

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navController = this.findNavController(R.id.mainNavHost)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = this.findNavController(R.id.mainNavHost)
        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.mainNavHost)
        return navController.navigateUp()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        when (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                Timber.i("Night mode is not active, we're using the light theme")
            } // Night mode is not active, we're using the light theme
            Configuration.UI_MODE_NIGHT_YES -> {
                Timber.i("Night mode is active, we're using dark theme")
            } // Night mode is active, we're using dark theme
        }
        super.onConfigurationChanged(newConfig)
    }
}
