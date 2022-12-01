package dev.wilerson.aoc.day1

import dev.wilerson.aoc.utils.readInput

fun main() {
//    part1()
    part2()

}

private fun part1() {
    val input = readInput("day1part1input")

    var max = 0
    var acc = 0
    input.forEach { line ->
        if (line == "") {
            if (acc > max) max = acc
            acc = 0
        } else {
            acc += line.toInt()
        }
    }
    if (acc > max) max = acc

    println(max)
}

private fun part2() {
    val input = readInput("day1part1input")

    buildList {
        var acc = 0
        input.forEach { line ->
            if (line == "") {
                add(acc)
                acc = 0
            } else {
                acc += line.toInt()
            }
        }
        add(acc)
    }.sortedDescending().take(3).sum().also { println(it) }
}
