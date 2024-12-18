import java.util.*

fun main() {
    val data = readFile(18)

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}

private fun findPath(corrupted: List<Point>): Int {
    val width = 70
    val height = 70

    val from = Point(0, 0)
    val to = Point(width, height)

    val frontier = PriorityQueue<Pair<Point, Int>>(compareBy { o ->
        o.second + o.first.manhattan(to)
    })
    frontier.add(Pair(from, 0))

    val visited = hashSetOf<Point>()

    while (frontier.isNotEmpty()) {
        val (me, steps) = frontier.poll()

        visited.add(me)

        if (me == to) return steps

        for (nd in Point.NEIGHBORS_HV) {
            val next = me + nd
            if (next.inBound(width, height) && Point(next.x, next.y) !in corrupted && next !in visited) {
                frontier.add(Pair(next, steps + 1))
            }
        }
    }

    return -1
}


//Part 1
private fun part1(data: List<String>): Int {
    return findPath(data.take(1024).map {
        val (x, y) = it.split(",", limit = 2)
        Point(x.toInt(), y.toInt())
    })
}


//Part 2
private fun part2(data: List<String>): String {
    val corrupted = data.map {
        val (x, y) = it.split(",", limit = 2)
        Point(x.toInt(), y.toInt())
    }

    repeat(data.size) { taken ->
        val result = findPath(corrupted.take(taken))
        if (result < 0) {
            return corrupted[taken - 1].let { "${it.x},${it.y}" }
        }
    }

    return ""
}
