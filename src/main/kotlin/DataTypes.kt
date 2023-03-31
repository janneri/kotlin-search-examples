import kotlin.math.abs

enum class Direction(val dx: Int, val dy: Int) {
    LEFT(-1, 0), RIGHT(1, 0), UP(0, -1), DOWN(0, 1)
}

data class Position(val x: Int, val y: Int) {
    fun move(direction: Direction, amount: Int = 1) =
        Position(x + amount * direction.dx, y + amount * direction.dy)

    override fun toString(): String = "($x, $y)"
}

typealias Path = List<Position>

class Grid(private val rows: List<String>) {
    private val width = rows[0].length
    private val height = rows.size

    /** Returns all valid/movable positions from the given position. */
    fun getNeighbors(fromPos: Position): List<Position> =
        Direction.values()
            .map { fromPos.move(it) }
            .filter { position -> isValidPos(position) }

    // Implement this based on your grid. In this example a wall marked with X. Cannot go through walls...
    private fun isValidPos(position: Position): Boolean =
        position.y in 0 until height && position.x in 0 until width && !isWall(position)

    private fun isWall(position: Position) = rows[position.y][position.x] == 'X'

    /** Assuming the moving cost is 1 here. Could be something else of course... */
    fun moveCost(fromPos: Position, toPosition: Position) = 1

    /** Only needed for the A-star-algorithm */
    fun heuristicDistance(startPos: Position, endPos: Position) =
        abs(endPos.x - startPos.x) + abs(endPos.y - startPos.y)
}

interface PathFinder {
    /** Returns the shortest path or null if no path is found. */
    fun findPath(startPos: Position, endPos: Position): Path?
}

