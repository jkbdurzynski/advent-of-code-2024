import kotlin.math.abs

fun main() {
    fun readToPairs(input: List<String>) = input.map {
        val first = it.substringBefore(" ").toLong()
        val second = it.substringAfterLast(" ").toLong()
        first to second
    }

    fun part1(input: List<String>): Long = readToPairs(input).unzip()
        .let { (left, right) ->
            left.sorted().zip(right.sorted())
                .sumOf { (leftItem, rightItem) -> abs(leftItem - rightItem) }
        }

    fun part2(input: List<String>): Long = readToPairs(input).unzip()
        .let { (left, right) ->
            val occurrences = right.groupingBy { it }.eachCount()
            left.sumOf { it * (occurrences[it] ?: 0) }
        }


//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1L)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
