private data class Robot(var position: Point, val velocity: Point)

fun main() {
    val data = readFile(14).map { line ->
        val lineRegex = "p=(\\d*),(\\d*) v=(-*\\d*),(-*\\d*)".toRegex()
        val numbers = lineRegex.findAll(line).first().groupValues.drop(1)

        Robot(Point(numbers[0].toInt(), numbers[1].toInt()), Point(numbers[2].toInt(), numbers[3].toInt()))
    }

    println("Part 1: ${part1(data)}")
    println("Part 2: ${part2(data)}")
}


//Part 1
private fun part1(data: List<Robot>): Int {
    val robots = data.map { it.copy() }

    val width = 100
    val height = 102
    val noX = 50
    val noY = 51

    repeat(100) {
        robots.forEach { robot ->
            var newPosition = robot.position + robot.velocity

            if (newPosition.x > width) {
                newPosition = newPosition.left(width + 1)
            }
            if (newPosition.y > height) {
                newPosition = newPosition.up(height + 1)
            }
            if (newPosition.x < 0) {
                newPosition = newPosition.right(width + 1)
            }
            if (newPosition.y < 0) {
                newPosition = newPosition.down(height + 1)
            }

            robot.position = newPosition
        }
    }

    return robots.fold(arrayListOf(0, 0, 0, 0)) { acc, robot ->
        when {
            robot.position.x < noX && robot.position.y < noY -> acc.also { it[0]++ }
            robot.position.x < noX && robot.position.y > noY -> acc.also { it[1]++ }
            robot.position.x > noX && robot.position.y < noY -> acc.also { it[2]++ }
            robot.position.x > noX && robot.position.y > noY -> acc.also { it[3]++ }
            else -> acc
        }
    }.fold(1) { acc, num ->
        acc * num
    }
}


//Part 2
private fun part2(data: List<Robot>): Int {
    val robots = data.map { it.copy() }

    val width = 100
    val height = 102
    val noX = 50
    val noY = 51

    var time = 0
    var min = Int.MAX_VALUE
    var go = true
    while (go) {
        time++

        robots.forEach { robot ->
            var newPosition = robot.position + robot.velocity

            if (newPosition.x > width) {
                newPosition = newPosition.left(width + 1)
            }
            if (newPosition.y > height) {
                newPosition = newPosition.up(height + 1)
            }
            if (newPosition.x < 0) {
                newPosition = newPosition.right(width + 1)
            }
            if (newPosition.y < 0) {
                newPosition = newPosition.down(height + 1)
            }

            robot.position = newPosition
        }

        val newSafetyFactor = robots.fold(arrayListOf(0, 0, 0, 0)) { acc, robot ->
            when {
                robot.position.x < noX && robot.position.y < noY -> acc.also { it[0]++ }
                robot.position.x < noX && robot.position.y > noY -> acc.also { it[1]++ }
                robot.position.x > noX && robot.position.y < noY -> acc.also { it[2]++ }
                robot.position.x > noX && robot.position.y > noY -> acc.also { it[3]++ }
                else -> acc
            }
        }.fold(1) { acc, num ->
            acc * num
        }

        if (newSafetyFactor < min) {
            println("----------------------------------------------------------------------")
            for (x in 0..width) {
                for (y in 0..height) {
                    val p = Point(x, y)
                    if (robots.any { it.position == p }) {
                        print("#")
                    } else {
                        print(" ")
                    }
                }
                println()
            }

            go = readln() != "ok"
            min = newSafetyFactor
            println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        }
    }

    return time
}
