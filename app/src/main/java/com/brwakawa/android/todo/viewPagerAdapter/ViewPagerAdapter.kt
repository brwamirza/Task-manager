package com.brwakawa.android.todo.viewPagerAdapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.brwakawa.android.todo.tabLayoutFragments.CompletedFragment
import com.brwakawa.android.todo.tabLayoutFragments.InProgressFragment
import com.brwakawa.android.todo.tabLayoutFragments.ToDoFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

fun init(fragment: Fragment, viewPager: ViewPager2, tabLayout:TabLayout) {
    viewPager.adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    ToDoFragment()
                }
                1 -> {
                    InProgressFragment()
                }
                else -> {
                    CompletedFragment()
                }
            }
        }

        override fun getItemCount(): Int {
            return 3
        }
    }

    TabLayoutMediator(tabLayout, viewPager) { tab, position ->
        tab.text = when (position) {
            0 -> "TO DO"
            1 -> "INPROGRESS"
            else -> "COMPLETED"
        }
    }.attach()
}