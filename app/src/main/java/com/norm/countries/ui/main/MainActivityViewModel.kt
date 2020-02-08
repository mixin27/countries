package com.norm.countries.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import com.norm.countries.ThemedActivityDelegate

class MainActivityViewModel(
    themedActivityDelegate: ThemedActivityDelegate
): ViewModel(), ThemedActivityDelegate by themedActivityDelegate {

}