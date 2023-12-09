package com.link.aoc2023inKotlin

import com.link.aoc2023inKotlin.solution.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.math.BigInteger

@SpringBootApplication
class Aoc2023InKotlinApplication

fun main(args: Array<String>) {
	val aoc2023 = runApplication<Aoc2023InKotlinApplication>(*args)

	val day01 = aoc2023.getBean(Day01::class.java)
	println( "Day 1, Part A: " + day01.solvePartA("day01") )
	println( "Day 1, Part B: " + day01.solvePartB("day01") )
	println()

	val day02 = aoc2023.getBean(Day02::class.java)
	println( "Day 2, Part A: " + day02.solvePartA("day02") )
	println( "Day 2, Part B: " + day02.solvePartB("day02") )
	println()

	val day03 = aoc2023.getBean(Day03::class.java)
	println( "Day 3, Part A: " + day03.solvePartA("day03") )
	println( "Day 3, Part B: " + day03.solvePartB("day03") )
	println()

	val day04 = aoc2023.getBean(Day04::class.java)
	println( "Day 4, Part A: " + day04.solvePartA("day04") )
	println( "Day 4, Part B: " + day04.solvePartB("day04") )
	println()

	val day05 = aoc2023.getBean(Day05::class.java)
	println( "Day 5, Part A: " + day05.solvePartA("day05") )
	// println( "Day 5, Part B: " + day05.solvePartB("day05") ) // Removed for performance
	println()

	val day06 = aoc2023.getBean(Day06::class.java)
	println( "Day 6, Part A: " + day06.solvePartA() )
	println( "Day 6, Part B: " + day06.solvePartB() )
	println()

	val day07 = aoc2023.getBean(Day07::class.java)
	println( "Day 7, Part A: " + day07.solvePartA() )
	println( "Day 7, Part B: " + day07.solvePartB() )
	println()

	val day08 = aoc2023.getBean(Day08::class.java)
	println( "Day 8, Part A: " + day08.solvePartA() )
	println( "Day 8, Part B: " + day08.solvePartB() )
	println()

	val day09 = aoc2023.getBean(Day09::class.java)
	println( "Day 9, Part A: " + day09.solvePartA() )
	println( "Day 9, Part B: " + day09.solvePartB() )
	println()
}
