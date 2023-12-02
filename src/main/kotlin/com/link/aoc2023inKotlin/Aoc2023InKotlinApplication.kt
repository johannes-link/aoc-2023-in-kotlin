package com.link.aoc2023inKotlin

import com.link.aoc2023inKotlin.solution.Day01
import com.link.aoc2023inKotlin.solution.Day02
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

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
}
