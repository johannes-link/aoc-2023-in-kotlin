package com.link.aoc2023inKotlin.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day07Tests {
    @Autowired
    private lateinit var day07: Day07

    @Test
    fun `Should return expected result fo part A`() {
        assertEquals(6440L, day07.solvePartA("day07"))
    }

    @Test
    fun `Should return expected result fo part B`() {
        assertEquals(5905L, day07.solvePartB("day07"))
    }
}