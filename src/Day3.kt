fun main() {
    val data = readFile(3).joinToString("")

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


//Part 1
private fun part1(data: String): Long {
    return Regex("mul\\((\\d*),(\\d*)\\)").findAll(data).fold(0L) { acc, result ->
        acc + result.groups.drop(1).map { it?.value?.toLong() ?: 0L }.fold(1L) { product, now -> product * now }
    }
}


//Part 2
private fun part2(data: String): Long {
    var enabled = true
    return Regex("mul\\((\\d*),(\\d*)\\)|do\\(\\)|don't\\(\\)").findAll(data).fold(0L) { acc, result ->
        val text = result.value

        if (text == "do()") {
            enabled = true
            return@fold acc
        } else if (text == "don't()") {
            enabled = false
            return@fold acc
        }

        if (enabled) {
            return@fold acc + result.groups.drop(1).map { it?.value?.toLong() ?: 0L }.fold(1L) { product, now -> product * now }
        } else {
            return@fold acc
        }
    }
}
