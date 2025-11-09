package com.eigenity.kpermute

import kotlin.random.Random
import kotlin.test.*

class LongPermutationsTest {

    @Test
    fun selectsImplementationBySizeSentinels() {
        // Full domain sentinel
        assertTrue(longPermutation(-1L) is FullLongPermutation)

        // Negative non-sentinel uses unsigned-domain variant
        assertTrue(longPermutation(-10L) is ULongPermutation)
    }

    @Test
    fun factoryReturnsFiniteSizeForPositive() {
        val size = 32L
        val p = longPermutation(size, seed = 1234L)
        assertEquals(size, p.size)
        // Check bijection on small domain
        CommonAssertsLong.assertBijectionOverDomain(p, size)
    }

    @Test
    fun factoryRepeatableSeed() {
        val p1 = longPermutation(32L, seed = 1234L)
        val p2 = longPermutation(32L, seed = 1234L)
        assertEquals(p1.toList(), p2.toList())
    }

    @Test
    fun halfLongPermutationLargeStillBijection() {
        val p = HalfLongPermutation(512L, Random(88))
        CommonAssertsLong.assertBijectionOverDomain(p, 512L)
    }

    @Test
    fun respectsRoundsParameterAcrossFactory() {
        val p1 = longPermutation(64L, seed = 123, rounds = 1)
        val p2 = longPermutation(64L, seed = 123, rounds = 5)
        CommonAssertsLong.assertDifferentMapping(p1, p2, sample = 10L)
    }

    @Test
    fun fullSelectedForMaxULongExplicit() {
        val p = longPermutation(-1L)
        assertTrue(p is FullLongPermutation)
    }

    @Test
    fun rangeWrapperRoundTrip() {
        val base = HalfLongPermutation(10L, Random(7))
        val rp = base.range(20L..29L)
        for (v in 20L..29L) {
            assertEquals(v, rp.decode(rp.encode(v)))
        }
    }

    @Test
    fun rangeWrapperIterator() {
        val base = HalfLongPermutation(5L, Random(1))
        val rp = base.range(5L..9L)
        val list = rp.toList()
        assertEquals(5, list.size)
        assertTrue(list.all { it in 5L..9L })
        assertEquals(5, list.toSet().size)
    }
}
