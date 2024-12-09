fun main() {
    val data = readFile(9)[0]

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


//Part 1
private fun part1(data: String): Long {
    var id = 0
    val system = arrayListOf<Int>()

    for (i in data.indices) {
        if (i % 2 == 0) {
            repeat(data[i].digitToInt()) {
                system.add(id)
            }
            id++
        } else {
            repeat(data[i].digitToInt()) {
                system.add(-1)
            }
        }
    }

    for (i in system.indices.reversed()) {
        if (system[i] != -1) {
            val newPos = system.withIndex().first { it.value == -1 }.index

            if (newPos > i) break

            system[newPos] = system[i]
            system[i] = -1
        }
    }

    return system.withIndex().fold(0L) { acc, file ->
        if (file.value != -1) {
            acc + file.index * file.value
        } else {
            acc
        }
    }
}


//Part 2
private fun part2(data: String): Long {
    var id = 0
    val system = arrayListOf<Pair<Int, Int>>()

    for (i in data.indices) {
        if (i % 2 == 0) {
            system.add(data[i].digitToInt() to id)
            id++
        } else {
            system.add(data[i].digitToInt() to -1)
        }
    }

    for (i in system.indices.reversed()) {
        if (system[i].second != -1) {
            val new = system.withIndex().firstOrNull { it.value.second == -1 && it.value.first >= system[i].first }
            val old = system[i]

            if (new != null) {
                if (new.index > i) continue

                val newEmpty = new.value.first - old.first

                system[new.index] = old

                var added = false
                if (newEmpty > 0) {
                    added = true
                    system.add(new.index + 1, newEmpty to -1)
                }

                system[i + if (added) 1 else 0] = old.first to -1

                var toMerge = system.withIndex().zipWithNext().firstOrNull { (l, r) ->
                    l.value.second == -1 && r.value.second == -1
                }
                while (toMerge != null) {
                    system[toMerge.first.index] = (toMerge.first.value.first + toMerge.second.value.first) to -1
                    system.removeAt(toMerge.second.index)

                    toMerge = system.withIndex().zipWithNext().firstOrNull { (l, r) ->
                        l.value.second == -1 && r.value.second == -1
                    }
                }
            }
        }
    }

    val result = arrayListOf<Int>()
    for (i in system.indices) {
        repeat(system[i].first) {
            result.add(system[i].second)
        }
    }

    return result.withIndex().fold(0L) { acc, file ->
        if (file.value != -1) {
            acc + file.index * file.value
        } else {
            acc
        }
    }
}
