package dev.wilerson.aoc.day6

import dev.wilerson.aoc.utils.readInput

fun main() {
    val input = readInput("day6input")

    input.forEach { line ->
        var buffer = ""
        var index = 0
        for (c in line) {
            if (buffer.length >= 14) {
                break
            } else {
                val repeatedIndex = buffer.indexOf(c)
                if (repeatedIndex >= 0) {
                    buffer = buffer.drop(repeatedIndex + 1)
                }
                buffer += c
                index++
            }
        }
        println(index)
    }
}
