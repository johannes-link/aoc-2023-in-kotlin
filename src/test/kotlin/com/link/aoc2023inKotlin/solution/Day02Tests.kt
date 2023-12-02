package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.model.CubeGame
import com.link.aoc2023inKotlin.model.CubeGameRound
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Day02Tests {
    @Autowired
    private lateinit var day02: Day02

    @Test
    fun `Should return expected result fo part A`() {
        assertEquals(8L, day02.solvePartA("day02"))
    }

    @Test
    fun `Should return expected result fo part B`() {
        assertEquals(2286L, day02.solvePartB("day02"))
    }

    @Test
    fun `Should map to CubeGame - 1`() {
        val expectedCubeGame = CubeGame(id=1L, listOf(CubeGameRound(red=4,green=0,blue=3), CubeGameRound(red=1,green=2,blue=6), CubeGameRound(red=0,green=2,blue=0)))

        with(day02) {
            assertEquals( expectedCubeGame, "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green".toCubeGame() )
        }
    }
}