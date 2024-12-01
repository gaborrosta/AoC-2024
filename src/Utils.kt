import kotlin.io.path.Path
import kotlin.io.path.readLines

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
