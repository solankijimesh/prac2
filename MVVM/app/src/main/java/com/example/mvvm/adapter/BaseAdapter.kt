package com.example.mvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.BR
import com.example.mvvm.adapter.BaseAdapter.MyViewHolder

class BaseAdapter() : RecyclerView.Adapter<MyViewHolder>() {

    private lateinit var mContext: Context
    private lateinit var items: ArrayList<*>
    private var layoutId: Int = 0

    constructor(mContext: Context, items: ArrayList<*>, layoutId: Int) : this() {
        this.mContext = mContext
        this.items = items
        this.layoutId = layoutId
    }

    inner class MyViewHolder(private var binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setBinding(items: Any) {
            binding.setVariable(BR.data, items)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setBinding(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}