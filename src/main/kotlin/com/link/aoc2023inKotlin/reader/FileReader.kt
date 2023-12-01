package com.link.aoc2023inKotlin.reader

import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import org.springframework.util.FileCopyUtils
import org.springframework.util.ResourceUtils
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

@Component
class FileReader {
    fun readStringsFromFile(fileName: String) = this
        .getFileAsText(fileName)
        .split(System.getProperty("line.separator"))
        .dropLast(1) // Remove last empty line
        .map { it.replace("\r", "") }

    fun getFileAsText(fileName: String): String {
        val resource = ClassPathResource("inputs/$fileName.txt")
        val inputStream = resource.inputStream
        val reader = InputStreamReader(inputStream, StandardCharsets.UTF_8)
        return reader.use { it.readText() }
    }
}