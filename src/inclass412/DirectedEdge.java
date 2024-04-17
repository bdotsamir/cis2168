package inclass412;

/* represents a directed edge from u to v whose weight is weight */
public class DirectedEdge {
  private final int u;
  private final int v;
  private final double weight;

  public DirectedEdge(int u, int v, double weight) {
    this.u = u;
    this.v = v;
    this.weight = weight;
  }

  public double weight() {
    return weight;
  }

  public int from() {
    return u;
  }

  public int to() {
    return v;
  }

  public String toString() {
    return String.format("%d -> %d  %.2f", u, v, weight);
  }

  public static void main(String[] args) {
    DirectedEdge e = new DirectedEdge(21, 68, 3.14);
    System.out.println((e));
  }
}
