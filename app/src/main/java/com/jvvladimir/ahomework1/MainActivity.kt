package com.jvvladimir.ahomework1

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jvvladimir.ahomework1.model.NumericHolder

class MainActivity : AppCompatActivity() {

    companion object {
        const val SPAN_COUNT_VERTICAL = 3
        const val SPAN_COUNT_HORIZONTAL = 4
    }

    private var numericHolder = NumericHolder { it % 2 == 0 }
    private var curSpanCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        curSpanCount =
            if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) SPAN_COUNT_VERTICAL else SPAN_COUNT_HORIZONTAL

        val data = numericHolder.numerics
        val adapter = NumericAdapter(data)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = GridLayoutManager(context, curSpanCount)
            this.adapter = adapter
        }

        findViewById<Button>(R.id.button).apply {
            setOnClickListener {
                if (it == this) {
                    numericHolder.generateOneMore()
                    adapter.notifyDataSetChanged()
                    recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }
}