import kotlin.math.abs

fun main() {

    fun isRowSafe(row: List<Long>, shouldDescend: Boolean) = row.windowed(2).all { (a, b) ->
        val increment = abs(a - b)
        increment in 1..3 && if (shouldDescend) a > b else a < b
    }

    fun part1(input: List<String>): Int = input
        .map { it.split("\\s+".toRegex()).map(String::toLong) }
        .count { row ->
            val shouldDescend = row[0] > row[1]
            isRowSafe(row, shouldDescend)
        }

    fun part2(input: List<String>): Int = input
        .map { it.split("\\s+".toRegex()).map(String::toLong) }
        .count { row ->
            val shouldDescend = row[0] > row[1]
            val safe = isRowSafe(row, shouldDescend)

            if (!safe) {
                row.indices.map { skipIndex ->
                    row.filterIndexed { index, _ -> index != skipIndex }
                }.any {
                    isRowSafe(it, it[0] > it[1])
                }.also {
                    println("$row = $it")
                }
            } else {
                true
            }
        }


//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1L)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
