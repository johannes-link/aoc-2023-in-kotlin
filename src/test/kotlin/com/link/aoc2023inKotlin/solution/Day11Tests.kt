package com.link.aoc2023inKotlin.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day11Tests {
    @Autowired
    private lateinit var day11: Day11

    @Test
    fun `Should return expected result fo part A`() {
        assertEquals(374L, day11.solvePartA())
    }

    @ParameterizedTest
    @CsvSource(
        "1030,10",
        "8410,100"
    )
    fun `Should return expected result fo part B`(expected: Long, steps: Int) {
        assertEquals(expected, day11.solvePartB(steps=steps))
    }

    @ParameterizedTest
    @CsvSource(
        "6,1",
        "14,10"
    )
    fun `Should calculate distance to other coordinates`(expected: Long, steps: Int) {
        val actualResult = Coordinate(3L, 0L, '#').calculatedDistances(listOf(Coordinate(0L, 2L, '#')), Pair(listOf(), listOf(2)), index=0, steps=steps)

        assertEquals(expected, actualResult)
    }
}