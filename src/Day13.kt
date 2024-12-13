fun main() {
    val data = readFile(13).joinToString("\r\n").split("\r\n\r\n").map { lines ->
        val (a, b, goal) = lines.split("\r\n", limit = 3)

        val abRegex = "Button [A|B]: X\\+(\\d*), Y\\+(\\d*)".toRegex()
        val goalRegex = "Prize: X=(\\d*), Y=(\\d*)".toRegex()

        Triple(
            abRegex.findAll(a).first().groupValues.drop(1).let { Point(it[0].toInt(), it[1].toInt()) },
            abRegex.findAll(b).first().groupValues.drop(1).let { Point(it[0].toInt(), it[1].toInt()) },
            goalRegex.findAll(goal).first().groupValues.drop(1).let { Point(it[0].toInt(), it[1].toInt()) }
        )
    }

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


//Part 1
private fun part1(data: List<Triple<Point, Point, Point>>): Int {
    return data.sumOf { m ->
        val a = m.first
        val b = m.second
        val goal = m.third

        val x = (goal.x * b.y.toFloat() - goal.y * b.x) / (a.x * b.y - a.y * b.x)
        val y = (a.x * goal.y.toFloat() - a.y * goal.x) / (a.x * b.y - a.y * b.x)

        if (x % 1 == 0F && y % 1 == 0F) {
            x.toInt() * 3 + y.toInt()
        } else {
            0
        }
    }
}


//Part 2
private fun part2(data: List<Triple<Point, Point, Point>>): Long {
    return data.sumOf { m ->
        val a = m.first
        val b = m.second
        val goal = Pair(m.third.x + 10_000_000_000_000L.toDouble(), m.third.y + 10_000_000_000_000L.toDouble())

        val x = (goal.first * b.y - goal.second * b.x) / (a.x * b.y - a.y * b.x)
        val y = (a.x * goal.second - a.y * goal.first) / (a.x * b.y - a.y * b.x)

        if (x % 1 == 0.toDouble() && y % 1 == 0.toDouble()) {
            x.toLong() * 3 + y.toLong()
        } else {
            0
        }
    }
}
