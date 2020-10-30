package com.jvvladimir.ahomework1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jvvladimir.ahomework1.fragments.NumericFragment
import com.jvvladimir.ahomework1.fragments.NumericListFragment
import com.jvvladimir.ahomework1.model.Numeric
import com.jvvladimir.ahomework1.model.NumericRepository

open class MainActivity : AppCompatActivity(), NumericListFragment.IListener {

    companion object {
        const val TAG_NUMERICS = "NUMERICS"
        const val TAG_SOLO_NUMERIC = "SOLO_NUMERIC"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val numeric = NumericRepository.instance.getNumeric(0)
            showNumeric(numeric)
        } else {
            checkDetails()
        }
    }

    private fun showNumeric(numeric: Numeric?) {
        if (numeric == null) {
            // просто проверка. Непонятно как сюда попали. По хорошему надо залогировать
            // Но, в рамках примера, игнорируем исполнение
            return
        }

        val numericFragment = NumericFragment.newInstance(numeric)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.numeric_fragment, numericFragment, TAG_SOLO_NUMERIC)
            .commitAllowingStateLoss()
    }

    private fun checkDetails() {
        val numericFragment =
            supportFragmentManager.findFragmentByTag(TAG_SOLO_NUMERIC) as? NumericFragment
        if (numericFragment != null) {
            supportFragmentManager
                .beginTransaction()
                .remove(numericFragment)
                .commitAllowingStateLoss()

            val droid = numericFragment.numeric()
            showNumeric(droid)
        }
    }

    override fun onNumericClicked(numeric: Numeric) {
        showNumeric(numeric)
    }
}