package inclass221;

import org.jetbrains.annotations.NotNull;
import util.In;

import java.util.*;

/**
 * An implementation of the stack data structure.
 * supports the usual push and pop operations, along with methods for peeking
 * at the top item, testing if the stack is empty, and iterating through the
 * items in LIFO (Last In First Out) order.
 * <p>
 * This implementation uses a resizing array, which doubles the underlying array
 * when it is full and halves the underlying array when it is one-quarter full.
 * The push and pop operations take constant amortized time. The size, peek, and
 * is-empty operations take constant time in the worst case.
 */

public class Stack<E> implements Iterable<E> {
  private E[] a;         // array of items
  private int n;         // number of elements on stack

  // Initializes an empty stack.
  @SuppressWarnings({"unchecked"})
  public Stack() {
    a = (E[]) new Object[2];
    n = 0;
  }

  // Is this stack empty?
  public boolean isEmpty() {
    return n == 0;
  }

  // Returns the number of items in the stack.
  public int size() {
    return n;
  }

  // resize the underlying array holding the elements
  private void resize(int capacity) {
    assert capacity >= n;
    a = Arrays.copyOf(a, capacity);
  }

  // Adds the e to this stack.
  public void push(E e) {
    if (n == a.length) resize(2 * a.length); // double size of array if necessary
    a[n++] = e;                                      // add e
  }

  // Removes and returns the item most recently added to this stack.
  // Throws java.util.EmptyStackException if this stack is empty.
  public E pop() {
    if (isEmpty()) throw new EmptyStackException();
    E e = a[n - 1];
    a[n - 1] = null;                              // to avoid loitering
    n--;
    // shrink size of array if necessary
    if (n > 0 && n == a.length / 4) resize(a.length / 2);
    return e;
  }


  // Returns (but does not remove) the item most recently added to this stack.
  public E peek() {
    if (isEmpty()) throw new EmptyStackException();
    return a[n - 1];
  }

  // Returns an iterator to this stack that iterates through the items in LIFO order.
  public @NotNull Iterator<E> iterator() {
    return new ReverseArrayIterator();
  }

  // an iterator, doesn't implement remove() since it's optional
  private class ReverseArrayIterator implements Iterator<E> {
    private int i;

    public ReverseArrayIterator() {
      i = n - 1;
    }

    public boolean hasNext() {
      return i >= 0;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public E next() {
      if (!hasNext()) throw new NoSuchElementException();
      return a[i--];
    }
  }

  /* returns a String representation of this stack */
  public String toString() {
    StringBuilder s = new StringBuilder();
    if (isEmpty()) return "This stack is empty ";
    for (E element : this) {
      s.append(element).append("  ");
    }
    return s.toString();
  }


  // Unit tests the Stack data type.
  public static void main(String[] args) {
    Stack<String> stack = new Stack<>();
    In in = new In("tobe.txt");
    while (!in.isEmpty()) {
      String item = in.readString();
      if (!item.equals("-")) stack.push(item);
      else if (!stack.isEmpty()) System.out.print(stack.pop() + " ");
    }
    System.out.println("(" + stack.size() + " left on stack)");
  }
}
