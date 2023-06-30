package com.el3sas.mcoloringview

import android.graphics.Bitmap
import com.el3sas.mcoloringview.draws.Position

/**
 * Created by seotm on 27.07.17.
 */
internal class ColorValidation(private val enableColoringBlackColor: Boolean) {
    fun isValidPosition(position: Position, image: Bitmap?): Boolean {
        if (image == null || !position.valid) {
            return false
        }
        if (enableColoringBlackColor) return true
        val pixelColor = image.getPixel(position.x, position.y)
        val r = pixelColor shr 16 and 0xFF
        val g = pixelColor shr 8 and 0xFF
        val b = pixelColor and 0xFF
        return r > MAX_BLACK_TONE_COLOR || g > MAX_BLACK_TONE_COLOR || b > MAX_BLACK_TONE_COLOR
    }

    companion object {
        private const val MAX_BLACK_TONE_COLOR = 0x22
    }
}