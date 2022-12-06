package dev.wilerson.aoc.utils

import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/resources", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun <T> List<T>.chunkedByPredicate(predicate: (T) -> Boolean): List<List<T>> {
    if (isEmpty()) return emptyList()
    val list = this

    return buildList {
        val end = list.size
        var i = 0
        while (i < end) {
            val slice = list.slice(i until list.size)
            val chunk = slice.takeWhile(predicate)
            i += if (chunk.isNotEmpty()) {
                add(chunk)
                chunk.size + 1
            } else {
                add(slice)
                slice.size
            }
        }
    }
}
