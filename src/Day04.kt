fun main() {
    fun List<String>.tiltLeft(): List<String> {
        val lines = this
        return buildList {
            for (i in lines.indices) {
                var x = 0
                var y = i
                add(
                    buildString {
                        while (x <= i) {
                            append(lines[y][x])
                            x++
                            y--
                        }
                    }
                )
            }

            val width = lines.first().length - 1
            for (i in lines.indices) {
                var x = 1 + i
                var y = width
                add(
                    buildString {
                        while (x <= width) {
                            append(lines[y][x])
                            x++
                            y--
                        }
                    }
                )
            }
        }
    }

    fun List<String>.tiltRight(): List<String> {
        val lines = this
        return buildList {
            val width = lines.first().length - 1
            for (i in lines.indices) {
                var x = 0
                var y = width - i
                add(
                    buildString {
                        while (x <= i) {
                            append(lines[y][x])
                            x++
                            y++
                        }
                    }
                )
            }

            for (i in lines.indices) {
                var x = i + 1
                var y = 0
                add(
                    buildString {
                        while (x <= width) {
                            append(lines[y][x])
                            x++
                            y++
                        }
                    }
                )
            }
        }
    }

    fun List<String>.pivot(): List<String> {
        val lines = this
        return buildList {
            for (i in lines.first().indices) {
                add(buildString {
                    for (j in lines.indices) {
                        append(lines[j][i])
                    }
                })
            }
        }
    }


    fun part1(input: List<String>): Int {
        val rightToLeft = input.map { it.reversed() }
        val topToBottom = input.pivot()
        val bottomToTop = topToBottom.map { it.reversed() }

        val topLeftToBottomRight = input.tiltLeft()
        val bottomRightToTopLeft = topLeftToBottomRight.map { it.reversed() }
        val topRightToBottomLeft = input.tiltRight()
        val bottomLeftToTopRight = topRightToBottomLeft.map { it.reversed() }

        val all = input + rightToLeft + topToBottom + bottomToTop +
                topLeftToBottomRight + bottomRightToTopLeft + topRightToBottomLeft + bottomLeftToTopRight
        return all.sumOf { Regex("XMAS").findAll(it).count() }
    }

    fun part2(input: List<String>): Int {
        val xmasPatterns = arrayOf("MAS", "SAM")
        return input.windowed(3).sumOf {
            var count = 0
            for (i in 0..input.first().length - 3) {
                var y = i
                val leftTopBottomRight = buildString {
                    for (j in 0..2) {
                        append(it[j][y++])
                    }
                }
                y = i + 2
                val rightTopBottomLeft = buildString {
                    for (j in 0..2) {
                        append(it[j][y--])
                    }
                }

                if (xmasPatterns.contains(leftTopBottomRight) && xmasPatterns.contains(rightTopBottomLeft)) {
                    count++
                }
            }
            count
        }
    }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
