package com.ezeetech.saloonme.store.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ezeetech.saloonme.store.BuildConfig
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.databinding.FragmentBarberCreationBinding
import com.ezeetech.saloonme.store.databinding.FragmentBookingsBinding
import com.ezeetech.saloonme.store.model.Slider
import com.ezeetech.saloonme.store.ui.home.ActivityUserHome
import com.salonme.base.BaseFragment
import com.salonme.base.inflateFragment
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class BarberCreationFragment: BaseFragment<FragmentBarberCreationBinding>() {
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
                R.layout.fragment_barber_creation
        ) as FragmentBarberCreationBinding
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
