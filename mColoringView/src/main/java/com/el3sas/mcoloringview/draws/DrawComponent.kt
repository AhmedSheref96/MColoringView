package com.el3sas.mcoloringview.draws

import android.graphics.Canvas

/**
 * Created by seotm on 08.06.17.
 */
interface DrawComponent {
    fun updateSize(w: Int, h: Int, oldw: Int, oldh: Int)
    fun draw(canvas: Canvas)
}