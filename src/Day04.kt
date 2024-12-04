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

    val xmasCornerLetters = arrayOf('M', 'S')
    fun isMas(corner: Char, oppositeCorner: Char) =
        corner != oppositeCorner &&
                xmasCornerLetters.contains(corner) &&
                xmasCornerLetters.contains(oppositeCorner)


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
        var count = 0
        for (i in 1..input.size - 2) {
            for (j in 1..input.size - 2) {
                if (input[i][j] != 'A') continue

                val topLeft = input[i - 1][j - 1]
                val topRight = input[i - 1][j + 1]
                val bottomLeft = input[i + 1][j - 1]
                val bottomRight = input[i + 1][j + 1]
                if (isMas(topLeft, bottomRight) && isMas(bottomLeft, topRight)) count++
            }
        }

        return count
    }

    fun part2V2(input: List<String>): Int = input.indices.drop(1).dropLast(1)
        .sumOf { i ->
            input[i].indices.drop(1).dropLast(1)
                .count { j ->
                    if (input[i][j] != 'A') {
                        false
                    } else {
                        val topLeft = input[i - 1][j - 1]
                        val topRight = input[i - 1][j + 1]
                        val bottomLeft = input[i + 1][j - 1]
                        val bottomRight = input[i + 1][j + 1]
                        isMas(topLeft, bottomRight) && isMas(bottomLeft, topRight)
                    }
                }
        }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
    part2V2(input).println()
}
