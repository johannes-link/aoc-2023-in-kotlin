package com.link.aoc2023inKotlin.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day10Tests {
    @Autowired
    private lateinit var day10: Day10

    @ParameterizedTest
    @CsvSource(
        "4,day10a",
        "8,day10b"
    )
    fun `Should return expected result fo part A`(expected: Long, fileName: String) {
        assertEquals(expected, day10.solvePartA(fileName = fileName))
    }

    @ParameterizedTest
    @CsvSource(
        "1,day10a",
        "4,day10c",
        "4,day10d",
        "8,day10e",
        "10,day10f"
    )
    fun `Should return expected result fo part B`(expected: Int, fileName: String) {
        assertEquals(expected, day10.solvePartB(fileName = fileName))
    }
}