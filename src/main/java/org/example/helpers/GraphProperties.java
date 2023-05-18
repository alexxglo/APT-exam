package org.example.helpers;

import org.example.graphs.GenericGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class GraphProperties {
    public static void printGraph(List<List<Integer>> graph) {
        System.out.println("Number of edges: " + graph.size());
        System.out.println("Edges:");
        for (List<Integer> e : graph) {
            System.out.println(e.get(0) + " " + e.get(1) + " " + e.get(2));
        }
    }

    public static GenericGraph readGraphFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int n = scanner.nextInt(); // number of nodes
            int m = scanner.nextInt(); // number of edges

            GenericGraph g = new GenericGraph(n);

            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int w = 0; // default weight is 0
                if (scanner.hasNextInt()) {
                    w = scanner.nextInt();
                }
                g.addEdge(u, v, w);
            }
            scanner.close();
            return g;
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
