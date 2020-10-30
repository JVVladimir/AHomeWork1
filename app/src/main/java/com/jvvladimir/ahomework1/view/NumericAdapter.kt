package com.jvvladimir.ahomework1.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jvvladimir.ahomework1.R
import com.jvvladimir.ahomework1.model.Numeric


class NumericAdapter(private val numerics: MutableList<Numeric>, val listener: NumericViewHolder.IListener) :
    RecyclerView.Adapter<NumericViewHolder>() {

    companion object {
        const val defaultView = R.layout.recycler_numeric
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumericViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return NumericViewHolder(inflater.inflate(defaultView, parent, false), listener)
    }

    override fun onBindViewHolder(holder: NumericViewHolder, position: Int) {
        val item = numerics[position]

        holder.bind(item)
    }

    override fun getItemCount() = numerics.size

    override fun getItemViewType(position: Int) = defaultView
}