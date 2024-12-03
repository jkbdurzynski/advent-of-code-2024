fun main() {


    fun multiplyResult(it: MatchResult): Long {
        check(it.groupValues.size == 3)
        return it.groupValues[1].toLong() * it.groupValues[2].toLong()
    }

    fun part1(input: List<String>): Long = input.flatMap {
        """mul\((\d{1,3}),(\d{1,3})\)""".toRegex().findAll(it)
    }.sumOf { multiplyResult(it) }

    fun part2(input: List<String>): Long {
        var enabled = true
        val regex = """mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""".toRegex()
        return input.flatMap { regex.findAll(it) }
            .filter {
                when (it.value) {
                    "do()" -> {
                        enabled = true
                        false
                    }

                    "don't()" -> {
                        enabled = false
                        false
                    }

                    else -> enabled
                }
            }.sumOf { multiplyResult(it) }
    }

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
