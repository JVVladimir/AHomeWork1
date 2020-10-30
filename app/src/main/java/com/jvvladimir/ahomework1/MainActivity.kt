package com.jvvladimir.ahomework1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jvvladimir.ahomework1.model.NumericHolder

class MainActivity : AppCompatActivity() {

    var numericHolder = NumericHolder { it % 2 == 0 }

    companion object {
        const val SPAN_COUNT_VERTICAL = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = numericHolder.numerics
        val adapter = NumericAdapter(data)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = GridLayoutManager(context, SPAN_COUNT_VERTICAL)
            this.adapter = adapter
        }

        // TODO: maybe delete this row!
        recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
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