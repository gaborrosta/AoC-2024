fun main() {
    val data = readFile(2)

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


//Part 1
private fun part1(data: List<String>): Int {
    return data.count { line ->
        val numbers = line.split(" ").map(String::toInt)

        val add = numbers[1] - numbers[0] > 0
        return@count numbers.zipWithNext().all { (a, b) ->
            if (add) {
                (b - a) > 0 && (b - a) < 4
            } else {
                (a - b) > 0 && (a - b) < 4
            }
        }
    }
}


//Part 2
private fun part2(data: List<String>): Int {
    return data.count { line ->
        val numbers = line.split(" ").map(String::toInt)

        repeat(numbers.size) { n ->
            val new = numbers.subList(0, n) + numbers.subList(n + 1, numbers.size)

            val add = new[1] - new[0] > 0
            val nowOk = new.zipWithNext().all { (a, b) ->
                if (add) {
                    (b - a) > 0 && (b - a) < 4
                } else {
                    (a - b) > 0 && (a - b) < 4
                }
            }

            if (nowOk) return@count true
        }

        return@count false
    }
}
