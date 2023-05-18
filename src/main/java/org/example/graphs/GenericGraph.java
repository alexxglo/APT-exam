package org.example.graphs;

import java.util.*;

public class GenericGraph {
    private int numberOfVertices;
    private List<List<Edge>> adj;

    public GenericGraph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        adj = new ArrayList<>(numberOfVertices);
        for (int i = 0; i < numberOfVertices; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, int weight) {
        adj.get(u).add(new Edge(v, weight));
    }

    public List<Edge> getNeighbors(int u) {
        return adj.get(u);
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public static class Edge {
        private int v; // destination vertex
        private int weight;

        public Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        public int getV() {
            return v;
        }


        public int getWeight() {
            return weight;
        }
    }

    // check for intersecting vertex
    public int isIntersecting(Boolean[] s_visited,
                              Boolean[] t_visited)
    {
        for (int i = 0; i < numberOfVertices; i++) {
            // if a vertex is visited by both front
            // and back BFS search return that node
            // else return -1
            if (s_visited[i] && t_visited[i])
                return i;
        }
        return -1;
    }

    // Print the path from source to target
    public void printPath(int[] s_parent, int[] t_parent,
                          int s, int t, int intersectNode)
    {
        LinkedList<Integer> path
                = new LinkedList<Integer>();
        path.add(intersectNode);
        int i = intersectNode;
        while (i != s) {
            path.add(s_parent[i]);
            i = s_parent[i];
        }
        Collections.reverse(path);
        i = intersectNode;
        while (i != t) {
            path.add(t_parent[i]);
            i = t_parent[i];
        }

        System.out.println("*****Path*****");
        for (int it : path)
            System.out.print(it + " ");
        System.out.println();
    }
}
