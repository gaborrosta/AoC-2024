fun main() {
    val data = readFile(8)

    val antennas = hashMapOf<Char, List<Point>>()
    for (y in data.indices) {
        for (x in data[y].indices) {
            val ch = data[y][x]
            if (ch == '.') continue

            if (antennas.contains(ch)) {
                antennas[ch] = antennas[ch]?.plus(Point(x, y)) ?: listOf(Point(x, y))
            } else {
                antennas[ch] = listOf(Point(x, y))
            }
        }
    }
    val height = data.size - 1
    val width = data[0].length - 1

    println("Part 1: ${part1(antennas, height, width)}")
    println("Part 2: ${part2(antennas, height, width)}")
}


//Part 1
private fun part1(antennas: Map<Char, List<Point>>, height: Int, width: Int): Int {
    val antinodes = hashSetOf<Point>()

    antennas.values.forEach { l ->
        val combinations = combinations(l, 2)

        combinations.forEach { (first, second, _) ->
            if (first != second) {
                val distance = second - first
                val left = second + distance
                val right = first - distance

                if (left.inBound(width, height)) {
                    antinodes.add(left)

                }
                if (right.inBound(width, height)) {
                    antinodes.add(right)
                }
            }
        }
    }

    return antinodes.size
}


//Part 2
private fun part2(antennas: Map<Char, List<Point>>, height: Int, width: Int): Int {
    val antinodes = hashSetOf<Point>()

    antennas.values.forEach { l ->
        val combinations = combinations(l, 2)

        combinations.forEach { (first, second, _) ->
            if (first != second) {
                val distance = second - first
                var left = second + distance
                var right = first - distance

                antinodes.add(first)
                antinodes.add(second)

                while (left.inBound(width, height)) {
                    antinodes.add(left)
                    left += distance
                }
                while (right.inBound(width, height)) {
                    antinodes.add(right)
                    right -= distance
                }
            }
        }
    }

    return antinodes.size
}
