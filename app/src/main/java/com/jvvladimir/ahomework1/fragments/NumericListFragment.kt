package com.jvvladimir.ahomework1.fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jvvladimir.ahomework1.view.NumericAdapter
import com.jvvladimir.ahomework1.view.NumericViewHolder
import com.jvvladimir.ahomework1.R
import com.jvvladimir.ahomework1.model.Numeric
import com.jvvladimir.ahomework1.model.NumericRepository

class NumericListFragment : Fragment() {

    companion object {
        const val SPAN_COUNT_VERTICAL = 3
        const val SPAN_COUNT_HORIZONTAL = 4
    }

    interface IListener {
        fun onNumericClicked(numeric: Numeric)
    }

    // Вариант кода, для общения с activity без Intent
    protected var listener: IListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = requireActivity() as? IListener
    }

    private var curSpanCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_numeric, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        curSpanCount =
            if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) SPAN_COUNT_VERTICAL else SPAN_COUNT_HORIZONTAL

        val data = NumericRepository.instance.numerics
        val adapter = NumericAdapter(data, NumericClickHandler())
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = GridLayoutManager(context, curSpanCount)
            this.adapter = adapter
        }

        view.findViewById<Button>(R.id.button).apply {
            setOnClickListener {
                if (it == this) {
                    NumericRepository.instance.generateOneMore()
                    adapter.notifyDataSetChanged()
                    recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()

        listener = null
    }

    inner class NumericClickHandler : NumericViewHolder.IListener {
        override fun onNumericClicked(position: Int) {
            val numeric = NumericRepository.instance.getNumeric(position)
            listener?.onNumericClicked(numeric!!)
        }
    }

//    companion object {
//
//        // TODO: убрать в ресурсы
//        const val SPAN_COUNT_VERTICAL = 3
//        const val SPAN_COUNT_HORIZONTAL = 4
//
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment NumericFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            NumericListFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}