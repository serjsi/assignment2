package com.shpp.ssierykh.assignment1.utils.extensions

import android.content.Context
import android.util.TypedValue



    fun Int.convertDpToPixels( context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            context.resources.displayMetrics
        )
            .toInt()
    }

    fun Int.convertSpToPixels( context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            this.toFloat(),
            context.resources.displayMetrics
        )
            .toInt()
    }