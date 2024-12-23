private fun calculateGraph(data: List<String>): Graph<String> {
    val graph = Graph<String>()

    data.forEach { line ->
        val (left, right) = line.split("-", limit = 2)

        graph.addEdge(left, right)
        graph.addEdge(right, left)
    }

    return graph
}

fun main() {
    val data = calculateGraph(readFile(23))

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


//Part 1
private fun part1(data: Graph<String>): Int {
    return data.findCycles(3)
        .filter { it.size == 3 && it.any { name -> name.startsWith("t") } }
        .map { it.toSet() }.toSet()
        .size
}


//Part 2
private fun part2(data: Graph<String>): String {
    return data.getAllCliques(emptySet(), data.adjacencyMap.keys, mutableSetOf())
        .maxBy { it.size }
        .sorted()
        .joinToString(",")
}
