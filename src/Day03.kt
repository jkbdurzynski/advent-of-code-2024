fun main() {


    fun part1(input: List<String>): Long = input.flatMap {
        """mul\((\d{1,3}),(\d{1,3})\)""".toRegex().findAll(it)
    }.sumOf {
        check(it.groupValues.size == 3)
        it.groupValues[1].toLong() * it.groupValues[2].toLong()
    }

    fun part2(input: List<String>): Long {
        var enabled = true
        return input.flatMap {
            """mul\((\d{1,3}),(\d{1,3})\)|do\(\)|don't\(\)""".toRegex().findAll(it)
        }.sumOf {
            when (it.value) {
                "do()" -> {
                    enabled = true
                    0
                }
                "don't()" -> {
                    enabled = false
                    0
                }
                else -> {
                    if (enabled) {
                        check(it.groupValues.size == 3)
                        it.groupValues[1].toLong() * it.groupValues[2].toLong()
                    } else {
                        0
                    }
                }
            }
        }
    }

//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1L)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
