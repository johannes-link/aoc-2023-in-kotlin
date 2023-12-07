package com.link.aoc2023inKotlin.model

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.provider.ValueSource

class CamelCardHandTests {
    @ParameterizedTest
    @CsvSource(
        "33332,2AAAA",
        "77888,77788",
        "11223,11234",

        "T55J5,32T3K",
        "KK677,32T3K",
        "KTJJT,32T3K",
        "QQQJA,32T3K",

        "T55J5,KTJJT",
        "KK677,KTJJT",
        "QQQJA,KTJJT",

        "T55J5,KK677",
        "QQQJA,KK677",

        "QQQJA,T55J5"
    )
    fun `should compare cards correclty`(greater: String, smaller: String) {
        assert(CamelCardHand(greater, 123) > CamelCardHand(smaller, 123))
        assert(CamelCardHand(smaller, 123) < CamelCardHand(greater, 123))
    }
    @ParameterizedTest
    @CsvSource(
        "T55X5,32T3K",
        "KK677,32T3K",
        "KTXXT,32T3K",
        "QQQXA,32T3K",

        "T55X5,KK677",
        "KTXXT,KK677",
        "QQQXA,KK677",

        "KTXXT,T55X5",
        "QQQXA,T55X5",

        "KTXXT,QQQXA",

        "QQQQ2,XKKK2",

        // Five of kind vs four of kind
        "1XXXX,44544",

        // Four of Kind vs full house
        "X1112,11222",

        // Full House vs three of kind
        "11X22,15222",
        "X1122,152X2",

        // Three of kind vs 2 pair
        "13XX2,11223",

        // 2 pair vs 1 pair
        "1X2X3,11234"
    )
    fun `should compare cards correclty with joker`(greater: String, smaller: String) {
        assert(CamelCardHand(greater, 123) > CamelCardHand(smaller, 123))
        assert(CamelCardHand(smaller, 123) < CamelCardHand(greater, 123))
    }

    @ParameterizedTest
    @ValueSource(strings = ["1XXXX","111XX", "11X11","6XX66","XQQQX","X999X","3X3X3","2X22X","XAAXA","X555X","XXXX8","XXXXX"])
    fun `should map to five of kind`(input: String) {
        val card =
        assertEquals(Type.FIVE_OF_A_KIND, CamelCardHand(input, 123).type)
    }

    @ParameterizedTest
    @ValueSource(strings = ["12XXX","111X2", "11X21", "QXXQ2","XQ999","9XX49","QQ3XX","X5X5K","X4X77","X88X7","XKK4X"])
    fun `should map to four of kind`(input: String) {
        assertEquals(Type.FOUR_OF_A_KIND, CamelCardHand(input, 123).type)
    }

    @ParameterizedTest
    @ValueSource(strings = ["11X22", "X1122"])
    fun `should map to full house`(input: String) {
        assertEquals(Type.FULL_HOUSE, CamelCardHand(input, 123).type)
    }

    @ParameterizedTest
    @ValueSource(strings = ["595XQ","X3K39","X9TX2","7XX4Q","73XXT","9XQ4X","X6X53"])
    fun `should map to three of kind`(input: String) {
        assertEquals(Type.THREE_OF_A_KIND, CamelCardHand(input, 123).type)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1234X","94X8A","XK59A"])
    fun `should map to one pair`(input: String) {
        assertEquals(Type.ONE_PAIR, CamelCardHand(input, 123).type)
    }

    @ParameterizedTest
    @ValueSource(strings = ["K93T5"])
    fun `should map to high card`(input: String) {
        assertEquals(Type.HIGH_CARD, CamelCardHand(input, 123).type)
    }
}