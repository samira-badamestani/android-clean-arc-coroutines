package com.architecture.clean.ui

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import android.view.LayoutInflater
import android.view.ViewGroup


abstract class DataBindingViewHolder<T>

( val dataBinding: ViewDataBinding)

    : androidx.recyclerview.widget.RecyclerView.ViewHolder(dataBinding.root) {

     constructor(@NonNull inflater: LayoutInflater, @IdRes layoutId: Int,
                @NonNull parent: ViewGroup, @NonNull attachToParent: Boolean) :
             this(DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, attachToParent))


    abstract fun onBind(t: T)
}

