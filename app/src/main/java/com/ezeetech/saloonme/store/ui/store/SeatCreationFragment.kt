package com.ezeetech.saloonme.store.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.databinding.FragmentSeatCreationBinding
import com.ezeetech.saloonme.store.databinding.FragmentServiceCreationBinding
import com.salonme.base.BaseFragment
import com.salonme.base.inflateFragment

class SeatCreationFragment : BaseFragment<FragmentSeatCreationBinding>() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //(activity as ActivityUserHome).setActionBarView(binding.toolbar)
        //(activity as ActivityUserHome).appBar(show = true, back = false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(activity as ActivityUserHome).title(getString(R.string.bookings))
        arguments?.let {

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = inflateFragment(
                inflater,
                container,
                R.layout.fragment_seat_creation
        ) as FragmentSeatCreationBinding
        observeClick(root)
        return root
    }

    override fun init() {
        // binding.toolbar.title = getString(R.string.bookings)
    }

    override fun onBackPressed() {
        //(activity as ActivityUserHome).finish()
    }

}
