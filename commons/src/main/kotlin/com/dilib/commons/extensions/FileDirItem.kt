package com.dilib.commons.extensions

import android.content.Context
import com.dilib.commons.models.FileDirItem

fun FileDirItem.isRecycleBinPath(context: Context): Boolean {
    return path.startsWith(context.recycleBinPath)
}
