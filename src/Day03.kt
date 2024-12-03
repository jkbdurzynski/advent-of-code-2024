fun main() {
    val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)"""
    val doRegex = """do\(\)"""
    val dontRegex = """don't\(\)"""

    fun multiplyResult(it: MatchResult): Long {
        val (first, second) = it.destructured
        return first.toLong() * second.toLong()
    }

    fun part1(input: String): Long = mulRegex.toRegex()
        .findAll(input)
        .sumOf { multiplyResult(it) }

    fun part2(input: String): Long {
        var enabled = true
        return """$mulRegex|$doRegex|$dontRegex""".toRegex()
            .findAll(input)
            .onEach {
                when (it.value) {
                    "do()" -> enabled = true
                    "don't()" -> enabled = false
                }
            }
            .filter { enabled && it.value.startsWith("mul") }
            .sumOf { multiplyResult(it) }
    }

    val input = readInputSingleLine("Day03")
    part1(input).println()
    part2(input).println()
}
