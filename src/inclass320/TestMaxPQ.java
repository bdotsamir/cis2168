package inclass320;

public class TestMaxPQ {

  /* Unit tests the MaxPQ data type. */
  public static void main(String[] args) {
    MaxPQ<Character> pq = new MaxPQ<>();
    Character[] items = {'P', 'Q', 'E', '-', 'X', 'A', 'M', '-', 'P', 'L', 'E', '-'};
    for (Character item : items) {
      if (!item.equals('-')) pq.add(item);
      else if (!pq.isEmpty()) System.out.print(pq.extractMax() + " ");
    }
    System.out.println("(" + pq.size() + " left on pq)");

    pq = new MaxPQ<>();
    for (Character item : items)
      if (!item.equals('-')) pq.add(item);

    System.out.print("\t\t pq contents now:\n");
    for (Character c : pq) {
      System.out.println(c);
    }
  }
}
