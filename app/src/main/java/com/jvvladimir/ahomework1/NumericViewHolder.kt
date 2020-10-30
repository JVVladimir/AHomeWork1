package com.jvvladimir.ahomework1

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.jvvladimir.ahomework1.model.Color
import com.jvvladimir.ahomework1.model.Numeric

class NumericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val name = itemView.findViewById<TextView>(R.id.name)
    private val image = itemView.findViewById<ImageView>(R.id.image)

    fun bind(item: Numeric) {
        name.text = item.value.toString()

        val colorResId = when (item.color) {
            Color.BLUE -> R.color.blue
            Color.RED -> R.color.red
        }
        image.setImageResource(colorResId)
    }
}