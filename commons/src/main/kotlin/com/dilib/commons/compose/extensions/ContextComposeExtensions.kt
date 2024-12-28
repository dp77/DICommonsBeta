package com.dilib.commons.compose.extensions

import android.app.Activity
import android.content.Context
import com.dilib.commons.R
import com.dilib.commons.extensions.baseConfig
import com.dilib.commons.extensions.redirectToRateUs
import com.dilib.commons.extensions.toast
import com.dilib.commons.helpers.BaseConfig

val Context.config: BaseConfig get() = BaseConfig.newInstance(applicationContext)

fun Activity.rateStarsRedirectAndThankYou(stars: Int) {
    if (stars == 5) {
        redirectToRateUs()
    }
    toast(R.string.thank_you)
    baseConfig.wasAppRated = true
}
