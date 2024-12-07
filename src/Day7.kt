fun main() {
    val data = readFile(7)

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


//Part 1
private fun part1(data: List<String>): Long {
    return data.sumOf {
        val n = it.split(":")[0].toLong()
        val numbers = it.split(": ")[1].split(" ").map(String::toLong)

        listOf("+", "*")
            .product(numbers.size - 1)
            .forEach { permutation ->
                var result = numbers[0]
                var i = 1
                val iterator = permutation.iterator()

                while (i < numbers.size) {
                    when (iterator.next()) {
                        "*" -> {
                            result *= numbers[i]
                        }

                        "+" -> {
                            result += numbers[i]
                        }

                        else -> throw Exception("Unknown operation")
                    }

                    i++
                }

                if (result == n) return@sumOf n
            }

        0
    }
}


//Part 2
private fun part2(data: List<String>): Long {
    return data.sumOf {
        val n = it.split(":")[0].toLong()
        val numbers = it.split(": ")[1].split(" ").map(String::toLong)

        listOf("+", "*", "||")
            .product(numbers.size - 1)
            .forEach { permutation ->
                var result = numbers[0]
                var i = 1
                val iterator = permutation.iterator()

                while (i < numbers.size) {
                    when (iterator.next()) {
                        "*" -> {
                            result *= numbers[i]
                        }

                        "+" -> {
                            result += numbers[i]
                        }

                        "||" -> {
                            result = (result.toString() + numbers[i].toString()).toLong()
                        }

                        else -> throw Exception("Unknown operation")
                    }

                    i++
                }

                if (result == n) return@sumOf n
            }

        0
    }
}
