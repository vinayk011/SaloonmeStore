package com.ezeetech.saloonme.store.adapter
/*
 *  Created by Vinay on 22-1-2021 for EzeeTech.
 *  Copyright (c) 2021  EzeeTech . All rights reserved.
 *
 */

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ezeetech.saloonme.ViewHolderBinding
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.databinding.AdapterProfileItemsBinding
import com.ezeetech.saloonme.store.listener.ItemClickListener
import com.ezeetech.saloonme.store.model.ListItem
import java.util.ArrayList

class AdapterProfileItem(
        private val mContext: Context,
        private val arrayList: ArrayList<ListItem>,
        private val listener: ItemClickListener<ListItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(mContext),
                R.layout.adapter_profile_items,
                parent,
                false
        )
        return ViewHolderBinding(viewDataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val profileItem = arrayList[holder.adapterPosition]
        val profileItemsBinding =
                (holder as ViewHolderBinding).binding as AdapterProfileItemsBinding
        profileItemsBinding.model = profileItem
        profileItemsBinding.layoutProfileItem.setOnClickListener {
            listener.onClicked(
                    profileItem
            )
        }
        profileItemsBinding.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}