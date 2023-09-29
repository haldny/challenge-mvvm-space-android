package com.devpass.spaceapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments: MutableList<Fragment> = mutableListOf()

    fun addListFragment(
        fragmentList: List<Fragment>
    ) = fragments.addAll(fragmentList)



    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]

}