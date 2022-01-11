package com.zero.pecodetest_devnestor.ui.main

import android.content.Intent
import androidx.annotation.IdRes
import com.zero.pecodetest_devnestor.base.BaseActivity
import androidx.viewpager2.widget.ViewPager2
import com.zero.pecodetest_devnestor.R
import com.zero.pecodetest_devnestor.ui.adapters.AdapterMainViewPager
import com.zero.pecodetest_devnestor.util.preferences.SharedManager

class MainActivity: BaseActivity(R.layout.activity_main) {

    @IdRes var parentLayoutId: Int? = null
    lateinit var adapter: AdapterMainViewPager
    lateinit var viewPager: ViewPager2
    private var currentItem = 0
    val intentIdName = "[PCTDN]position"
    lateinit var sharedManager: SharedManager

    override fun onActivityCreated() {
        parentLayoutId = R.id.parentViewPager

        sharedManager = SharedManager(this)

        adapter = AdapterMainViewPager(this, sharedManager.countItems)

        viewPager = findViewById(parentLayoutId!!)
        viewPager.adapter = adapter

        viewPager.currentItem = currentItem

        fetchIntent(intent)
    }

    override fun backPressed(): Boolean {
        return true
    }

    private fun fetchIntent(intent: Intent) {
        intent.extras?.let {
            val id: Int = it.getInt(intentIdName)

               // Timer().schedule(object : TimerTask() {
                   // override fun run() {
            viewPager.currentItem = id
                   // }
               // }, 500)
        }
    }
}