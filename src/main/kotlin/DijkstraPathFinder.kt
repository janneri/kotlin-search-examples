import java.util.*

class DijkstraPathFinder(private val grid: Grid): PathFinder {
    private data class PathWithCost(val path: Path, val cost: Int) : Comparable<PathWithCost> {
        override fun compareTo(other: PathWithCost): Int =
            this.cost.compareTo(other.cost)
    }

    override fun findPath(startPos: Position, endPos: Position): Path? {
        val seen = mutableSetOf<Position>()
        val queue = PriorityQueue<PathWithCost>().apply { add(PathWithCost(listOf(startPos), 0)) }

        while (queue.isNotEmpty()) {
            val (currentPath, currentCost) = queue.poll()
            val currentPosition = currentPath.last()

            if (currentPosition == endPos) {
                return currentPath
            }

            if (currentPosition !in seen) {
                seen += currentPosition

                grid.getNeighbors(currentPosition)
                    .filter { it !in seen }
                    .forEach { neighbour ->
                        queue.add(PathWithCost(currentPath + neighbour, currentCost + 1))
                    }
            }
        }
        return null
    }
}