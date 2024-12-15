fun main() {
    val data = readFile(15).joinToString("\r\n")

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


//Part 1
private fun part1(data: String): Int {
    val (map, start, instructions) = data.split("\r\n\r\n").let { (m, i) ->
        var s = Point(-1, -1)
        val m2 = ArrayList(m.split("\r\n"))
        find@ for (y in m2.indices) {
            for (x in m2[y].indices) {
                if (m2[y][x] == '@') {
                    s = Point(x, y)
                    m2[y] = m2[y].replace('@', '.')
                    break@find
                }
            }
        }

        Triple(
            m2.map { it.toList() },
            s,
            i.mapNotNull { ch ->
                when (ch) {
                    '<' -> Point(-1, 0)
                    '>' -> Point(1, 0)
                    '^' -> Point(0, -1)
                    'v' -> Point(0, 1)
                    '\n' -> null
                    '\r' -> null
                    else -> throw Exception("Unknown instruction")
                }
            },
        )
    }

    var position = start
    val newMap = ArrayList(map.map { ArrayList(it) })
    val height = map.size - 1
    val width = map[0].size - 1

    instructions.forEach { instruction ->
        var newPos = position + instruction

        when (newMap[newPos.y][newPos.x]) {
            '.' -> {}
            '#' -> newPos = position
            'O' -> {
                var firstProblem = newPos
                while (firstProblem.inBound(
                        width,
                        height
                    ) && (newMap[firstProblem.y][firstProblem.x] != '.' && newMap[firstProblem.y][firstProblem.x] != '#')
                ) {
                    firstProblem += instruction
                }

                if (newMap[firstProblem.y][firstProblem.x] == '.') {
                    newMap[firstProblem.y][firstProblem.x] = 'O'
                    newMap[newPos.y][newPos.x] = '.'
                } else {
                    newPos = position
                }
            }
        }

        position = newPos
    }

    var sum = 0
    for (y in newMap.indices) {
        for (x in newMap[y].indices) {
            if (newMap[y][x] == 'O') {
                sum += (100 * y + x)
            }
        }
    }

    return sum
}


//Part 2
private fun part2(data: String): Int {
    return -1
}
