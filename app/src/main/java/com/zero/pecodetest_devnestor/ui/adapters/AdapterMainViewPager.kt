package com.zero.pecodetest_devnestor.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zero.pecodetest_devnestor.ui.main.MainActivity
import com.zero.pecodetest_devnestor.ui.screens.MainScreen

class AdapterMainViewPager(private val mainActivity: MainActivity, private var fragmentCount: Int) :
FragmentStateAdapter(mainActivity) {

    //private var fragmentCount : Int = 1

    override fun getItemCount(): Int {
        return fragmentCount
    }

    override fun createFragment(position: Int): Fragment {
        return MainScreen(position)
    }

    fun removeFrag() {
        fragmentCount--
        notifyDataSetChanged()
        mainActivity.sharedManager.countItems = fragmentCount
    }

    fun addFrag() {
        fragmentCount++
        notifyDataSetChanged()
        mainActivity.sharedManager.countItems = fragmentCount
    }

    /*fun setItems(int: Int): Boolean{
        fragmentCount += int
        notifyDataSetChanged()
        return true
    }*/
}