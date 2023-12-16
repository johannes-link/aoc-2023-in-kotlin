package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component

@Component
class Day16(
    private val fileReader: FileReader
) {
    fun solvePartA(fileName: String = "day16"): Int {
        val firstPosition = Visited(0,0,Direction.R)
        val map = fileReader.readStringsFromFile(fileName)

        return findEnergizedTiles(firstPosition = firstPosition, map=map)
    }

    fun solvePartB(fileName: String = "day16"): Int {
        val map = fileReader.readStringsFromFile(fileName)
        val yStartPositions = map.indices.flatMap { listOf(
            Visited(x=0, y=it, direction=Direction.R),
            Visited(x=map.first().length-1, y=it, direction=Direction.L)
        )}
        val xStartPositions = map.first().indices.flatMap { listOf(
            Visited(x=it, y=0, direction=Direction.D),
            Visited(x=it, y=map.size-1, direction=Direction.U)
        )}
        val startPositions = yStartPositions + xStartPositions

        return startPositions.maxOf { findEnergizedTiles(firstPosition = it, map = map) }
    }

    private fun findEnergizedTiles(
        firstPosition: Visited,
        map: List<String>
    ): Int {
        val visited = mutableListOf<Visited>()
        val positionsToCheck = mutableListOf(firstPosition)

        while (positionsToCheck.isNotEmpty()) {
            val position = positionsToCheck.removeFirst()
            visited.add(position)

            val nextPositions = when(map[position.y][position.x]) {
                '.' -> listOf( position.move() )
                '|' -> position.splitVertical()
                '-' -> position.splitHorizontal()
                '/' -> listOf( position.mirrorDownUp() )
                '\\' -> listOf( position.mirrorUpDown() )
                else -> throw Exception("Invalid operation!")
            }.filterNot {
                it in visited || isOutsideOfMap(position=it, map=map)
            }

            positionsToCheck.addAll(nextPositions)
        }

        return visited.distinctBy { Pair(it.x, it.y) }.size
    }

    private fun isOutsideOfMap(
        position: Visited,
        map: List<String>
    ) = (position.y !in map.indices || position.x !in map.first().indices)
}

data class Visited(
    val x: Int,
    val y: Int,
    val direction: Direction
) {
    fun move() = when (direction) {
        Direction.L -> Visited(x=x-1, y=y, direction=direction)
        Direction.R -> Visited(x=x+1, y=y, direction=direction)
        Direction.U -> Visited(x=x, y=y-1, direction=direction)
        Direction.D -> Visited(x=x, y=y+1, direction=direction)
    }

    fun splitVertical() = when(direction in listOf(Direction.L, Direction.R)) {
        true -> listOf(
            Visited(x=x, y=y-1, direction=Direction.U),
            Visited(x=x, y=y+1, direction=Direction.D),
        )
        else -> listOf(this.move())
    }

    fun splitHorizontal() = when(direction in listOf(Direction.U, Direction.D)) {
        true -> listOf(
            Visited(x=x+1, y=y, direction=Direction.R),
            Visited(x=x-1, y=y, direction=Direction.L),
        )
        else -> listOf(this.move())
    }

    fun mirrorDownUp() = when(direction) {
        Direction.D -> Visited(x=x-1,y=y, direction=Direction.L)
        Direction.U -> Visited(x=x+1,y=y, direction=Direction.R)
        Direction.L -> Visited(x=x,y=y+1, direction=Direction.D)
        Direction.R -> Visited(x=x,y=y-1, direction=Direction.U)
    }

    fun mirrorUpDown() = when(direction) {
        Direction.D -> Visited(x=x+1,y=y, direction=Direction.R)
        Direction.U -> Visited(x=x-1,y=y, direction=Direction.L)
        Direction.L -> Visited(x=x,y=y-1, direction=Direction.U)
        Direction.R -> Visited(x=x,y=y+1, direction=Direction.D)
    }
}

enum class Direction {
    L, R, U, D
}