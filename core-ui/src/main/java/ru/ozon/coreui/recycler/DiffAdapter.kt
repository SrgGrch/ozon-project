package ru.ozon.coreui.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class DiffAdapter : AsyncListDifferDelegationAdapter<AdapterItem> {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AdapterItem>() {
            override fun areItemsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
                oldItem.uniqueTag == newItem.uniqueTag

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: AdapterItem, newItem: AdapterItem): Boolean =
                oldItem == newItem
        }
    }

    constructor() : super(DIFF_CALLBACK)

    constructor(vararg delegates: AdapterDelegate<List<AdapterItem>>) : super(
        DIFF_CALLBACK,
        *delegates
    )

    constructor(manager: AdapterDelegatesManager<List<AdapterItem>>) : super(
        DIFF_CALLBACK,
        manager
    )
}
