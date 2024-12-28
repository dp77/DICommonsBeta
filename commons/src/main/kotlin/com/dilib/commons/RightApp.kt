package com.dilib.commons

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.dilib.commons.extensions.appLockManager
import com.dilib.commons.extensions.checkUseEnglish

open class RightApp : Application() {

    open val isAppLockFeatureAvailable = false

    override fun onCreate() {
        super.onCreate()
        checkUseEnglish()
        setupAppLockManager()
    }

    private fun setupAppLockManager() {
        if (isAppLockFeatureAvailable) {
            ProcessLifecycleOwner.get().lifecycle.addObserver(appLockManager)
        }
    }
}