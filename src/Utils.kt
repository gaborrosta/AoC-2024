import java.util.*
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
 *   Increment value in a MutableMap.
 */
fun <T : Any> MutableMap<T, Long>.increment(key: T, long: Long = 1) {
    this.compute(key) { _: T, v: Long? -> if (v == null) long else v + long }
}


/**
 *   Point in 2D.
 */
data class Point(val x: Int, val y: Int) {

    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)

    operator fun minus(other: Point): Point = Point(x - other.x, y - other.y)

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


/**
 *   Permutations.
 *   https://stackoverflow.com/questions/63433335/java-alternative-of-product-function-of-python-form-itertools
 */
fun <T> Collection<T>.product(r: Int): List<Collection<T>> {
    var result = Collections.nCopies<Collection<T>>(1, emptyList())
    for (pool in Collections.nCopies(r, LinkedHashSet(this))) {
        val temp: MutableList<Collection<T>> = ArrayList()
        for (x in result) {
            for (y in pool) {
                val z: MutableCollection<T> = ArrayList(x)
                z.add(y)
                temp.add(z)
            }
        }
        result = temp
    }
    return result
}

/**
 *   Combinations.
 */
fun <T> combinations(seed: Iterable<T>, count: Int): List<List<T>> {

    fun inner(acc: List<List<T>>, remaining: Int): List<List<T>> = when (remaining) {
        0 -> acc
        count -> inner(seed.map { s -> listOf(s) }, remaining - 1)
        else -> inner(seed.flatMap { s -> acc.map { list -> list + s } }, remaining - 1)
    }

    return inner(emptyList(), count)
}
