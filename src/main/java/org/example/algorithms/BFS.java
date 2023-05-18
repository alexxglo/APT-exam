package org.example.algorithms;

import org.example.graphs.GenericGraph;

import java.util.*;

public class BFS {
    private GenericGraph graph;
    private boolean[] visited;

    public BFS(GenericGraph graph) {
        this.graph = graph;
        visited = new boolean[graph.getNumberOfVertices()];
    }

    public void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.print(curr + " ");

            for (GenericGraph.Edge edge : graph.getNeighbors(curr)) {
                int neighbor = edge.getV();
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}