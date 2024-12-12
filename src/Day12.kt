import java.util.*

private data class Region(private val letter: Char, var list: List<Point>) {

    val fence: Int
        get() {
            val area = list.size

            var perimeter = 0
            for (p in list) {
                perimeter += p.neighborsHv().count { it !in list }
            }

            return area * perimeter
        }

    val fence2: Int
        get() {
            val area = list.size

            var perimeter = 0
            for (p in list) {
                if (p.left() !in list && p.up() !in list) perimeter++
                if (p.left() !in list && p.down() !in list) perimeter++
                if (p.right() !in list && p.up() !in list) perimeter++
                if (p.right() !in list && p.down() !in list) perimeter++

                if (p.left() in list && p.up() in list && p.upLeft() !in list) perimeter++
                if (p.left() in list && p.down() in list && p.downLeft() !in list) perimeter++
                if (p.right() in list && p.up() in list && p.upRight() !in list) perimeter++
                if (p.right() in list && p.down() in list && p.downRight() !in list) perimeter++
            }

            return area * perimeter
        }

}

fun main() {
    val data = readFile(12)

    val used = hashSetOf<Point>()
    val regions = arrayListOf<Region>()

    for (y in data.indices) {
        for (x in data.indices) {
            if (Point(x, y) in used) continue

            val r = Region(data[y][x], lookAround(data, data[y][x], Point(x, y)))
            used.addAll(r.list)
            regions.add(r)
        }
    }

    println("Part 1: ${part1(regions)}") //1 371 306
    println("Part 2: ${part2(regions)}") //805 880
}

private fun lookAround(data: List<String>, letter: Char, start: Point): List<Point> {
    val queue = LinkedList<Point>()
    val result = HashSet<Point>()

    queue.offer(start)
    result.add(start)

    while (!queue.isEmpty()) {
        val current = queue.poll()!!

        for (next in current.neighborsHv()) {
            if (next !in result && next.inBound(data.size - 1, data[0].length - 1) && data[next.y][next.x] == letter) {
                queue.offer(next)
                result.add(next)
            }
        }
    }

    return result.toList()
}


//Part 1
private fun part1(regions: List<Region>): Int {
    return regions.sumOf { it.fence }
}


//Part 2
private fun part2(regions: List<Region>): Int {
    return regions.sumOf { it.fence2 }
}
