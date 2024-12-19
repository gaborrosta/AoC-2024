fun main() {
    val (towels, wanted) = readFile(19).joinToString("\r\n").split("\r\n\r\n", limit = 2).let {
        it[0].split(", ") to it[1].split("\r\n")
    }

    println("Part 1: ${part1(towels, wanted)}")
    println("Part 2: ${part2(towels, wanted)}")
}


//Part 1
private fun part1(towels: List<String>, wanted: List<String>): Int {
    return wanted.count { design ->
        recursiveCheck(design, towels)
    }
}

fun recursiveCheck(design: String, towels: List<String>): Boolean {
    if (design == "") return true

    val usable = towels.filter { design.startsWith(it) }

    usable.forEach { towel ->
        val result = recursiveCheck(design.drop(towel.length), towels)
        if (result) {
            return true
        }
    }

    return false
}


//Part 2
private fun part2(towels: List<String>, wanted: List<String>): Long {
    return wanted.sumOf { design ->
        recursiveCount(design, towels, hashMapOf())
    }
}

fun recursiveCount(design: String, towels: List<String>, cache: HashMap<String, Long>): Long {
    if (design == "") return 1L

    val usable = towels.filter { design.startsWith(it) }
    var count = 0L

    usable.forEach { towel ->
        val new = design.drop(towel.length)
        if (new in cache) {
            count += cache[new]!!
        } else {
            val result = recursiveCount(new, towels, cache)
            cache[new] = result
            count += result
        }
    }

    return count
}
