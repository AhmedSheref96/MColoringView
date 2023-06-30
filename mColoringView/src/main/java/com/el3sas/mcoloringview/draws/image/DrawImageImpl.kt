package com.el3sas.mcoloringview.draws.image

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import com.el3sas.mcoloringview.draws.Position
import com.el3sas.mcoloringview.draws.ViewSize

/**
 * Created by seotm on 08.06.17.
 */
class DrawImageImpl : DrawImage {
    @JvmField
    var drawableImg: Drawable? = null
    @JvmField
    var bitmapImg: BitmapHolder? = null
    @JvmField
    var bounds: Rect? = null
    private val viewSize = ViewSize()
    override fun setImage(image: Drawable?) {
        drawableImg = image
        setupImageBounds()
    }

    private fun setupImageBounds() {
        val setupBounds = SetupImageBounds(this)
        setupBounds.setupImageBounds(viewSize)
    }

    override fun setImage(image: Bitmap?) {
        bitmapImg = null
        if (image != null) {
            bitmapImg = BitmapHolder(image, false)
        }
        drawableImg = null
        setupImageBounds()
    }

    override fun setStateImage(image: Bitmap?) {
        bitmapImg = null
        if (image != null) {
            bitmapImg = BitmapHolder(image, true)
        }
        drawableImg = null
        setupImageBounds()
    }

    override fun getImage(): Bitmap? {
        return if (bitmapImg != null) {
            bitmapImg!!.bitmap
        } else null
    }

    override fun toBitmapPosition(x: Int, y: Int): Position? {
        if (bitmapImg == null) return Position.invalid()
        val imageX = x - bounds!!.left
        val imageY = y - bounds!!.top
        if (imageX > bounds!!.width() || imageY > bounds!!.height()) {
            return Position.invalid()
        }
        return if (imageX < 0 || imageY < 0) {
            Position.invalid()
        } else Position(
            imageX,
            imageY
        )
    }

    override fun updateSize(w: Int, h: Int, oldw: Int, oldh: Int) {
        viewSize.setSize(w, h)
        setupImageBounds()
    }

    override fun draw(canvas: Canvas) {
        if (bitmapImg == null || bounds == null) {
            return
        }
        canvas.drawBitmap(bitmapImg!!.bitmap, bounds!!.left.toFloat(), bounds!!.top.toFloat(), null)
    }
}