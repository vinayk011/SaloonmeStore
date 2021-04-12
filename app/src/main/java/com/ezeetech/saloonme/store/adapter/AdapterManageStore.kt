package com.ezeetech.saloonme.store.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.ezeetech.saloonme.ViewHolderBinding
import com.ezeetech.saloonme.store.R
import com.ezeetech.saloonme.store.databinding.AdapterManageStoreBinding
import com.ezeetech.saloonme.store.listener.ItemClickListener
import com.ezeetech.saloonme.store.model.ListItem
import java.util.ArrayList

class AdapterManageStore(private val mContext: Context,
                         private val arrayList: ArrayList<ListItem>,
                         private val listener: ItemClickListener<ListItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(mContext),
                R.layout.adapter_manage_store,
                parent,
                false
        )
        return ViewHolderBinding(viewDataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val storeItem = arrayList[holder.adapterPosition]
        val manageItemsBinding =
                (holder as ViewHolderBinding).binding as AdapterManageStoreBinding
        manageItemsBinding.model = storeItem
        manageItemsBinding.layoutManageStore.setOnClickListener {
            listener.onClicked(
                    storeItem
            )
        }
        manageItemsBinding.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}