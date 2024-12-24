fun main() {
    val data = hashMapOf<String, Pair<Boolean?, String>>()

    val (initials, rules) = readFile(24).joinToString("\r\n").split("\r\n\r\n")

    initials.split("\r\n").forEach { initial ->
        val split = initial.split(": ")
        data[split[0]] = Pair(split[1] == "1", "")
    }

    rules.split("\r\n").forEach { rule ->
        val split = rule.split(" -> ")
        data[split[1]] = Pair(null, split[0])
    }

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


private fun calculateWire(now: String, data: HashMap<String, Pair<Boolean?, String>>): Boolean {
    val value = data[now]

    requireNotNull(value)

    if (value.first == null) {
        val connections = value.second.split(" ")
        val left = calculateWire(connections[0], data)
        val right = calculateWire(connections[2], data)

        return when (connections[1]) {
            "AND" -> left and right
            "OR" -> left or right
            "XOR" -> left xor right
            else -> throw Exception("Unknown gate")
        }
    } else {
        return value.first!!
    }
}

private fun calculateZed(data: HashMap<String, Pair<Boolean?, String>>): Map<String, Boolean> {
    val zeds = data.keys.filter { it.startsWith("z") }.sortedDescending()
    val result = hashMapOf<String, Boolean>()

    zeds.forEach { z ->
        result[z] = calculateWire(z, data)
    }

    return result
}

//Part 1
private fun part1(data: HashMap<String, Pair<Boolean?, String>>): Long {
    return binToDec(
        calculateZed(data).entries
            .sortedByDescending { it.key }
            .joinToString("") { if (it.value) "1" else "0" }
    )
}


//Part 2
private fun part2(data: HashMap<String, Pair<Boolean?, String>>): Int {
    //I did not manage to solve this on my own, I needed help from others' solutions.
    return -1
}
