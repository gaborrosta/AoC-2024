fun main() {
    val data = readFile(4)

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


//Part 1
private fun part1(data: List<String>): Int {
    var count = 0
    val l = data[0].length
    val h = data.size

    data.forEachIndexed { y, row ->
        row.forEachIndexed { x, ch ->
            if (ch == 'X') {
                val right = row.drop(x).take(4) == "XMAS"
                val left = x >= 3 && row.drop(x - 3).take(4) == "SAMX"
                val up = y >= 3 && data[y - 1][x] == 'M' && data[y - 2][x] == 'A' && data[y - 3][x] == 'S'
                val down = y < h - 3 && data[y + 1][x] == 'M' && data[y + 2][x] == 'A' && data[y + 3][x] == 'S'

                val rightDown = y < h - 3 && x < l - 3 && data[y + 1][x + 1] == 'M' && data[y + 2][x + 2] == 'A' && data[y + 3][x + 3] == 'S'
                val rightUp = y >= 3 && x < l - 3 && data[y - 1][x + 1] == 'M' && data[y - 2][x + 2] == 'A' && data[y - 3][x + 3] == 'S'
                val leftDown = y < h - 3 && x >= 3 && data[y + 1][x - 1] == 'M' && data[y + 2][x - 2] == 'A' && data[y + 3][x - 3] == 'S'
                val leftUp = y >= 3 && x >= 3 && data[y - 1][x - 1] == 'M' && data[y - 2][x - 2] == 'A' && data[y - 3][x - 3] == 'S'

                count += listOf(right, left, up, down, rightDown, rightUp, leftDown, leftUp).count { it }
            }
        }
    }

    return count
}


//Part 2
private fun part2(data: List<String>): Int {
    var count = 0
    val l = data[0].length
    val h = data.size

    data.forEachIndexed { y, row ->
        row.forEachIndexed { x, ch ->
            if (ch == 'A' && x >= 1 && y >= 1 && x < l - 1 && y < h - 1) {
                val right = data[y - 1][x - 1] == 'M' && data[y - 1][x + 1] == 'M' && data[y + 1][x - 1] == 'S' && data[y + 1][x + 1] == 'S'
                val left = data[y - 1][x - 1] == 'S' && data[y - 1][x + 1] == 'S' && data[y + 1][x - 1] == 'M' && data[y + 1][x + 1] == 'M'
                val up = data[y - 1][x - 1] == 'M' && data[y - 1][x + 1] == 'S' && data[y + 1][x - 1] == 'M' && data[y + 1][x + 1] == 'S'
                val down = data[y - 1][x - 1] == 'S' && data[y - 1][x + 1] == 'M' && data[y + 1][x - 1] == 'S' && data[y + 1][x + 1] == 'M'

                count += listOf(right, left, up, down).count { it }
            }
        }
    }

    return count
}
