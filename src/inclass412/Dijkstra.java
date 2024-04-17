package inclass412;

import java.util.*;

public class Dijkstra {
  private DirectedEdge[] edgeTo;
  private double distTo[];
  Set<Integer> Q = new HashSet<>();  // vertices not on the shortest path

  public Dijkstra(WeightedDiGraph G, int source) {
    edgeTo = new DirectedEdge[G.V()];
    distTo = new double[G.V()];

    // initialization
    for (int v = 0; v < G.V(); v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
      Q.add(v);
    }
    distTo[source] = 0;

    // remove u with smallest distTo[u] from Q & relax edges leaving u
    while (!Q.isEmpty()) {
      int u = closestVertex(Q);
      Q.remove(u);
      for (DirectedEdge e : G.adj(u))
        relax(e);
    }
  }

  // relaxes edge e
  private void relax(DirectedEdge e) {
    int u = e.from(), v = e.to();
    if (distTo[v] > distTo[u] + e.weight()) {
      distTo[v] = distTo[u] + e.weight();
      edgeTo[v] = e;
    }
  }

  // finds the vertex u in Q with the smallest distTo[u]
  private int closestVertex(Set<Integer> Q) {
    double minDist = Double.POSITIVE_INFINITY;
    int v = -1;
    for (int x : Q)
      if (distTo[x] <= minDist) {
        minDist = distTo[x];
        v = x;
      }
    return v;
  }

  public boolean hasPathTo(int v) {
    return distTo[v] < Double.POSITIVE_INFINITY;
  }

  public double distTo(int v) {
    return distTo[v];
  }

  public Iterable<DirectedEdge> pathTo(int v) {
    if (!hasPathTo(v)) return null;
    ArrayDeque<DirectedEdge> path = new ArrayDeque<DirectedEdge>();
    for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
      path.push(e);
    }
    return path;
  }
}
