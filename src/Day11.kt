fun main() {
    val data = readFile(11)[0].split(" ").map(String::toLong)

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


//Part 1
private fun part1(data: List<Long>): Int {
    val prev = arrayListOf<Long>()
    prev.addAll(data)

    repeat(25) {
        val stones = arrayListOf<Long>()

        prev.forEach { current ->
            when {
                current == 0L -> stones.add(1)
                current.toString().length % 2 == 0 -> {
                    val n = current.toString()
                    val left = n.take(n.length / 2).toLong()
                    val right = n.drop(n.length / 2).toLong()
                    stones.add(left)
                    stones.add(right)
                }

                else -> stones.add(current * 2024)
            }
        }

        prev.clear()
        prev.addAll(stones)
    }

    return prev.size
}


//Part 2
private fun part2(data: List<Long>): Long {
    var prev = hashMapOf<Long, Long>()
    data.forEach { prev.increment(it, 1) }

    repeat(75) {
        val stones = hashMapOf<Long, Long>()

        prev.entries.forEach { (key, times) ->
            when {
                key == 0L -> stones.increment(1, times)
                key.toString().length % 2 == 0 -> {
                    val n = key.toString()
                    val left = n.take(n.length / 2).toLong()
                    val right = n.drop(n.length / 2).toLong()
                    stones.increment(left, times)
                    stones.increment(right, times)
                }

                else -> stones.increment(key * 2024, times)
            }
        }

        prev.clear()
        prev = stones
    }

    return prev.values.sum()
}
