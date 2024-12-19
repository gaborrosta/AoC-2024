import kotlin.math.pow

fun main() {
    val data = readFile(17)

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}

fun runProgram(registers: Triple<Long, Long, Long>, instructions: List<List<Int>>): List<Int> {
    var (regA, regB, regC) = registers
    val length = instructions.size

    val result = arrayListOf<Int>()

    var pointer = 0
    while (pointer < length) {
        val instruction = instructions[pointer][0]
        val operand = instructions[pointer][1]

        when (instruction) {
            0 -> {
                val combo = when (operand) {
                    0, 1, 2, 3 -> operand.toLong()
                    4 -> regA
                    5 -> regB
                    6 -> regC
                    7 -> throw Exception("Reserved combo operand")
                    else -> throw Exception("Unknown combo operand")
                }

                val numerator = regA
                val denominator = 2F.pow(combo.toFloat()).toLong()

                regA = (numerator / denominator)
                pointer++
            }

            1 -> {
                regB = (regB xor operand.toLong())
                pointer++
            }

            2 -> {
                val combo = when (operand) {
                    0, 1, 2, 3 -> operand.toLong()
                    4 -> regA
                    5 -> regB
                    6 -> regC
                    7 -> throw Exception("Reserved combo operand")
                    else -> throw Exception("Unknown combo operand")
                }

                regB = combo % 8
                pointer++
            }

            3 -> {
                if (regA != 0L) {
                    pointer = operand / 2
                } else {
                    pointer++
                }
            }

            4 -> {
                regB = (regB xor regC)
                pointer++
            }

            5 -> {
                val combo = when (operand) {
                    0, 1, 2, 3 -> operand.toLong()
                    4 -> regA
                    5 -> regB
                    6 -> regC
                    7 -> throw Exception("Reserved combo operand")
                    else -> throw Exception("Unknown combo operand")
                }

                result.add((combo % 8).toInt())
                pointer++
            }

            6 -> {
                val combo = when (operand) {
                    0, 1, 2, 3 -> operand.toLong()
                    4 -> regA
                    5 -> regB
                    6 -> regC
                    7 -> throw Exception("Reserved combo operand")
                    else -> throw Exception("Unknown combo operand")
                }

                val numerator = regA
                val denominator = 2F.pow(combo.toFloat()).toLong()

                regB = (numerator / denominator)
                pointer++
            }

            7 -> {
                val combo = when (operand) {
                    0, 1, 2, 3 -> operand.toLong()
                    4 -> regA
                    5 -> regB
                    6 -> regC
                    7 -> throw Exception("Reserved combo operand")
                    else -> throw Exception("Unknown combo operand")
                }

                val numerator = regA
                val denominator = 2F.pow(combo.toFloat()).toLong()

                regC = (numerator / denominator)
                pointer++
            }
        }
    }

    return result
}


//Part 1
private fun part1(data: List<String>): String {
    val regA = data[0].split(": ")[1].toLong()
    val regB = data[1].split(": ")[1].toLong()
    val regC = data[2].split(": ")[1].toLong()

    val input = data[4].split(": ")[1].split(",").windowed(2, 2).map { l -> l.map { n -> n.toInt() } }

    return runProgram(Triple(regA, regB, regC), input).joinToString(",")
}


//Part 2
private fun part2(data: List<String>): Int {
    //I did not manage to solve this part on my own, I needed help from others' solutions.
    return -1
}
