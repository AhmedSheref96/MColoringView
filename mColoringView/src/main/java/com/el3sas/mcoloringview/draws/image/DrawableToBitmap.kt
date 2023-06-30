package com.el3sas.mcoloringview.draws.image

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable

/**
 * Created by seotm on 09.06.17.
 */
class DrawableToBitmap(private val drawable: Drawable) {
    fun convertToBitmap(bounds: Rect): Bitmap {
        val bitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.RGB_565)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}