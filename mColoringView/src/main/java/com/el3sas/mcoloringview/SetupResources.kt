package com.el3sas.mcoloringview

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.core.content.ContextCompat

/**
 * Created by seotm on 12.06.17.
 */
internal class SetupResources(
    private val context: Context,
    private val attrs: AttributeSet?,
    private val defStyleAttr: Int
) {
    fun setup(view: ColoringView) {
        if (attrs == null) return
        val array =
            context.theme.obtainStyledAttributes(attrs, R.styleable.ColoringView, defStyleAttr, 0)
        setImage(view, array)
        setPaintColor(view, array)
        setEnableColoringBlackColor(view, array)
        array.recycle()
    }

    private fun setImage(view: ColoringView, array: TypedArray) {
        val image = array.getDrawable(R.styleable.ColoringView_imageSrc)
        if (image != null) view.drawImage.setImage(image)
    }

    private fun setPaintColor(view: ColoringView, array: TypedArray) {
        val defColor = ContextCompat.getColor(context, R.color.defaultPaintColor)
        view.paintColor = array.getColor(R.styleable.ColoringView_paintColor, defColor)
    }

    private fun setEnableColoringBlackColor(view: ColoringView, array: TypedArray) {
        view.enableColoringBlackColor =
            array.getBoolean(R.styleable.ColoringView_enableColoringBlackColor, true)
    }
}