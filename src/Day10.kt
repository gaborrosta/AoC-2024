fun main() {
    val data = readFile(10)

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}

private fun findNine(data: List<String>, currentY: Int, currentX: Int): Pair<List<Point>, Int> {
    if (data[currentY][currentX] == '9') return listOf(Point(currentX, currentY)) to 1

    val height = data.size - 1
    val width = data[0].length - 1
    val nextValue = (data[currentY][currentX].digitToInt() + 1).toString()[0]

    val reachable = hashSetOf<Point>()
    var count = 0
    if (Point(currentX + 1, currentY).inBound(width, height) && data[currentY][currentX + 1] == nextValue) {
        val (r, c) = findNine(data, currentY, currentX + 1)
        reachable.addAll(r)
        count += c
    }

    if (Point(currentX, currentY + 1).inBound(width, height) && data[currentY + 1][currentX] == nextValue) {
        val (r, c) = findNine(data, currentY + 1, currentX)
        reachable.addAll(r)
        count += c
    }

    if (Point(currentX - 1, currentY).inBound(width, height) && data[currentY][currentX - 1] == nextValue) {
        val (r, c) = findNine(data, currentY, currentX - 1)
        reachable.addAll(r)
        count += c
    }

    if (Point(currentX, currentY - 1).inBound(width, height) && data[currentY - 1][currentX] == nextValue) {
        val (r, c) = findNine(data, currentY - 1, currentX)
        reachable.addAll(r)
        count += c
    }

    return reachable.toList() to count
}


//Part 1
private fun part1(data: List<String>): Int {
    var count = 0

    for (y in data.indices) {
        for (x in data[y].indices) {
            if (data[y][x] == '0') {
                count += findNine(data, y, x).first.size
            }
        }
    }

    return count
}


//Part 2
private fun part2(data: List<String>): Int {
    var count = 0

    for (y in data.indices) {
        for (x in data[y].indices) {
            if (data[y][x] == '0') {
                count += findNine(data, y, x).second
            }
        }
    }

    return count
}
