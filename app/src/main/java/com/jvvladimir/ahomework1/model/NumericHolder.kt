package com.jvvladimir.ahomework1.model

class NumericHolder(
    val rule: (Int) -> Boolean
) {

    val numerics = mutableListOf<Numeric>()

    init {
        generateNumerics { it % 2 == 0 }
    }

    fun generateOneMore() {
        if (rule(numerics.size))
            numerics.add(Numeric(numerics.size, Color.RED))
        else
            numerics.add(Numeric(numerics.size, Color.BLUE))
    }

    private fun generateNumerics(n: Int = 100, rule: (Int) -> Boolean): MutableList<Numeric> {
        for (i in 0..n) {
            if (rule(i))
                numerics.add(Numeric(i, Color.RED))
            else
                numerics.add(Numeric(i, Color.BLUE))
        }
        return numerics
    }
}