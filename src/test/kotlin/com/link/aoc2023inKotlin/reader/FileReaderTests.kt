package com.link.aoc2023inKotlin.reader

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FileReaderTests {
    @Autowired
    private lateinit var fileReader: FileReader

    @Test
    fun `should return string-array from file`() {
        assertEquals(listOf("hello", "world"), fileReader.readStringsFromFile("stringFile"))
    }
}