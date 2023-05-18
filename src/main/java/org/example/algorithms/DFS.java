package org.example.algorithms;

import org.example.graphs.GenericGraph;


public class DFS {
    private GenericGraph graph;
    private boolean[] visited;

    public DFS(GenericGraph graph) {
        this.graph = graph;
        visited = new boolean[graph.getNumberOfVertices()];
    }

    public void dfs(int start) {
        visited[start] = true;
        System.out.print(start + " ");

        for (GenericGraph.Edge edge : graph.getNeighbors(start)) {
            int neighbor = edge.getV();
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}