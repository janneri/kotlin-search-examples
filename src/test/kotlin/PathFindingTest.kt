import org.junit.jupiter.api.Test

class PathFindingTest {
	private val grid = Grid(listOf(
		  // 012345
			"XXXXXX", // 0
			"X____X", // 1
			"X__X_X", // 2
			"X__XXX", // 3
			"X__X_X", // 4
			"XXXXXX",
	))

	@Test
	fun bfsPathFindingWorks() {
		assertPathsAreFound(BFSPathFinder(grid))
	}

	@Test
	fun dijkstraPathFindingWorks() {
		assertPathsAreFound(DijkstraPathFinder(grid))
	}

	@Test
	fun aStarPathFindingWorks() {
		assertPathsAreFound(AStarPathFinder(grid))
	}

	// We assert that these paths are found:
	private val noMovePath = listOf(Position(1, 1))
	private val simplePath = listOf(Position(1, 1), Position(2, 1), Position(3, 1))
	private val goAroundPath = listOf(Position(1, 3), Position(1, 2), Position(2, 2), Position(2, 1), Position(3, 1), Position(4, 1), Position(4, 2))
	private val goAroundPathAlt = listOf(Position(1, 3), Position(2, 3), Position(2, 2), Position(2, 1), Position(3, 1), Position(4, 1), Position(4, 2))
	private val impossiblePath = null

	private fun assertPathsAreFound(pathFinder: PathFinder) {
		assert(noMovePath == pathFinder.findPath(Position(1, 1), Position(1, 1)))
		assert(simplePath == pathFinder.findPath(Position(1, 1), Position(3, 1)))
		assert(goAroundPathAlt == pathFinder.findPath(Position(1, 3), Position(4, 2)) ||
				goAroundPath == pathFinder.findPath(Position(1, 3), Position(4, 2)))
		assert(impossiblePath == pathFinder.findPath(Position(1, 1), Position(4, 3)))
	}

}
