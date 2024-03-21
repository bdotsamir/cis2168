package inclass320;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<Key extends Comparable<Key>> implements Iterable<Key> {
  private Key[] pq; // a max-heap in pq[1...n], pq[0] is unused
  private int n;    // number of items on this priority queue

  /* Constructor: creates a MaxPQ of size 1 */
  public MaxPQ() {
    this(1);
  }

  /* Constructor: creates a MaxPQ of capacity size */
  @SuppressWarnings("unchecked")
  public MaxPQ(int capacity) {
    pq = (Key[]) new Comparable[capacity + 1];
  }

  /* Constructor: builds a MaxPQ from an array of Comparable items */
  @SuppressWarnings("unchecked")
  public MaxPQ(Key[] keys) {
    n = keys.length;
    pq = (Key[]) new Comparable[n + 1];
    for (int i = 0; i < n; i++)
      pq[i + 1] = keys[i];
    // YOUR CODE HERE
    for (int k = n / 2; k >= 1; k--) {
      sink(k);
    }
  }

  // Adds a new key to this priority queue.
  public void add(Key x) {
    // double size of array if necessary
    if (n == pq.length - 1) resize(2 * pq.length);

    // add x, and percolate it up to maintain heap invariant
    pq[++n] = x;
    swim(n);
  }

  // Removes and returns the largest key on this priority queue.
  public Key extractMax() {
    if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
    Key max = pq[1];
    // YOUR CODE HERE
    this.exch(1, n--);
    this.sink(1);
    if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
    return max;
  }

  // Returns the largest key on this priority queue without removing it
  public Key max() {
    if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
    return pq[1];
  }

  // Is this priority queue empty?
  public boolean isEmpty() {
    return n == 0;
  }

  // How many items are in this priority queue?
  public int size() {
    return n;
  }

  /* Helper methods to restore the heap invariant */
  private void swim(int k) {
    // YOUR CODE HERE
    // so long as k is not the ROOT root node && k's child is less than k
    while(k > 1 && this.less(k / 2, k)) {
      this.exch(k / 2, k); // exchange k's child with k
      k = k / 2; // become the parent
    }
  }

  private void sink(int k) {
    // YOUR CODE HERE
    while(k * 2 <= this.n) {
      int j = k * 2; // left child
      if(j < n && this.less(j , k + 1)) j++; // k + 1 = right child
      if(!this.less(k, j)) break; // root is not less than child
      exch(k, j); // exchange root & child
    }
  }

  /* Helper methods swaps and comparisons */
  private void exch(int i, int j) {
    Key swap = pq[i];
    pq[i] = pq[j];
    pq[j] = swap;
  }

  private boolean less(int i, int j) {
    return pq[i].compareTo(pq[j]) < 0;
  }

  /* A helper method for resizing an array (used when pq[] is full) */
  @SuppressWarnings("unchecked")
  private void resize(int capacity) {
    assert capacity > n;
    Key[] temp = (Key[]) new Comparable[capacity];
    for (int i = 1; i <= n; i++)
      temp[i] = pq[i];
    pq = temp;
  }

  public @NotNull Iterator<Key> iterator() {
    return new HeapIterator();
  }

  private class HeapIterator implements Iterator<Key> {
    private MaxPQ<Key> copy; // create a new pq

    // make a copy of pq:
    public HeapIterator() {
      copy = new MaxPQ<Key>(size());
      for (int i = 1; i <= n; i++)
        copy.add(pq[i]);
    }

    public boolean hasNext() {
      return !copy.isEmpty();
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public Key next() {
      if (!hasNext()) throw new NoSuchElementException();
      return copy.extractMax();
    }
  }

  /* Unit tests the MaxPQ data type. */
  public static void main(String[] args) {
    MaxPQ<Character> pq = new MaxPQ<>();
    Character[] items = {'P', 'Q', 'E', '-', 'X', 'A', 'M', '-', 'P', 'L', 'E', '-'};
    for (Character item : items) {
      if (!item.equals('-')) pq.add(item);
      else if (!pq.isEmpty()) System.out.print(pq.extractMax() + " ");
    }
    System.out.println("(" + pq.size() + " left on pq)");
  }
}
