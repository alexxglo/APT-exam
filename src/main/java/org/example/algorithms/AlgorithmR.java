package org.example.algorithms;

import org.example.graphs.GenericGraph;

import java.util.*;

public class AlgorithmR {
    private int numVertices;
    private int[] parent;
    private boolean[] visited;
    private GenericGraph graph;

    public AlgorithmR(GenericGraph graph) {
        this.graph = graph;
        numVertices = graph.getNumberOfVertices();
        parent = new int[numVertices];
        visited = new boolean[numVertices];
        Arrays.fill(parent, -1);
    }

    public List<GenericGraph> getSpanningTrees() {
        List<GenericGraph> spanningTrees = new ArrayList<>();
        for (int v = 0; v < numVertices; v++) {
            if (!visited[v]) {
                GenericGraph tree = new GenericGraph(numVertices);
                dfs(v, tree);
                spanningTrees.add(tree);
            }
        }
        return spanningTrees;
    }

    private void dfs(int u, GenericGraph tree) {
        visited[u] = true;
        for (GenericGraph.Edge edge : graph.getNeighbors(u)) {
            int v = edge.getV();
            int weight = edge.getWeight();
            if (!visited[v]) {
                parent[v] = u;
                dfs(v, tree);
                tree.addEdge(u, v, weight);
            } else if (parent[u] != v) {
                shortcut(u, v, weight, tree);
            }
        }
    }

    private void shortcut(int u, int v, int weight, GenericGraph tree) {
        int ancestor = lca(u, v);
        int p = parent[u];
        while (p != ancestor) {
            tree.addEdge(p, u, weight);
            p = parent[p];
        }
        p = parent[v];
        while (p != ancestor) {
            tree.addEdge(p, v, weight);
            p = parent[p];
        }
    }

    private int lca(int u, int v) {
        boolean[] visited = new boolean[numVertices];
        while (u != -1 || v != -1) {
            if (u != -1) {
                if (visited[u]) return u;
                visited[u] = true;
                u = parent[u];
            }
            if (v != -1) {
                if (visited[v]) return v;
                visited[v] = true;
                v = parent[v];
            }
        }
        return -1;
    }
    /*
    The AlgorithmR class takes a weighted graph as input and provides a getSpanningTrees method that returns a list of all spanning trees for the connected components of the graph.

The algorithm works by performing a depth-first search (DFS) from each unvisited vertex in the graph. During the DFS, we keep track of the parent of each vertex and add edges to the spanning tree accordingly. If we encounter a vertex that has already been visited and is not a parent of the current vertex, we use the shortcut method to add edges that bypass the previously visited portion of the graph. The shortcut method starts from both vertices and follows their parents until it reaches the lowest common ancestor of the two vertices, adding edges to the spanning tree along the way.

The lca method is a helper method that finds the lowest common ancestor of two vertices using a simple DFS-based approach.

Note that this implementation assumes that the graph is connected. If the graph is not connected, the getSpanningTrees method will return a list of spanning trees for each connected component.
    * */
}

