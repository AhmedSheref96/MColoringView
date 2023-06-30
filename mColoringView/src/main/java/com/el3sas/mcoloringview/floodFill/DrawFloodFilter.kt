package com.el3sas.mcoloringview.floodFill

import android.graphics.Bitmap
import android.graphics.Color
import com.el3sas.mcoloringview.draws.Position

/**
 * Created by seotm on 09.06.17.
 */
class DrawFloodFilter(private val position: Position) {
    fun draw(newColor: Int, bitmap: Bitmap?) {
        if (!position.valid || bitmap == null) return
        val targetColor = getTargetColor(bitmap)
        val floodFiller = QueueLinearFloodFiller(bitmap, targetColor, newColor)
        floodFiller.setTolerance(TOLERANCE)
        floodFiller.floodFill(position.x, position.y)
    }

    private fun getTargetColor(bitmap: Bitmap): Int {
        val pixel = bitmap.getPixel(position.x, position.y)
        val redValue = Color.red(pixel)
        val blueValue = Color.blue(pixel)
        val greenValue = Color.green(pixel)
        return Color.rgb(redValue, greenValue, blueValue)
    }

    companion object {
        private const val TOLERANCE = 100
    }
}