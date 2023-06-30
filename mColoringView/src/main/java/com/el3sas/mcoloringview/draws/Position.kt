package com.el3sas.mcoloringview.draws

/**
 * Created by seotm on 09.06.17.
 */
class Position private constructor(val x: Int, val y: Int, val valid: Boolean) {
    constructor(x: Int, y: Int) : this(x, y, true) {}

    companion object {
        fun invalid(): Position {
            return Position(-1, -1, false)
        }
    }
}