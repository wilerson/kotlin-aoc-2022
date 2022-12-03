package dev.wilerson.aoc.day3

import dev.wilerson.aoc.utils.readInput

fun main() {
    val input = readInput("day3input")
    val itemTypes = ('a'..'z') + ('A'..'Z')
    val priorities = (1 .. 52).toList()
    val priorityMap = itemTypes.mapIndexed { index, c -> c to priorities[index] }.toMap()

//    part1(input, priorityMap)

    val badgeSum = input.chunked(3).sumOf { (first, second, third) ->
        val badge = first.filter { it in second && it in third }.first()
        priorityMap[badge] ?: 0
    }

    println(badgeSum)
}

private fun part1(
    input: List<String>,
    priorityMap: Map<Char, Int>
) {
    val prioritySum = input.sumOf { line ->
        val (first, second) = line.chunked(line.length / 2)

        first.filter { it in second }.toList().distinct().sumOf { priorityMap[it] ?: 0 }
    }

    println(prioritySum)
}
