package com.zero.pecodetest_devnestor.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes private val layoutId: Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(null)
        setContentView(layoutId)
        onActivityCreated()
    }

    abstract fun onActivityCreated()

    override fun onBackPressed() {
        if (!backPressed()){
            when {
                supportFragmentManager.backStackEntryCount > 0 -> finishFragment()
                supportFragmentManager.backStackEntryCount == 0 -> finishAffinity()//exitVariant()
                else -> super.onBackPressed()
            }
        }
    }

    abstract fun backPressed(): Boolean

    override fun onResume() {
        super.onResume()
    }
}