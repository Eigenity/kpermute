package com.eigenity.kpermute

object EmptyIntPermutation : IntPermutation {
    override val size: Int get() = 0
    override val rounds: Int get() = 0
    override fun encode(value: Int): Int = 0
    override fun decode(encoded: Int): Int = 0
    private val array = IntArray(0)
    override fun iterator(offset: Int): IntIterator = array.iterator()
}
