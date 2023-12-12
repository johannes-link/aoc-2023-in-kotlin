package com.link.aoc2023inKotlin.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day12Tests {
    @Autowired
    private lateinit var day12: Day12

    @Test
    fun `Should return expected result fo part A`() {
        assertEquals(21L, day12.solvePartA("day12"))
    }

    @Test
    fun `Should return expected result fo part B`() {
        assertEquals(525152L, day12.solvePartB("day12"))
    }

    @Test
    fun `should return number of possible arrangements`() {
        val actual = Pair("?###????????", listOf(3,2,1)).numberOfPossibleArrangements()
        assertEquals(10L, actual)
    }
}