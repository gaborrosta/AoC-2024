private class Guard(private val now: Point, private val facing: Point) {

    fun move(grid: List<String>, start: Point = now, face: Point = facing, obstacle: Point? = null): List<Point> {
        val visited: HashSet<Point> = hashSetOf(start)
        val height = grid.size - 1
        val width = grid[0].length - 1

        var next = start + face
        var current = start
        var currentFace = face
        var loopCheck = 10000
        while (next.inBound(width, height)) {
            if (loopCheck == 0) throw Exception("loop")

            if ((grid[next.y][next.x] == '.' || grid[next.y][next.x] == '^') && next != obstacle) {
                current = next
                if (visited.add(next)) {
                    loopCheck = 10000
                } else {
                    loopCheck--
                }
            } else {
                currentFace = currentFace.rotate(90)
            }

            next = current + currentFace
        }

        return visited.toList()
    }

    fun findLoop(grid: List<String>): Int {
        val possible = move(grid) - now

        return possible.fold(0) { acc, point ->
            try {
                move(grid, obstacle = point)
                return@fold acc
            } catch (e: Exception) {
                return@fold acc + 1
            }
        }
    }
}

fun main() {
    val data = readFile(6)

    var x = -1
    var y = -1
    find@ for (row in data.indices) {
        for (column in data.indices) {
            if (data[row][column] == '^') {
                x = column
                y = row
                break@find
            }
        }
    }
    val guard = Guard(Point(x, y), Point(0, -1))


    println("Part 1: ${part1(data, guard)}") //5 131
    println("Part 2: ${part2(data, guard)}") //1 784
}


//Part 1
private fun part1(data: List<String>, guard: Guard): Int {
    return guard.move(data).size
}


//Part 2
private fun part2(data: List<String>, guard: Guard): Int {
    return guard.findLoop(data)
}
