package org.example.algorithms;

import org.example.graphs.GenericGraph;
import org.example.helpers.EuclideanHeuristic;

import java.util.*;

public class AStar {
    private GenericGraph graph;
    private int source;
    private int target;
    private int[] parent;
    private int[] dist;
    private boolean[] visited;
    private EuclideanHeuristic euclideanHeuristic;

    public AStar(GenericGraph graph, EuclideanHeuristic euclideanHeuristic, int source, int target) {
        this.graph = graph;
        this.source = source;
        this.target = target;
        this.euclideanHeuristic = euclideanHeuristic;
        parent = new int[graph.getNumberOfVertices()];
        dist = new int[graph.getNumberOfVertices()];
        visited = new boolean[graph.getNumberOfVertices()];
    }

    public void runAStar() {
        // Initialize distances to infinity and set source distance to 0
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Create a priority queue and add the source vertex
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(u -> dist[u] + heuristic(u, target)));
        pq.offer(source);

        // A* algorithm
        while (!pq.isEmpty()) {
            // Extract the vertex with the minimum f-score
            int u = pq.poll();
            visited[u] = true;

            // If we have reached the target vertex, break out of the loop
            if (u == target) {
                break;
            }

            // Iterate through the neighbors of the current vertex
            for (GenericGraph.Edge edge : graph.getNeighbors(u)) {
                int v = edge.getV();
                int w = edge.getWeight();

                // Skip if v has already been visited
                if (visited[v]) {
                    continue;
                }

                // Calculate the tentative distance to v
                int tentativeDist = dist[u] + w;

                // If this is a better path to v, update the parent and distance
                if (tentativeDist < dist[v]) {
                    dist[v] = tentativeDist;
                    parent[v] = u;

                    // Add v to the priority queue with its updated f-score
                    pq.offer(v);
                }
            }
        }
    }

    public int[] getParent() {
        return parent;
    }

    public int[] getDist() {
        return dist;
    }

    // Heuristic function: Euclidean distance between two vertices
    private int heuristic(int u, int v) {
        int x1 = euclideanHeuristic.getCoord(u).getX();
        int y1 = euclideanHeuristic.getCoord(u).getY();
        int x2 = euclideanHeuristic.getCoord(v).getX();
        int y2 = euclideanHeuristic.getCoord(v).getY();

        return (int) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

    }
}

/*
The AStar class takes a WeightedGraph object, a source vertex, and a target vertex as input,
and provides a runAStar method that runs the A* algorithm with the provided heuristic function.

The algorithm starts by initializing the distances to all vertices to infinity, except for the source vertex
which is set to 0. We also create a priority queue pq and add the source vertex to it.
The priority queue is sorted by the sum of the distance and heuristic estimate of each vertex.

The A* algorithm iteratively extracts the vertex with the minimum f-score from the priority queue. If the target vertex is reached,
we break out of the loop.
Otherwise, we iterate through the neighbors of the current vertex, and if a neighbor has not been visited yet,
we calculate the tentative distance to that neighbor, and if it is better than the current distance, weupdate the parent and distance, and add the neighbor to the priority queue with its updated f-score.

The heuristic function used in this implementation is the Euclidean distance between two vertices,
but it can be replaced with any other suitable heuristic function depending on the problem.

The getParent and getDist methods can be used to retrieve the parent array and distance array respectively, after running the algorithm.
 */