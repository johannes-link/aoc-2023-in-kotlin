package com.link.aoc2023inKotlin.model

data class CubeGame(
    val id: Long,
    val cubeGameRounds: List<CubeGameRound>
) {
    fun containsMaxCubes(red: Int, green: Int, blue: Int) = cubeGameRounds
        .any { it.red > red || it.green > green || it.blue > blue }
        .not()

    fun maxNumberOfRedCubes() = cubeGameRounds.maxOf{ it.red }.toLong()
    fun maxNumberOfBlueCubes() = cubeGameRounds.maxOf{ it.blue }.toLong()
    fun maxNumberOfGreenCubes() = cubeGameRounds.maxOf{ it.green }.toLong()
}

data class CubeGameRound(
    val red: Int,
    val green: Int,
    val blue: Int
)
