package com.el3sas.mcoloringview.draws

/**
 * Created by seotm on 09.06.17.
 */
class ViewSize {
    var width = 0
        private set
    var height = 0
        private set
    var isEstablished = false
        private set

    fun setSize(width: Int, height: Int) {
        this.width = width
        this.height = height
        isEstablished = true
    }
}