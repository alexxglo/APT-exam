package org.example.algorithms;

import org.example.graphs.GenericGraph;

import java.util.*;

public class LexBFS {
    private GenericGraph graph;
    private int[] ordering;

    public LexBFS(GenericGraph graph) {
        this.graph = graph;
        ordering = new int[graph.getNumberOfVertices()];
    }

    public int[] lexBfs() {
        int n = graph.getNumberOfVertices();
        int[] degree = new int[n];

        for (int i = 0; i < n; i++) {
            for (GenericGraph.Edge edge : graph.getNeighbors(i)) {
                int neighbor = edge.getV();
                degree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            queue.add(i);
            visited[i] = true;
        }

        int k = n - 1;
        while (!queue.isEmpty()) {
            int maxDegreeVertex = -1;
            int maxDegree = -1;
            Queue<Integer> nextQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                ordering[curr] = k--;
                for (GenericGraph.Edge edge : graph.getNeighbors(curr)) {
                    int neighbor = edge.getV();
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        degree[neighbor]--;
                        if (degree[neighbor] > maxDegree) {
                            maxDegree = degree[neighbor];
                            maxDegreeVertex = neighbor;
                        }
                        nextQueue.add(neighbor);
                    }
                }
            }
            queue = nextQueue;
            if (maxDegreeVertex != -1) {
                queue.add(maxDegreeVertex);
                visited[maxDegreeVertex] = true;
            }
        }

        return ordering;
    }
}
