package com.el3sas.mcoloringview.draws.image

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.el3sas.mcoloringview.draws.DrawComponent
import com.el3sas.mcoloringview.draws.Position

/**
 * Created by seotm on 09.06.17.
 */
interface DrawImage : DrawComponent {
    fun setImage(image: Drawable?)
    fun setImage(image: Bitmap?)
    fun setStateImage(image: Bitmap?)
    fun getImage(): Bitmap?
    fun toBitmapPosition(x: Int, y: Int): Position?
}