fun main() {
    val (locks, keys) = readFile(25).joinToString("\r\n").split("\r\n\r\n").partition {
        it.startsWith("#####")
    }.toList().map { schemas ->
        schemas.map { schema ->
            schema.split("\r\n").rotateStrings().fold(listOf<Int>()) { acc, row ->
                acc + (row.count { it == '#' } - 1)
            }
        }
    }

    println("Part 1: ${part1(locks, keys)}")
}


//Part 1
private fun part1(locks: List<List<Int>>, keys: List<List<Int>>): Int {
    var count = 0
    locks.forEach { lock ->
        keys.forEach { key ->
            if (lock.zip(key).map { it.first + it.second }.all { it <= 5 }) {
                count++
            }
        }
    }

    return count
}
