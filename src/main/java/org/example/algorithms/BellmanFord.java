package org.example.algorithms;

import org.example.graphs.GenericGraph;

import java.util.Arrays;
import java.util.Stack;

public class BellmanFord {
    private GenericGraph graph;
    private int[] distTo;
    private int[] parent;
    private boolean hasNegativeCycle;

    public BellmanFord(GenericGraph graph, int source) {
        this.graph = graph;
        distTo = new int[graph.getNumberOfVertices()];
        parent = new int[graph.getNumberOfVertices()];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        distTo[source] = 0;

        for (int i = 1; i < graph.getNumberOfVertices(); i++) {
            for (int u = 0; u < graph.getNumberOfVertices(); u++) {
                for (GenericGraph.Edge e : graph.getNeighbors(u)) {
                    int v = e.getV();
                    int w = e.getWeight();
                    if (distTo[u] != Integer.MAX_VALUE && distTo[v] > distTo[u] + w) {
                        distTo[v] = distTo[u] + w;
                        parent[v] = u;
                    }
                }
            }
        }

        // Check for negative cycles
        for (int u = 0; u < graph.getNumberOfVertices(); u++) {
            for (GenericGraph.Edge e : graph.getNeighbors(u)) {
                int v = e.getV();
                int w = e.getWeight();
                if (distTo[u] != Integer.MAX_VALUE && distTo[v] > distTo[u] + w) {
                    hasNegativeCycle = true;
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return distTo[v] != Integer.MAX_VALUE;
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int u = v; u != -1; u = parent[u]) {
            path.push(u);
        }
        return path;
    }

    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }
}
