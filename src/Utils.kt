import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 *   Read input file.
 */
fun readFile(day: Int): List<String> = Path("src/Day${day}.txt").readLines()


/**
 *   Rotates a matrix.
 */
fun <T> Collection<Collection<T>>.rotate(): List<List<T>> {
    return this.flatMap { it.withIndex() }.groupBy({ (i, _) -> i }, { (_, v) -> v }).map { (_, v) -> v.reversed() }
}


/**
 *   Point in 2D.
 */
data class Point(val x: Int, val y: Int) {

    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)

    fun inBound(minX: Int, maxX: Int, minY: Int, maxY: Int): Boolean = x in minX..maxX && y in minY..maxY
    fun inBound(maxX: Int, maxY: Int): Boolean = inBound(0, maxX, 0, maxY)

    fun rotate(angle: Double): Point {
        val s = sin(angle)
        val c = cos(angle)

        val nx = x * c - y * s
        val ny = x * s + y * c

        return Point(nx.roundToInt(), ny.roundToInt())
    }

    fun rotate(degrees: Int): Point = rotate(degrees * (Math.PI / 180.0))

}
