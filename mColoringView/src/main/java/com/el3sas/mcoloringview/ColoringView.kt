package com.el3sas.mcoloringview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import com.el3sas.mcoloringview.draws.image.DrawImage
import com.el3sas.mcoloringview.draws.image.DrawImageImpl
import com.el3sas.mcoloringview.floodFill.DrawFloodFilter

/**
 * Created by seotm on 08.06.17.
 */
class ColoringView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    @JvmField
    val drawImage: DrawImage

    @JvmField
    var paintColor = 0

    @JvmField
    var enableColoringBlackColor = false
    private var fillColorListener: OnFillColorListener? = null

    init {
        drawImage = DrawImageImpl()
        if (context != null) {
            SetupResources(context, attrs, defStyleAttr).setup(this)
        }
    }

    fun setImage(image: Drawable?) {
        drawImage.setImage(image)
        invalidate()
    }

    fun setImage(image: Bitmap?) {
        drawImage.setImage(image)
        invalidate()
    }

    fun setPaintColor(@ColorInt paintColor: Int) {
        this.paintColor = paintColor
    }

    var state: ColoringState?
        get() = ColoringState(this)
        set(state) {
            if (state != null) {
                state.restoreState(this)
                invalidate()
            }
        }

    fun setFillColorListener(listener: OnFillColorListener?) {
        fillColorListener = listener
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        drawImage.updateSize(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawImage.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)
        if (event.action == MotionEvent.ACTION_DOWN) {
            val x = event.x.toInt()
            val y = event.y.toInt()
            val bitmapPosition = drawImage.toBitmapPosition(x, y)
            val image = drawImage.getImage()
            if (!ColorValidation(enableColoringBlackColor).isValidPosition(
                    bitmapPosition!!,
                    image
                )
            ) {
                return true
            }
            DrawFloodFilter(bitmapPosition).draw(paintColor, image)
            invalidate()
            if (fillColorListener != null) {
                fillColorListener!!.onFillColor(paintColor)
            }
        }
        return true
    }
}