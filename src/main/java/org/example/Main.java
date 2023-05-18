package org.example;

import org.example.algorithms.*;
import org.example.graphs.GenericGraph;
import org.example.helpers.EuclideanHeuristic;
import org.example.helpers.GraphProperties;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add the filename path:");
        String filename = scanner.nextLine(); // i used src/main/resources/graph.txt

        doBiDirSearch(filename);
    }

    private static void doLexDFS(String filename) {
        GenericGraph graph = GraphProperties.readGraphFromFile(filename);
        List<Integer> results = LexDFS.lexDFS(graph);
        for (int i : results) {
            System.out.println(i + " ");
        }
    }

    private static void doBiDirSearch(String filename) { // src/main/resources/bidirectionalsearch_graph.txt -- works
        GenericGraph graph = GraphProperties.readGraphFromFile(filename);
        int source = 0;
        int target = 14;
        BidirectionalSearch bidirectionalSearch = new BidirectionalSearch(graph);
        if (bidirectionalSearch.biDirSearch(source, target) == -1){
            System.out.printf("Path does not exist between %d and %d", source, target);
        };
    }

    private static void doAlgorithmR(String filename) { // won't work probably
        GenericGraph graph = GraphProperties.readGraphFromFile(filename);
        AlgorithmR r = new AlgorithmR(graph);
        r.getSpanningTrees();
    }

    private static void doBfs(String filename) { // src/main/resources/bfs_dfs_graph.txt
        GenericGraph graph = GraphProperties.readGraphFromFile(filename);
        BFS bfs = new BFS(graph);
        bfs.bfs(2);
    }

    private static void doDfs(String filename) { // src/main/resources/bfs_dfs_graph.txt
        GenericGraph graph = GraphProperties.readGraphFromFile(filename);
        DFS dfs = new DFS(graph);
        dfs.dfs(2);
    }

    private static void doLexBFS(String filename) { // src/main/resources/lexbfs_graph.txt
        GenericGraph graph = GraphProperties.readGraphFromFile(filename);
        LexBFS lexBFS = new LexBFS(graph);
        for (int i: lexBFS.lexBfs()) {
            System.out.println(i);
        }
    }

    private static void doDijkstraSearch(String filename) { // src/main/resources/dijkstra_graph.txt
        GenericGraph graph = GraphProperties.readGraphFromFile(filename);
        int j = 0;
        int source = 0;
        for (int i : DijkstraSearch.dijkstra(graph, source)) {
            System.out.println("The distance from " + source + " to node " + j + " is: " + i);
            j++;
        }

    }

    private static void doBellmanFord(String filename) { // src/main/resources/bellmanford_graph.txt -- works fine
        GenericGraph graph = GraphProperties.readGraphFromFile(filename);
        int source = 0;
        BellmanFord bellmanFord = new BellmanFord(graph, source);
        int j = 0;
        for (int i = 0; i < graph.getNumberOfVertices(); i++) {
            System.out.println("Distance from " + source + " to " + i + " is: " + bellmanFord.distTo(i));
        }
    }

    private static void doAStarHeuristic(String filename) {
        GenericGraph graph = GraphProperties.readGraphFromFile(filename);
        int source = 0;
        int target = 4;
        EuclideanHeuristic euclideanHeuristic = new EuclideanHeuristic("src/main/resources/heuristics/node_coordinates.txt");
        AStar aStar = new AStar(graph, euclideanHeuristic, source, target);

    }
    // do A* HEURISTIC
}