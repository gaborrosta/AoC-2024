import java.util.*

fun main() {
    val data = readFile(16)

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


//Part 1
private fun part1(data: List<String>): Int {
    var start: Point? = null
    var end: Point? = null
    for (y in data.indices) {
        for (x in data[y].indices) {
            if (data[y][x] == 'S') {
                start = Point(x, y)
            } else if (data[y][x] == 'E') {
                end = Point(x, y)
            }
        }
    }

    requireNotNull(start)
    requireNotNull(end)

    val width = data[0].length
    val height = data.size

    val frontier = PriorityQueue<Triple<Point, Int, Point>>(compareBy { o ->
        o.second
    })
    frontier.add(Triple(start, 0, start - Point(1, 0)))

    val visited = hashSetOf<Pair<Point, Point>>()

    while (frontier.isNotEmpty()) {
        val (me, score, previous) = frontier.poll()
        val direction = me - previous

        if (Pair(me, direction) in visited) continue
        visited.add(Pair(me, direction))

        if (me == end) return score

        for (nd in Point.NEIGHBORS_HV) {
            val next = me + nd
            val newDirection = next - me
            if (next.inBound(width - 1, height - 1) && data[next.y][next.x] != '#') {
                if (newDirection == direction) {
                    frontier.add(Triple(next, score + 1, me))
                } else {
                    frontier.add(Triple(next, score + 1001, me))
                }
            }
        }
    }

    return -1
}


//Part 2
private fun part2(data: List<String>): Int {
    return -1
}
