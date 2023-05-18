package org.example.helpers;

import org.example.graphs.GenericGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EuclideanHeuristic {

    List<NodeCoordinates> coord;

    public NodeCoordinates getCoord(int u) {
        return coord.get(u);
    }

    public EuclideanHeuristic(String filename) {
        coord = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int n = scanner.nextInt(); // number of nodes

            for (int i = 0; i < n; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                NodeCoordinates nodeCoordinates = new NodeCoordinates(x, y);
                coord.add(nodeCoordinates);
            }
            scanner.close();
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static class NodeCoordinates {
      int x;
      int y;

      public NodeCoordinates(int x, int y) {
          this.x = x;
          this.y = y;
      }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
