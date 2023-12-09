package com.link.aoc2023inKotlin.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day09Tests {
    @Autowired
    private lateinit var day09: Day09

    @Test
    fun `Should return expected result fo part A`() {
        assertEquals(114L, day09.solvePartA("day09"))
    }

    @Test
    fun `Should return expected result fo part B`() {
        assertEquals(5L, day09.solvePartB("day09b"))
    }

    @ParameterizedTest
    @CsvSource(
        "18,0 3 6 9 12 15",
        "3,3 3 3 3 3",
        "0,0 0 0 0 0",

        "28,1 3 6 10 15 21",
        "7,2 3 4 5 6",
        "1,1 1 1 1",
        "0,0 0 0",

        "68,10 13 16 21 30 45",
        "23,3 3 5 9 15",
        "8,0 2 4 6",
        "2,2 2 2",
        "0,0 0"
    )
    fun `should predict next value`(expectedValue: Long, input: String) {
        val seq = input.split(" ").map { it.toLong() }
        assertEquals(expectedValue, day09.predictNextValue(sequence = seq))
    }
}