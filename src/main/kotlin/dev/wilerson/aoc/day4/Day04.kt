package dev.wilerson.aoc.day4

import dev.wilerson.aoc.utils.readInput

fun main() {
    val input = readInput("day4input")
    val count = input.count { pair ->
        val (first, second) = pair.split(",").map { rangeString ->
            val (begin, end) = rangeString.split("-").map { it.toInt() }
            begin..end
        }

//        first.all { it in second } || second.all { it in first } // part1
        first.any { it in second } || second.any { it in first }
    }

    println(count)
}
