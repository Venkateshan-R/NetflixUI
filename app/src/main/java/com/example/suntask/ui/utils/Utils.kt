package com.example.suntask.ui.utils

import android.content.Context
import android.view.View
import android.widget.Toast


fun Context.showToast(txt : String){
    Toast.makeText(this,txt,Toast.LENGTH_SHORT).show()
}

fun View.gone() = let { visibility = View.GONE }
fun View.invisible() = let { visibility = View.INVISIBLE }
fun View.visible() = let { visibility = View.VISIBLE }
