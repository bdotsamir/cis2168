package inclass412;

import util.In;

import java.util.ArrayList;

public class WeightedDiGraph {
  private int V;
  private int E;
  ArrayList<DirectedEdge>[] adj;

  @SuppressWarnings("unchecked")
  public WeightedDiGraph(In in) {
    this.V = in.readInt();
    int E = in.readInt();
    adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
    for (int v = 0; v < V; v++) {
      adj[v] = new ArrayList<>();
    }
    for (int i = 0; i < E; i++) {
      int u, v;
      double weight;
      u = in.readInt();
      v = in.readInt();
      weight = in.readDouble();
      addEdge(new DirectedEdge(u, v, weight));
    }
  }

  // Adds the directed edge e to this edge-weighted digraph.
  public void addEdge(DirectedEdge e) {
    int v = e.from();
    int w = e.to();
    adj[v].add(e);
    this.E++;
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  // returns the adjacency list of vertex v
  public Iterable<DirectedEdge> adj(int v) {
    return adj[v];
  }

  // returns all edges of this graph
  public Iterable<DirectedEdge> edges() {
    ArrayList<DirectedEdge> edges = new ArrayList<>();
    for (int v = 0; v < V; v++)
      edges.addAll(adj[v]);
    return edges;
  }

  public static void main(String[] args) {
    In in = new In("1000EWG.txt");
    WeightedDiGraph wg = new WeightedDiGraph(in);
    for (DirectedEdge e : wg.edges()) {
      System.out.println(e);
    }
  }
}
