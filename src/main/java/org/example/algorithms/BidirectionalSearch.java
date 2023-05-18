package org.example.algorithms;

import org.example.graphs.GenericGraph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;

public class BidirectionalSearch {

    private int numberOfVertices;
    private GenericGraph g;

    public BidirectionalSearch(GenericGraph g) {
        numberOfVertices = g.getNumberOfVertices();
        this.g = g;
        for (int i = 0; i < g.getNumberOfVertices(); i++) {
            for (GenericGraph.Edge e : g.getNeighbors(i)) {
                g.addEdge(e.getV(), i, 0);
            }
        }
    }

    public void bfs(Queue<Integer> queue, Boolean[] visited,
                    int[] parent) {
        int current = queue.poll();
        for (GenericGraph.Edge i : g.getNeighbors(current)) {
            // If adjacent vertex is not visited earlier
            // mark it visited by assigning true value
            if (!visited[i.getV()]) {
                // set current as parent of this vertex
                parent[i.getV()] = current;

                // Mark this vertex visited
                visited[i.getV()] = true;

                // Push to the end of queue
                queue.add(i.getV());
            }
        }
    }

    // check for intersecting vertex
    public int isIntersecting(Boolean[] s_visited,
                              Boolean[] t_visited) {
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
                          int s, int t, int intersectNode) {
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

    // Method for bidirectional searching
    public int biDirSearch(int s, int t) {
        // Booleanean array for BFS started from
        // source and target(front and backward BFS)
        // for keeping track on visited nodes
        Boolean[] s_visited = new Boolean[numberOfVertices];
        Boolean[] t_visited = new Boolean[numberOfVertices];

        // Keep track on parents of nodes
        // for front and backward search
        int[] s_parent = new int[numberOfVertices];
        int[] t_parent = new int[numberOfVertices];

        // queue for front and backward search
        Queue<Integer> s_queue = new LinkedList<Integer>();
        Queue<Integer> t_queue = new LinkedList<Integer>();

        int intersectNode = -1;

        // necessary initialization
        for (int i = 0; i < numberOfVertices; i++) {
            s_visited[i] = false;
            t_visited[i] = false;
        }

        s_queue.add(s);
        s_visited[s] = true;

        // parent of source is set to -1
        s_parent[s] = -1;

        t_queue.add(t);
        t_visited[t] = true;

        // parent of target is set to -1
        t_parent[t] = -1;

        while (!s_queue.isEmpty() && !t_queue.isEmpty()) {
            // Do BFS from source and target vertices
            bfs(s_queue, s_visited, s_parent);
            bfs(t_queue, t_visited, t_parent);

            // check for intersecting vertex
            intersectNode
                    = isIntersecting(s_visited, t_visited);

            // If intersecting vertex is found
            // that means there exist a path
            if (intersectNode != -1) {
                System.out.printf(
                        "Path exist between %d and %d\n", s, t);
                System.out.printf("Intersection at: %d\n",
                        intersectNode);

                // print the path and exit the program
                printPath(s_parent, t_parent, s, t,
                        intersectNode);
                System.exit(0);
            }
        }
        return -1;
    }

}
