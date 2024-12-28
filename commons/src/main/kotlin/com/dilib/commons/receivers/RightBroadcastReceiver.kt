package com.dilib.commons.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dilib.commons.extensions.syncGlobalConfig
import com.dilib.commons.helpers.MyContentProvider

class RightBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == MyContentProvider.ACTION_GLOBAL_CONFIG_UPDATED) {
            context?.syncGlobalConfig()
        }
    }
}
