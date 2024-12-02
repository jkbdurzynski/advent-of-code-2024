fun main() {

    fun isReportSafe(row: List<Long>) = row.windowed(2)
        .let { windows ->
            val shouldDescend = row[0] > row[1]
            windows.all { (a, b) ->
                if (shouldDescend) {
                    a - b in 1..3
                } else {
                    a - b in -3..-1
                }
            }
        }

    fun part1(input: List<String>): Int = input
        .map { it.split("""\s+""".toRegex()).map(String::toLong) }
        .count { isReportSafe(it) }

    fun part2(input: List<String>): Int = input
        .map { it.split("\\s+".toRegex()).map(String::toLong) }
        .count { row ->
            row.indices.asSequence()
                .map { removeIndex -> row.toMutableList().apply { removeAt(removeIndex) } }
                .any { isReportSafe(it) }
        }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
