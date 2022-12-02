package dev.wilerson.aoc.day2

import dev.wilerson.aoc.utils.readInput

fun main() {
    val input = readInput("day2input")

    // part 1
    val part1Score = input.sumOf { line ->
        val (opponentHand, ourHand) = line.split(" ").map { parseHand(it) }

        ourHand.score + ourHand.match(opponentHand).score
    }
    println(part1Score)

    // part2
    val part2Score = input.sumOf { line ->
        val (columnA, columnB) = line.split(" ")
        val opponentHand = parseHand(columnA)
        val desiredResult = parseResult(columnB)
        val ourHand = handForDesiredResult(opponentHand, desiredResult)

        ourHand.score + desiredResult.score
    }
    println(part2Score)
}

fun handForDesiredResult(opponentHand: Hand, desiredResult: Result) = when(desiredResult) {
    Result.DRAW -> opponentHand
    Result.WIN -> opponentHand.beaten()
    Result.LOSS -> opponentHand.beats()
}

fun parseHand(value: String) = when(value) {
    "A" -> Hand.ROCK
    "B" -> Hand.PAPER
    "C" -> Hand.SCISSORS
    "X" -> Hand.ROCK
    "Y" -> Hand.PAPER
    "Z" -> Hand.SCISSORS
    else -> throw IllegalArgumentException()
}

fun parseResult(value: String) = when(value) {
    "X" -> Result.LOSS
    "Y" -> Result.DRAW
    "Z" -> Result.WIN
    else -> throw IllegalArgumentException()
}

enum class Hand(val score: Int) {
    ROCK(1), PAPER(2), SCISSORS(3);

    fun match(other: Hand) = if (this == other) Result.DRAW else if (this.beats() == other) Result.WIN else Result.LOSS

    fun beats() = when(this) {
        ROCK -> SCISSORS
        PAPER -> ROCK
        SCISSORS -> PAPER
    }

    fun beaten() = when(this) {
        ROCK -> PAPER
        PAPER -> SCISSORS
        SCISSORS -> ROCK
    }
}

enum class Result(val score: Int) {
    WIN(6), LOSS(0), DRAW(3)
}
