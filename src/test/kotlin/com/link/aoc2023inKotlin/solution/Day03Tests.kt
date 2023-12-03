package com.link.aoc2023inKotlin.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day03Tests {
    @Autowired
    private lateinit var day03: Day03

    @Test
    fun `Should return expected result fo part A`() {
        assertEquals(4361L, day03.solvePartA("day03"))
    }

    @Test
    fun `Should return expected result fo part B`() {
        assertEquals(467835L, day03.solvePartB("day03"))
    }
}