package homework.graphs;

import util.In;

import java.util.ArrayList;

public class ConnectedComponents {
  private static int numberOfComponents(ArrayList<Integer>[] adj) {

    System.out.println("Test");

    // YOUR CODE HERE
    int components = 0;
    boolean[] visited = new boolean[adj.length];
    while (!allTrue(visited)) {
      int firstFalse = firstIndexOfFalse(visited);
      dfs(adj, firstFalse, visited);
      components++;
    }

    return components;
  }

  // feel free to add a helper method to make your code cleaner & modular.

  private static void dfs(ArrayList<Integer>[] adj, int v, boolean[] visited) {
    ArrayList<Integer> adjacentToThisVertex = adj[v];
    System.out.printf("What are the vertices that are connected to %d? %s\n", v, adjacentToThisVertex);

    // Mark vertex v as visited
    visited[v] = true;
    System.out.println("Iterating through those vertices...");
    for (Integer vertexAdjacentToV : adj[v]) {
      System.out.printf("Have we visited vertex %d? %s\n", vertexAdjacentToV, visited[vertexAdjacentToV]);
      if (!visited[vertexAdjacentToV]) {
//        visited[vertexAdjacentToV] = true; // Handled in recursion!
        System.out.printf("Visiting vertex %d!\n\n", vertexAdjacentToV);
        dfs(adj, vertexAdjacentToV, visited);
      }
    }
  }

  private static boolean allTrue(boolean[] list) {
    boolean isAllTrue = true;

    for (boolean e : list) {
      if (!e) {
        isAllTrue = false;
        break;
      }
    }

    return isAllTrue;
  }

  /**
   * Returns the first index of a false boolean in a given list
   *
   * @param list The list
   * @return Index of the first false, -1 otherwise
   */
  private static int firstIndexOfFalse(boolean[] list) {
    for (int i = 0; i < list.length; i++) {
      boolean val = list[i];
      if (!val) return i;
    }

    return -1;
  }

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    In in = new In("mediumG.txt");
    int n = in.readInt();
    int m = in.readInt();
    ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
    for (int i = 0; i < n; i++) {
      adj[i] = new ArrayList<>();
    }
    for (int i = 0; i < m; i++) {
      int x, y;
      x = in.readInt();
      y = in.readInt();
      adj[x - 1].add(y - 1);
      adj[y - 1].add(x - 1);
    }
    System.out.println(numberOfComponents(adj));
  }
}

