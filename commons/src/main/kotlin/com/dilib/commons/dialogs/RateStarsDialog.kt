package com.dilib.commons.dialogs

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dilib.commons.R
import com.dilib.commons.compose.alert_dialog.AlertDialogState
import com.dilib.commons.compose.alert_dialog.rememberAlertDialogState
import com.dilib.commons.compose.extensions.MyDevices
import com.dilib.commons.compose.theme.AppThemeSurface
import com.dilib.commons.compose.theme.Shapes
import com.dilib.commons.compose.theme.SimpleTheme
import com.dilib.commons.databinding.DialogRateStarsBinding
import com.dilib.commons.extensions.*

class RateStarsDialog(val activity: Activity) {
    private var dialog: AlertDialog? = null

    init {
        val view = DialogRateStarsBinding.inflate(activity.layoutInflater, null, false).apply {
            val primaryColor = activity.getProperPrimaryColor()
            arrayOf(rateStar1, rateStar2, rateStar3, rateStar4, rateStar5).forEach {
                it.applyColorFilter(primaryColor)
            }

            rateStar1.setOnClickListener { dialogCancelled(true) }
            rateStar2.setOnClickListener { dialogCancelled(true) }
            rateStar3.setOnClickListener { dialogCancelled(true) }
            rateStar4.setOnClickListener { dialogCancelled(true) }
            rateStar5.setOnClickListener {
                activity.redirectToRateUs()
                dialogCancelled(true)
            }
        }

        activity.getAlertDialogBuilder()
            .setNegativeButton(R.string.later) { _, _ -> dialogCancelled(false) }
            .setOnCancelListener { dialogCancelled(false) }
            .apply {
                activity.setupDialogStuff(view.root, this, cancelOnTouchOutside = false) { alertDialog ->
                    dialog = alertDialog
                }
            }
    }

    private fun dialogCancelled(showThankYou: Boolean) {
        dialog?.dismiss()
        if (showThankYou) {
            activity.toast(R.string.thank_you)
            activity.baseConfig.wasAppRated = true
        }
    }
}

@Composable
fun RateStarsAlertDialog(
    alertDialogState: AlertDialogState,
    modifier: Modifier = Modifier,
    onRating: (stars: Int) -> Unit
) {

}

@Composable
private fun StarRating(
    modifier: Modifier = Modifier,
    maxRating: Int = 5,
    currentRating: Int,
    onRatingChanged: (Int) -> Unit,
    starsColor: Color = SimpleTheme.colorScheme.primary,
) {
    val animatedRating by animateIntAsState(
        targetValue = currentRating,
        label = "animatedRating",
        animationSpec = tween()
    )
    Row(modifier) {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= animatedRating) Icons.Filled.Star
                else Icons.Filled.StarOutline,
                contentDescription = null,
                tint = starsColor,
                modifier = Modifier
                    .size(48.dp)
                    .clip(shape = Shapes.large)
                    .clickable { onRatingChanged(i) }
                    .padding(4.dp)
            )
        }
    }
}

@Composable
@MyDevices
private fun RateStarsAlertDialogPreview() {
    AppThemeSurface {
        RateStarsAlertDialog(alertDialogState = rememberAlertDialogState(), onRating = {})
    }
}
