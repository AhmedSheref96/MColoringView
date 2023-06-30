package com.el3sas.mcoloringview.draws.image

import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.drawable.Drawable

/**
 * Created by seotm on 09.06.17.
 */
internal object ImageBoundsCreator {
    fun create(image: Drawable, viewWidth: Int, viewHeight: Int): Rect {
        val imageWidth = image.intrinsicWidth.toFloat()
        val imageHeight = image.intrinsicHeight.toFloat()
        return getRect(viewWidth, viewHeight, imageWidth, imageHeight, false)
    }

    @JvmStatic
    fun create(image: Bitmap, viewWidth: Int, viewHeight: Int): Rect {
        val imageWidth = image.width.toFloat()
        val imageHeight = image.height.toFloat()
        return getRect(viewWidth, viewHeight, imageWidth, imageHeight, true)
    }

    private fun getRect(
        viewWidth: Int,
        viewHeight: Int,
        imageWidth: Float,
        imageHeight: Float,
        allowGrown: Boolean
    ): Rect {
        val left: Int
        val top: Int
        val right: Int
        val bottom: Int
        if (imageWidth > viewWidth || imageHeight > viewHeight) {
            val ratio = Math.min(viewWidth / imageWidth, viewHeight / imageHeight)
            left = ((viewWidth - ratio * imageWidth) / 2).toInt()
            top = ((viewHeight - ratio * imageHeight) / 2).toInt()
            right = viewWidth - left
            bottom = viewHeight - top
        } else if (allowGrown && imageWidth < viewWidth && imageHeight < viewHeight) {
            val ratio = Math.min(viewWidth / imageWidth, viewHeight / imageHeight)
            left = ((viewWidth - ratio * imageWidth) / 2).toInt()
            top = ((viewHeight - ratio * imageHeight) / 2).toInt()
            right = viewWidth - left
            bottom = viewHeight - top
        } else {
            left = ((viewWidth - imageWidth) / 2).toInt()
            top = ((viewHeight - imageHeight) / 2).toInt()
            right = viewWidth - left
            bottom = viewHeight - top
        }
        return Rect(left, top, right, bottom)
    }
}