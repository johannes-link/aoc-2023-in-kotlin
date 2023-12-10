package com.link.aoc2023inKotlin

import com.link.aoc2023inKotlin.solution.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Aoc2023InKotlinApplication

fun main(args: Array<String>) {
	val aoc2023 = runApplication<Aoc2023InKotlinApplication>(*args)

	val day = aoc2023.getBean(Day10::class.java)
	println( "Part A: " + day.solvePartA() )
	println( "Part B: " + day.solvePartB() )
}
