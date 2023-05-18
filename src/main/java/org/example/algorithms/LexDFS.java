package org.example.algorithms;

import org.example.graphs.GenericGraph;

import java.util.*;

public class LexDFS {
    public static List<Integer> lexDFS(GenericGraph graph) {
        System.out.println("Starting LexDFS algorithm");
        int n = graph.getNumberOfVertices();
        boolean[] visited = new boolean[n];
        List<Integer> order = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.empty()) {
            int u = stack.peek();
            if (visited[u]) {
                stack.pop();
                continue;
            }
            visited[u] = true;
            order.add(u);
            boolean found = false;
            for (GenericGraph.Edge e : graph.getNeighbors(u)) {
                if (!visited[e.getV()]) {
                    stack.push(e.getV());
                    found = true;
                }
            }
            if (!found) {
                stack.pop();
            }
        }

        return order;
    }
}
