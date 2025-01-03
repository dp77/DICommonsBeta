package com.dilib.commons.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import com.dilib.commons.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.dilib.commons.extensions.applyColorFilter
import com.dilib.commons.extensions.baseConfig
import com.dilib.commons.extensions.getContrastColor
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel

class MyFloatingActionButton : FloatingActionButton {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    fun setColors(textColor: Int, accentColor: Int, backgroundColor: Int) {
        backgroundTintList = ColorStateList.valueOf(accentColor)
        applyColorFilter(accentColor.getContrastColor())

        if (!context.baseConfig.materialDesign3) {
            val shapeAppearance = ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(CornerFamily.ROUNDED, context.resources.getDimension(R.dimen.material2_corners))
                .build()
            shapeAppearanceModel = shapeAppearance
        }
    }
}
