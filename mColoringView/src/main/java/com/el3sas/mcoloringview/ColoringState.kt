package com.el3sas.mcoloringview

import android.graphics.Bitmap

/**
 * Created by seotm on 12.06.17.
 */
class ColoringState internal constructor(view: ColoringView) {
    val bitmap: Bitmap?

    init {
        bitmap = view.drawImage.getImage()
    }

    fun restoreState(view: ColoringView) {
        if (bitmap != null) {
            view.drawImage.setStateImage(bitmap)
        }
    }
}