package com.link.aoc2023inKotlin.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day04Tests {
    @Autowired
    private lateinit var day04: Day04

    @Test
    fun `Should return expected result fo part A`() {
        assertEquals(13L, day04.solvePartA("day04"))
    }

    @Test
    fun `Should return expected result fo part B`() {
        assertEquals(30, day04.solvePartB("day04"))
    }
}