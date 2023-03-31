class AStarPathFinder(private val grid: Grid): PathFinder {
    fun generatePath(currentPos: Position, cameFrom: Map<Position, Position>): Path {
        val path = mutableListOf(currentPos)
        var current = currentPos
        while (cameFrom.containsKey(current)) {
            current = cameFrom.getValue(current)
            path.add(0, current)
        }
        return path.toList()
    }

    override fun findPath(startPos: Position, endPos: Position): Path? {
        val open = mutableSetOf(startPos)
        val seen = mutableSetOf<Position>()
        val cameFrom = mutableMapOf<Position, Position>()
        val costFromStart = mutableMapOf(startPos to 0)
        val estimatedTotalCost = mutableMapOf(startPos to grid.heuristicDistance(startPos, endPos))

        while (open.size > 0) {
            // get the next position, that looks the best (has the smallest estimated cost to goal)
            val currentPos = open.minBy { estimatedTotalCost.getValue(it) }

            if (currentPos == endPos) {
                return generatePath(currentPos, cameFrom)
            }

            open.remove(currentPos)
            seen.add(currentPos)

            grid.getNeighbors(currentPos)
                .filter { it !in seen }
                .forEach { neighbour ->
                    val score = costFromStart.getValue(currentPos) + grid.moveCost(currentPos, neighbour)
                    if (score < costFromStart.getOrDefault(neighbour, Int.MAX_VALUE)) {
                        open.add(neighbour)
                        cameFrom[neighbour] = currentPos
                        costFromStart[neighbour] = score
                        estimatedTotalCost[neighbour] = score + grid.heuristicDistance(neighbour, endPos)
                    }
                }
        }
        return null
    }
}