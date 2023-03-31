class BFSPathFinder(private val grid: Grid): PathFinder {
    private fun nextPaths(currentPaths: List<Path>, visited: MutableSet<Position>): List<Path> {
        val newPaths = currentPaths.map { path ->
            val nextAvailable = grid.getNeighbors(path.last()).filter { !visited.contains(it) }
            if (nextAvailable.isEmpty()) emptyList<Path>()
            visited.addAll(nextAvailable)
            nextAvailable.map { path + it }
        }.filter { it.isNotEmpty() }.flatten()

        return newPaths
    }

    override fun findPath(startPos: Position, endPos: Position): Path? {
        if (startPos == endPos) return listOf(startPos)

        var currentPaths = listOf(listOf(startPos))
        val visited = mutableSetOf(startPos)

        for (steps in 1 until 1000) {
            val nextPaths = nextPaths(currentPaths, visited)
            nextPaths.forEach {
                if (it.last() == endPos) {
                    return it
                }
                visited.add(it.last())
            }
            currentPaths = nextPaths
        }
        return null
    }
}