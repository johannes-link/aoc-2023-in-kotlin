package com.link.aoc2023inKotlin.solution

import com.link.aoc2023inKotlin.reader.FileReader
import org.springframework.stereotype.Component
import kotlin.math.max

@Component
class Day15(
    private val fileReader: FileReader
) {
    fun solvePartA(fileName: String = "day15") = fileReader
        .readStringsFromFile(fileName)
        .first()
        .split(",")
        .sumOf { it.toHash() }

    fun solvePartB(fileName: String = "day15"): Long {
        val boxes = mutableMapOf<Int, MutableSet<Lens>>()

        fileReader
            .readStringsFromFile(fileName)
            .first()
            .split(",")
            .map { it.toLens() }
            .forEach {
                val key = it.label.toHash()
                var value = boxes[key] ?: mutableSetOf()

                value = when (it.operationCharacter) {
                    '-' -> {
                        value.filterNot { box -> box.label == it.label }.toMutableSet()
                    }
                    '=' -> {
                        val index = value.indexOf(it).takeIf { index -> index >= 0 } ?: value.size
                        (value.toMutableList().apply { add(index, it) }).toMutableSet()
                    }
                    else -> throw Exception("Unknown operation!")
                }

                boxes[key] = value
            }

        return boxes
            .entries
            .flatMap { box -> box.value.mapIndexed { index, lens -> (box.key + 1) * (index + 1) * (lens.focalLength ?: 1) } }
            .sumOf { it.toLong() }
    }
}

fun String.toHash(): Int = this
    .toCharArray()
    .fold(0){ a, b -> ((a+b.code)*17)%256 }

fun String.toLens(): Lens {
    val index = max(indexOf('-'), indexOf('='))
    return Lens(
        label = this.substring(0, index),
        operationCharacter = this[index],
        focalLength = this.getOrNull(index+1)?.toString()?.toInt()
    )
}

class Lens(
    val label: String,
    val operationCharacter: Char,
    val focalLength: Int?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Lens) return false

        return label == other.label
    }

    override fun hashCode(): Int {
        return label.toHash()
    }
}