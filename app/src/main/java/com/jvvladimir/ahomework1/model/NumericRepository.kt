package com.jvvladimir.ahomework1.model

class NumericRepository private constructor() {

    companion object {
        val instance by lazy { NumericRepository() }
    }

    val numerics = mutableListOf<Numeric>()

    init {
        generateNumerics()
    }

    fun generateOneMore() {
        if (numerics.size % 2 == 0)
            numerics.add(Numeric(numerics.size, Color.RED))
        else
            numerics.add(Numeric(numerics.size, Color.BLUE))
    }

    fun getNumeric(ind: Int?) = if (ind == null) null else numerics[ind]

    private fun generateNumerics(n: Int = 100): MutableList<Numeric> {
        for (i in 0..n) {
            if (i % 2 == 0)
                numerics.add(Numeric(i, Color.RED))
            else
                numerics.add(Numeric(i, Color.BLUE))
        }
        return numerics
    }
}