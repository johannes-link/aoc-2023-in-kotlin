package com.link.aoc2023inKotlin.reader

import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils

@Component
class FileReader {
    fun readStringsFromFile(fileName: String) = this
        .getFileAsText(fileName)
        .split(System.getProperty("line.separator"))
        .dropLast(1) // Remove last empty line
        .map { it.replace("\r", "") }

    private fun getFileAsText(fileName: String) = ResourceUtils
        .getFile("classpath:inputs/$fileName.txt")
        .readText()
}