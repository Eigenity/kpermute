package com.eigenity.kpermute

import kotlin.random.Random

interface IntPermutation : Iterable<Int> {

    /**
     * Domain of the permutation.
     */
    val size: Int


    /** Fast path. No range checks. Precondition: if size >= 0 then arg ∈ [0, size). */
    fun encodeUnchecked(value: Int): Int

    /** Fast path. No range checks. Precondition: if size >= 0 then arg ∈ [0, size). */
    fun decodeUnchecked(encoded: Int): Int

    /**
     * Encode an integer in [0, [size]) into its permuted value.
     */
    fun encode(value: Int): Int {
        if (size >= 0) require(value in 0 until size) {
            "value $value out of range [0, $size)"
        }
        return encodeUnchecked(value)
    }

    /**
     *  Decode a previously encoded integer back to its original value.
     */
    fun decode(encoded: Int): Int {
        if (size >= 0) require(encoded in 0 until size) {
            "encoded $encoded out of range [0, $size)"
        }
        return decodeUnchecked(encoded)
    }

    /**
     * Iterator that yields encoded values for [0, size).
     */
    override fun iterator(): IntIterator = iterator(0)

    /**
     * Iterator that yields encoded values for [offset, size).
     */
    fun iterator(offset: Int): IntIterator
}
