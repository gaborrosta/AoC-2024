fun main() {
    val data = readFile(22)

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}

private fun calculateNext(input: Long): Long {
    val step1 = ((input * 64) xor input) % 16777216
    val step2 = ((step1 / 32) xor step1) % 16777216
    return ((step2 * 2048) xor step2) % 16777216
}


//Part 1
private fun part1(data: List<String>): Long {
    return data.sumOf { l ->
        val input = l.toLong()

        var next = input
        repeat(2000) {
            next = calculateNext(next)
        }

        next
    }
}


//Part 2
private fun part2(data: List<String>): Long {
    val sequences = hashMapOf<List<Long>, Long>()

    data.fold(listOf<List<Long>>()) { acc, l ->
        val usedSequence = mutableSetOf<List<Long>>()
        val input = l.toLong()
        val digits = arrayListOf(input % 10)

        var next = input
        repeat(2000) {
            next = calculateNext(next)
            digits.add(next % 10)
        }

        val zipped = digits.drop(1).zip(digits.zipWithNext().map { (a, b) -> b - a })
        zipped.windowed(4).forEach { sequence ->
            val diffs = sequence.map { it.second }
            if (diffs !in usedSequence) {
                usedSequence.add(diffs)
                sequences.increment(diffs, sequence.last().first)
            }
        }

        acc + listOf(digits.toList())
    }

    return sequences.values.max()
}
