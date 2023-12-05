package com.link.aoc2023inKotlin.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day05Tests {
    @Autowired
    private lateinit var day05: Day05

    @Test
    fun `Should return expected result fo part A`() {
        assertEquals(35L, day05.solvePartA("day05"))
    }

    @Test
    fun `Should return expected result fo part B`() {
        assertEquals(46L, day05.solvePartB("day05"))
    }

    @Test
    fun `Should map a map to almanac`() {
        val expectedAlmanac = listOf(listOf(45L, 77L, 23L), listOf(81L, 45L, 19L), listOf(68L, 64L, 13L))

        with(day05) {
            val actualAlmanac = "light-to-temperature map:;45 77 23;81 45 19;68 64 13".toAlmanac()

            assertEquals(expectedAlmanac, actualAlmanac)
        }
    }

    @Test
    fun `Should map seeds to almanac`() {
        val expectedAlmanac = listOf(listOf(79L, 14L, 55L, 13L))

        with(day05) {
            val actualAlmanac = "seeds: 79 14 55 13".toAlmanac()

            assertEquals(expectedAlmanac, actualAlmanac)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "79,81",
        "14,14",
        "55,57",
        "13,13"
    )
    fun `Should map to expected soil-value`(input: Long, expected: Long) {
        val almanac = listOf(listOf(listOf(50L, 98L, 2L), listOf(52L, 50L, 48L)))

        with(day05) {
            assertEquals( expected, input.getDestination(almanac) )
        }
    }

    @Test
    fun `Should map to expected destination`() {
        val almanac = listOf(
            listOf(listOf(88L, 18L, 7L), listOf(18L, 25L, 70L))
        )

        with(day05) {
            assertEquals( 74, 81L.getDestination(almanac) )
        }
    }
}