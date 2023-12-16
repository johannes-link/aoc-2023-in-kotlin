package com.link.aoc2023inKotlin.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day16Tests {
    @Autowired
    private lateinit var day: Day16

    @Test
    fun `Should return expected result fo part A`() {
        assertEquals(46, day.solvePartA())
    }

    @Test
    fun `Should return expected result fo part B`() {
        assertEquals(51, day.solvePartB())
    }
}