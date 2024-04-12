package inclass410;
/******************************************************************************
 *  Compilation:  javac Graph.java        
 *  Execution:    java Graph input.txt
 *  Dependencies: In.java
 *  Data files:   tinyG.txt, mediumG.txt, largeG.txt
 *
 *  A graph, implemented using an array of sets.
 *  Parallel edges and self-loops allowed.
 *
 *  % java Graph tinyG.txt
 *  13 vertices, 13 edges 
 *  0: 6 2 1 5 
 *  1: 0 
 *  2: 0 
 *  3: 5 4 
 *  4: 5 6 3 
 *  5: 3 4 0 
 *  6: 0 4 
 *  7: 8 
 *  8: 7 
 *  9: 11 10 12 
 *  10: 9 
 *  11: 9 12 
 *  12: 11 9 
 *
 *  % java Graph mediumG.txt
 *  250 vertices, 1273 edges 
 *  0: 225 222 211 209 204 202 191 176 163 160 149 114 97 80 68 59 58 49 44 24 15 
 *  1: 220 203 200 194 189 164 150 130 107 72 
 *  2: 141 110 108 86 79 51 42 18 14 
 *  ...
 *
 ******************************************************************************/

import util.In;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The Graph class represents an undirected graph of vertices named 0 through V – 1.
 * It supports the following two primary operations:
 * <p>
 * 1) add an edge to the graph,
 * 2) iterate over all the vertices adjacent to a vertex.
 * <p>
 * It also provides methods
 * for returning the number of vertices V and the number of edges E.
 * Parallel edges and self-loops are permitted.
 * <p>
 * This implementation uses an adjacency-lists representation.
 * <p>
 * All operations take constant time (in the worst case) except
 * iterating over the vertices adjacent to a given vertex, which takes
 * time proportional to the number of such vertices.
 */
public class Graph {

  private final int V;  // number of vertices in this graph
  private int E;        // number of edges in this graph
  private ArrayList<Integer>[] adj; // V adjacency lists (one for each vertex)

  //Constructor. Initializes an empty graph with V vertices and 0 edges.
  public Graph(int V) {
    this.V = V;
    this.E = 0;
    adj = new ArrayList[V];  // V adjacency lists (one for each vertex)
    for (int v = 0; v < V; v++) {
      adj[v] = new ArrayList<>();  // the adjacency list for vertex v
    }
  }

  // Constructor. Initializes a graph from the specified input stream.
  // The format is the number of vertices V,
  // followed by the number of edges E,
  // followed by E pairs of vertices, with each entry separated by whitespace.
  public Graph(In in) {
    if (in == null) throw new IllegalArgumentException("argument is null");
    try {
      this.V = in.readInt();
      if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
      adj = new ArrayList[V];
      for (int v = 0; v < V; v++) {
        adj[v] = new ArrayList();
      }
      int E = in.readInt();
      if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
      for (int i = 0; i < E; i++) {
        int v = in.readInt();
        int w = in.readInt();
        addEdge(v, w);
      }
    } catch (NoSuchElementException e) {
      throw new IllegalArgumentException("invalid input format in Graph constructor", e);
    }
  }


  // Returns the number of vertices in this graph.
  public int V() {
    return V;
  }

  // Returns the number of edges in this graph.
  public int E() {
    return E;
  }


  // Adds the undirected edge v-w to this graph.
  public void addEdge(int v, int w) {
    E++;
    adj[v].add(w);
    adj[w].add(v);
  }


  // Returns the vertices adjacent to vertex v.
  public Iterable<Integer> adj(int v) {
    return adj[v];
  }

  // Returns the degree of vertex v.
  public int degree(int v) {
    return adj[v].size();
  }


  // Returns a string representation of this graph.
  // (the number of vertices V, followed by the number of edges E.)
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append(V).append(" vertices, ").append(E).append(" edges \n");
    for (int v = 0; v < V; v++) {
      s.append(v).append(": ");
      for (int w : adj[v])
        s.append(w).append(" ");
      s.append("\n");
    }
    return s.toString();
  }


  // Tests the Graph data type.
  public static void main(String[] args) {
    Graph G = new Graph(new In("mediumG.txt"));
    System.out.println(G);
  }

}
