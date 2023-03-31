# kotlin-search-examples

This repo contains search implementation examples with Kotlin.

## Basic Data Types
All implementations use [basic data types such as the Grid and the Position](src/main/kotlin/DataTypes.kt):
- The Grid contains the map / graph
- The Position represents x, y -coordinates on the map

## Search algorithm implementations

### Dijkstra

Note! There are several variants to Dijkstra. This one uses a priority queue.

[Dijkstra with PriorityQueue](src/main/kotlin/DijkstraPathFinder.kt)

### Custom BFS

This approach is good for solving any puzzle that has a gamestate and moves. The idea is
From any list of gamestates
1. Apply all available moves
1. Return a new list of gamestates
1. If you need to optimize performance, remove the worst gamestates 
1. Check if you reached the goal

[Custom BFS implementation](src/main/kotlin/BFSPathFinder.kt)

### A*

The A* uses a heuristic function to find the next best looking neighbor. 
hat's why it most likely finds the path quicker then the Dijkstra (and the custom bfs).

[A* implementation](src/main/kotlin/AStarPathFinder.kt)
