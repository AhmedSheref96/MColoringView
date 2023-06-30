package com.el3sas.mcoloringview.draws.image

import android.graphics.Bitmap
import com.el3sas.mcoloringview.draws.ViewSize
import com.el3sas.mcoloringview.draws.image.ImageBoundsCreator.create

/**
 * Created by seotm on 09.06.17.
 */
internal class SetupImageBounds(private val drawImage: DrawImageImpl) {
    fun setupImageBounds(viewSize: ViewSize) {
        if (!setupBoundFromDrawable(viewSize)) {
            setupBoundsFromBitmap(viewSize)
        }
    }

    private fun setupBoundFromDrawable(viewSize: ViewSize): Boolean {
        if (drawImage.drawableImg == null) return false
        if (!viewSize.isEstablished) {
            drawImage.drawableImg!!.setBounds(0, 0, 0, 0)
            return true
        }
        drawImage.bounds = create(drawImage.drawableImg!!, viewSize.width, viewSize.height)
        val drawableToBitmap = DrawableToBitmap(drawImage.drawableImg!!)
        val bitmap = drawableToBitmap.convertToBitmap(drawImage.bounds!!)
        drawImage.bitmapImg = BitmapHolder(bitmap, false)
        return true
    }

    private fun setupBoundsFromBitmap(viewSize: ViewSize) {
        if (drawImage.bitmapImg == null || !viewSize.isEstablished) return
        val oldBitmap = drawImage.bitmapImg!!.bitmap
        drawImage.bounds = create(oldBitmap, viewSize.width, viewSize.height)
        val bitmap = Bitmap.createScaledBitmap(
            oldBitmap,
            drawImage.bounds!!.width(),
            drawImage.bounds!!.height(),
            drawImage.bitmapImg!!.allowGrown
        )
        oldBitmap.recycle()
        drawImage.bitmapImg = BitmapHolder(bitmap, false)
    }
}