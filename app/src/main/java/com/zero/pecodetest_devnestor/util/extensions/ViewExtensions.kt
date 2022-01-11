package com.zero.pecodetest_devnestor.util.extensions

import android.view.View

fun View.gone(): View {
    visibility = View.GONE
    return this
}
fun View.visible(): View {
    visibility = View.VISIBLE
    return this
}