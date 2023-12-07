package com.link.aoc2023inKotlin.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day08Tests {
    @Autowired
    private lateinit var day08: Day08

    @ParameterizedTest
    @CsvSource(
        "day08a,2",
        "day08b,6"
    )
    fun `Should return expected result fo part A`(fileName: String, expected: Long) {
        assertEquals(expected, day08.solvePartA(fileName))
    }

    @Test
    fun `Should return expected result fo part B`() {
        assertEquals(6L, day08.solvePartB("day08c"))
    }
}