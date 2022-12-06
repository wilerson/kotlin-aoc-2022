package utils

import dev.wilerson.aoc.utils.chunkedByPredicate
import org.junit.jupiter.api.Test

class UtilsTest {

    @Test
    fun `chunking with predicate`() {
        val list = listOf(
            "a",
            "ab",
            "abc",
            "",
            "d",
            "de",
            "",
            "f"
        )

        val result = list.chunkedByPredicate { it != "" }
        assert(result.size == 3)
        assert(result == listOf(listOf("a", "ab", "abc"), listOf("d", "de"), listOf("f")))
    }

    @Test
    fun `chunking empty list`() {
        val list = emptyList<String>()

        val result = list.chunkedByPredicate { it != "" }
        assert(result.isEmpty())
    }

    @Test
    fun `chunking with nonexistent predicate`() {
        val list = listOf(
            "a",
            "ab",
            "abc",
            "d",
            "de",
            "f"
        )

        val result = list.chunkedByPredicate { it != "" }
        assert(result.size == 1)
        assert(result == listOf(list))
    }
}
