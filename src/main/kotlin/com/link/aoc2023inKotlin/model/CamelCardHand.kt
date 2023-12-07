package com.link.aoc2023inKotlin.model

data class CamelCardHand(
    val cards: String,
    val bid: Int
): Comparable<CamelCardHand> {
    override fun compareTo(other: CamelCardHand): Int {
        // Compare full hands (first rule)
        when {
            this.type.value > other.type.value -> return 1
            this.type.value > other.type.value -> return 1
            this.type.value < other.type.value -> return -1
        }

        // Compare single cards (second rule)
        (0..<this.cards.length).forEach {
            when {
                this.cards[it]  isGreaterThan other.cards[it] -> return 1
                other.cards[it] isGreaterThan this.cards[it]  -> return -1
            }
        }

        throw Exception("Cards are fully equal")
    }

    val type = when {
        isXOfAKind(x = 5)   -> Type.FIVE_OF_A_KIND
        isXOfAKind(x = 4)   -> Type.FOUR_OF_A_KIND
        isFullHouse()       -> Type.FULL_HOUSE
        isXOfAKind(x = 3)   -> Type.THREE_OF_A_KIND
        isXPair(x = 2)      -> Type.TWO_PAIR
        isXPair(x = 1)      -> Type.ONE_PAIR
        isHighCard()        -> Type.HIGH_CARD
        else                -> Type.NO_MATCH
    }

    private fun isXOfAKind(
        x: Int
    ) = this
        .cards
        .groupBy { it }
        .filter {
            (((it.value.size + this.cards.filter{ char -> char == 'X' }.length) >= x) && it.key != 'X') || this.cards.filter { it == 'X' }.length == this.cards.length
        }
        .isNotEmpty()

    private fun isFullHouse(): Boolean {
        val cards = this
            .cards
            .groupBy { it }

        val numberOfCards = cards
            .filterNot { it.key == 'X' }
            .map { it.value.size }
            .sortedDescending()

        // Full house not possible
        if (numberOfCards.size != 2) {
            return false
        }

        // No Joker
        if (!cards.map { it.key }.contains('X')) {
            return numberOfCards[0] == 3 && numberOfCards[1] == 2
        }

        return (numberOfCards[0] == 2 && numberOfCards[1] == 2) || (numberOfCards[0] == 3 && numberOfCards[1] == 1)
    }

    private fun isXPair(x: Int): Boolean {
        val cards = this
            .cards
            .groupBy { it }

        // Contains no joker
        return when (cards.filterNot { it.key == 'X' }.size == this.cards.length || x != 1) {
            true -> cards.filter { it.value.size == 2 }.size == x
            else -> true
        }
    }

    private fun isHighCard() = this.cards.toSet().size == this.cards.length

    private infix fun Char.isGreaterThan(
        other: Char
    ): Boolean {
        val orderedCards = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'X')

        return orderedCards.indexOf(this) < orderedCards.indexOf(other)
    }
}

enum class Type(
    val value: Int
) {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1),
    NO_MATCH(0)
}