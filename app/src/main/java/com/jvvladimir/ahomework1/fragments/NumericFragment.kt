package com.jvvladimir.ahomework1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jvvladimir.ahomework1.R
import com.jvvladimir.ahomework1.model.Color
import com.jvvladimir.ahomework1.model.Numeric
import com.jvvladimir.ahomework1.model.NumericRepository

class NumericFragment : Fragment() {

    companion object {
        const val EXTRAS_NUMERIC = "NUMERIC"

        fun newInstance(numeric: Numeric): NumericFragment {
            // Создаем данные, которые потом положим в фрагмент
            val extras = Bundle().apply {
                putSerializable(EXTRAS_NUMERIC, numeric.value)
            }

            return NumericFragment().apply {
                arguments = extras
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_numeric, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numeric = numeric()
        if (numeric != null) {
            val textView = view.findViewById<TextView>(R.id.numeric_name).apply {
                text = numeric.value.toString()
            }

            val backgroundColor = when (numeric.color) {
                Color.BLUE -> R.color.blue
                Color.RED -> R.color.red
            }

            textView.setBackgroundResource(backgroundColor)
        }
    }

    fun numeric(): Numeric? {
        return NumericRepository.instance.getNumeric(arguments?.getSerializable(EXTRAS_NUMERIC) as? Int)
    }
}