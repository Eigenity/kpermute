@file:JvmName("IntPermutations")
package com.eigenity.kpermute

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertFalse

class IntPermutationTest {

    @Test
    fun emptyPermutation() {
        assertFalse(intPermutation(0).iterator().hasNext())
    }

    @Test
    fun onePermutation() {
        val p = intPermutation(1)
        assertTrue(p.iterator().hasNext())
        assertContentEquals(listOf(0), p.toList())
        assertEquals(0, p.encode(0))
    }

    @Test
    fun emptyOffsetPermutation() {
        assertFalse(intPermutation(1).iterator(1).hasNext())
        assertFalse(intPermutation(100).iterator(100).hasNext())
        assertFalse(intPermutation(UInt.MAX_VALUE).iterator(UInt.MAX_VALUE.toInt()).hasNext())
        assertFalse(intPermutation(-120).iterator(-120).hasNext())
    }

    @Test
    fun sameTwice() {
        val p = intPermutation(100)
        for (i in 0 until 100) {
            assertEquals(p.encode(i), p.encode(i))
        }
    }

    @Test
    fun exhaustiveEvenPermutation() {
        val p = intPermutation(64)
        val set = HashSet<Int>()
        for (i in 0 until 64) {
            set.add(p.encode(i))
        }
        assertEquals(64, set.size)
        for (i in 0 until 64.toInt()) {
            assertTrue(set.contains(i))
        }
    }

    @Test
    fun exhaustiveOddPermutation() {
        val p = intPermutation(65)
        val set = HashSet<Int>()
        for (i in 0 until 65) {
            set.add(p.encode(i))
        }
        assertEquals(65, set.size)
    }

    /*
    @Test
    fun iterator() {
        val l = CyclingHashIntPermutation(4, Random).iterator().asSequence().toList()
        assertEquals(4, l.size)
        assertEquals(4, l.toSet().size)
    }

    @Test
    fun permutationSize() {
        for (i in 0..500)
            assertEquals(i, CyclingHashIntPermutation(i, Random).asSequence().count())
    }

    @Test
    fun allValuesFirst() {
        for (i in 1..100) {
            while (CyclingHashIntPermutation(i, Random).first() != i - 1) {
            }
        }
    }
     */
}
