package dev.wilerson.aoc.day5

import dev.wilerson.aoc.utils.chunkedByPredicate
import dev.wilerson.aoc.utils.readInput

fun main() {
    val input = readInput("day5input")
    val (stacksWithNumbers, instructions) = input.chunkedByPredicate { it != "" }
    val crateIndices = listOf(1, 5, 9, 13, 17, 21, 25, 29, 33)

    val stacks = stacksWithNumbers.dropLast(1)

    val movableStacks = MutableList<List<String>>(9) { emptyList()  }
    (0..8).forEach { i -> movableStacks[i] = stacks.mapNotNull { s(it, crateIndices[i]) }.filterNot { it.isBlank() } }

    val r = Regex("""move (\d+) from (\d) to (\d)""")

    instructions.forEach { instruction ->
        val matchResult = r.matchEntire(instruction)
        if (matchResult != null) {
            val (howMany, from, to) = matchResult.groupValues.drop(1).map { it.toInt() }
            val fromIndex = from - 1
            val toIndex = to - 1
            movableStacks[toIndex] = movableStacks[fromIndex].take(howMany) + movableStacks[toIndex]
            movableStacks[fromIndex] = movableStacks[fromIndex].drop(howMany)
        }
    }

    println(movableStacks.filterNot { it.isEmpty() }.joinToString(separator = "") { it.first() })
}

private fun s(it: String, startIndex: Int) =
    if(startIndex < it.length) it.substring(startIndex, startIndex + 1) else null