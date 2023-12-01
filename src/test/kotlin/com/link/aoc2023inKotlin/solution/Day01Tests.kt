package com.link.aoc2023inKotlin.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day01Tests {
    @Autowired
    private lateinit var day01: Day01

    @Test
    fun `Should return expected result fo part A`() {
        assertEquals(142L, day01.solvePartA("day01a"))
    }

    @Test
    fun `Should return expected result fo part B`() {
        assertEquals(281L, day01.solvePartB("day01b"))
    }
}