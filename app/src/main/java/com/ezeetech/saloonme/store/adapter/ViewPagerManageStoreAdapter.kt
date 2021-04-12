package com.ezeetech.saloonme.store.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ezeetech.saloonme.store.ui.store.BarberCreationFragment
import com.ezeetech.saloonme.store.ui.store.SeatCreationFragment
import com.ezeetech.saloonme.store.ui.store.ServiceCreationFragment

class ViewPagerManageStoreAdapter(fragment: Fragment, private val titles: Array<String>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BarberCreationFragment()
            1 -> ServiceCreationFragment()
            2 -> SeatCreationFragment()
            else -> BarberCreationFragment()
        }
    }

}