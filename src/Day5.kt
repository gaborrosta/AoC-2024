import kotlin.math.floor

fun main() {
    val (rules, data) = readFile(5).joinToString("*").split("**", limit = 2).map { it.split("*") }

    println("Part 1: ${part1(rules, data)}")
    println("Part 2: ${part2(rules, data)}")
}


//Part 1
private fun part1(rules: List<String>, data: List<String>): Int {
    return data.fold(0) { acc, r ->
        val row = r.split(",")

        var ok = true
        for (iFirst in row.indices) {
            val first = row[iFirst]
            for (iSecond in iFirst..<row.size) {
                val second = row[iSecond]
                if ("$second|$first" in rules) {
                    ok = false
                }
            }
        }

        if (ok) {
            acc + row[floor((row.size / 2).toDouble()).toInt()].toInt()
        } else {
            acc
        }

    }
}


//Part 2
private fun part2(rules: List<String>, data: List<String>): Int {
    return data.filter { r ->
        val row = r.split(",")

        var ok = true
        for (iFirst in row.indices) {
            val first = row[iFirst]
            for (iSecond in iFirst..<row.size) {
                val second = row[iSecond]
                if ("$second|$first" in rules) {
                    ok = false
                }
            }
        }

        !ok
    }.fold(0) { acc, r ->
        val row = ArrayList(r.split(","))

        var ok = false
        while (!ok) {
            var firstWrong = Pair(-1,-1)
            found@ for (iFirst in row.indices) {
                val first = row[iFirst]
                for (iSecond in iFirst..<row.size) {
                    val second = row[iSecond]
                    if ("$second|$first" in rules) {
                        firstWrong = iFirst to iSecond
                        break@found
                    }
                }
            }

            if (firstWrong == Pair(-1, -1)) {
                ok = true
            } else {
                row[firstWrong.first] = row[firstWrong.second].also { row[firstWrong.second] = row[firstWrong.first] }
            }
        }

        acc + row[floor((row.size / 2).toDouble()).toInt()].toInt()
    }
}
