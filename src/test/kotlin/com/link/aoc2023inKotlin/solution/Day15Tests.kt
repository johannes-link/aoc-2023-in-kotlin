package com.link.aoc2023inKotlin.solution

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day15Tests {
    @Autowired
    private lateinit var day: Day15

    @Test
    fun `Should return expected result fo part A`() {
        assertEquals(1320, day.solvePartA("day15"))
    }

    @Test
    fun `Should return expected result fo part B`() {
        assertEquals(145L, day.solvePartB("day15"))
    }

    @ParameterizedTest
    @CsvSource(
        "52,HASH",
        "30,rn=1",
        "253,cm-",
        "97,qp=3",
        "47,cm=2",
        "14,qp-",
        "180,pc=4",
        "9,ot=9",
        "197,ab=5",
        "48,pc-",
        "214,pc=6",
        "231,ot=7",
        "0,rn",
        "1,qp"
    )
    fun `should calculate hash`(expected: Int, input: String) {
        assertEquals(expected, input.toHash())
    }
}