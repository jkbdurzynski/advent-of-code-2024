fun main() {
    val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)"""
    val doAndDontRegex = """do(n't)?\(\)"""

    fun multiplyResult(it: MatchResult): Long {
        val (first, second) = it.destructured
        return first.toLong() * second.toLong()
    }

    fun part1(input: String): Long = mulRegex.toRegex()
        .findAll(input)
        .sumOf { multiplyResult(it) }

    fun part2(input: String): Long {
        var enabled = true
        var sum = 0L
        """$mulRegex|$doAndDontRegex""".toRegex()
            .findAll(input)
            .forEach {
                when {
                    it.value == "do()" -> enabled = true
                    it.value == "don't()" -> enabled = false
                    enabled -> sum += multiplyResult(it)
                }
            }
        return sum
    }

    val input = readInput("Day03").joinToString()
    part1(input).println()
    part2(input).println()
}
