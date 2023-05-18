package org.example.algorithms;

import org.example.graphs.GenericGraph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


public class DijkstraSearch {
    public static int[] dijkstra(GenericGraph graph, int source) {
        int n = graph.getNumberOfVertices();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> dist[i]));
        pq.add(source);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            for (GenericGraph.Edge edge : graph.getNeighbors(u)) {
                int v = edge.getV();
                int alt = dist[u] + edge.getWeight();
                if (alt < dist[v]) {
                    dist[v] = alt;
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }
        return dist;
    }
}

/*
The dijkstra method takes a WeightedGraph object and a source vertex as inputs,
 and returns an array of shortest distances from the source to every other vertex in the graph.

The algorithm initializes all distances to Integer.MAX_VALUE except for the source vertex, which is set to 0.
It then uses a priority queue to process vertices in order of increasing distance from the source,
updating the distances of neighboring vertices if a shorter path is found.

Note that this implementation assumes that all edge weights are non-negative.
If the graph contains negative edge weights, you should use a different algorithm such as the Bellman-Ford algorithm instead.
 */