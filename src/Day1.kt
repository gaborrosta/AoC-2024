import kotlin.math.abs

fun main() {
    val data = readFile(1)

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


//Part 1
private fun part1(data: List<String>): Long {
    val (left, right) = data.map { it.split("   ", limit = 2).map(String::toLong) }.rotate()

    return left.sorted().zip(right.sorted()).fold(0L) { acc, element ->
        val (l, r) = element
        acc + abs(l - r)
    }
}


//Part 2
private fun part2(data: List<String>): Long {
    val (left, right) = data.map { it.split("   ", limit = 2).map(String::toLong) }.rotate()

    return left.fold(0L) { acc, element ->
        val times = right.count { it == element }.toLong()
        acc + element * times
    }
}
