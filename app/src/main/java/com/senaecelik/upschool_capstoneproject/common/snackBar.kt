package com.senaecelik.upschool_capstoneproject.common

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

fun onSNACK(view: View, s: String){

        Snackbar.make(view, s, 1000).show()

}