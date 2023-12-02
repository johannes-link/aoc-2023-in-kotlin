package com.link.aoc2023inKotlin.model

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CubeGameTests {
    @Test
    fun `should return that CubeGame contains the given amount of cubes`() {
        val cubeGame = CubeGame(id = 1L, listOf(CubeGameRound(4,0,3), CubeGameRound(1,2,6), CubeGameRound(0,2,0)))

        assertTrue(cubeGame.containsMaxCubes(red = 12, green = 13, blue = 14))
    }
    @Test
    fun `should return that CubeGame contains not the given amount of cubes`() {
        val cubeGame = CubeGame(id = 4L, listOf(CubeGameRound(3,1,6), CubeGameRound(6,3,0), CubeGameRound(14,3,15)))

        assertFalse(cubeGame.containsMaxCubes(red = 12, green = 13, blue = 14))
    }
}