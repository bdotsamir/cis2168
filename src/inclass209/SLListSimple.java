package inclass209;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import util.In;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLListSimple<E> implements Iterable<E> {
  private int n;        // size of the list
  private Node first;   // reference to the first node of the list
  private Node last;

  // Helper Node data type
  public class Node {
    protected E data;
    protected Node next;

    // constructor: initializes data to the value e and
    // initializes the next Node reference to null
    Node(E e) {
      this(e, null);
    }

    // constructor: initializes data to the value e and
    // initializes the next Node reference to node
    Node(E e, @Nullable Node node) {
      this.data = e;
      this.next = node;
    }
  }

  // constructor: initializes an empty list
  public SLListSimple() {
    this.n = 0;
  } // fist = last = null by default

  public Node getMiddleNode() {
    Node slow = this.first;
    Node fast = this.first;

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  public Node reverse() {
    Node prev = null;
    Node current = this.first;

    // Loop through the entire list
    while(current != null) {
      // Grab the next node
      Node next = current.next;
      // The next node should now point to the node behind it
      current.next = prev;
      // The node behind it should now point to the current node
      prev = current;
      // and now the current node (prev) should point to the next node in the chain.
      current = next;
      // repeat.
    }
    // At this point, prev is the first node.
    return prev;
  }

  // adds an element to the front of the list
  public void addFirst(E value) {
    Node oldFirst = this.first;
    this.first = new Node(value);
    if (this.isEmpty()) this.last = this.first;
    else this.first.next = oldFirst;
    this.n++;
  }

  // adds element to the end of the list
  public void addLast(E value) {
    if (this.isEmpty()) this.addFirst(value);
    else {
      Node oldLast = this.last;
      this.last = new Node(value);
      oldLast.next = last;
      this.n++;
    }
  }

  // removes and returns the first element from the list
  public E removeFirst() {
    if (this.isEmpty()) throw new NoSuchElementException("The list is empty");

    E removedValue = this.first.data;
    if (this.first == this.last)
      this.first = this.last = null;
    else this.first = this.first.next;
    this.n--;
    return removedValue;
  }

  // removes and returns the last element from the list
  public E removeLast() {
    if (this.isEmpty()) throw new NoSuchElementException("The list is empty");

    E removed = this.last.data;

    if (this.first == this.last) this.first = this.last = null;
    else {
      Node current = this.first;
      while (current.next != this.last)
        current = current.next;
      this.last = current;
      current.next = null;
    }
    this.n--;
    return removed;
  }

  private void addAfter(Node node, E value) {
    Node newNode = new Node(value);
    newNode.next = node.next;
    node.next = newNode;
    this.n++;
  }

  private E removeAfter(Node node) {
    Node temp = node.next;
    if (temp != null) {
      node.next = temp.next;
      n--;
      return temp.data;
    } else return null;
  }

  private Node getNode(int index) {
    Node current = this.first;
    for (int i = 0; i < index && current != null; i++) {
      current = current.next;
    }
    return current;
  }

  public boolean add(int index, E value) {
    if (index < 0 || index > this.n) {
      System.err.println("Index out of bounds.");
      return false;
    }
    if (index == 0) {
      this.addFirst(value);
      return true;
    }
    if (index == n) {
      this.addLast(value);
    } else {
      Node node = this.getNode(index - 1);
      this.addAfter(node, value);
    }
    return true;
  }

  public boolean remove(int index) {
    if (index < 0 || index >= n) {
      System.err.println("Index out of bounds");
      return false;
    }
    if (index == 0) {
      this.removeFirst();
      return true;
    }
    if (index == n - 1) {
      this.removeLast();
    } else {
      Node node = this.getNode(index - 1);
      this.removeAfter(node);
    }
    return true;
  }

  public E get(int index) {
    if (index < 0 || index >= n) {
      System.err.println("Index out of bounds");
      return null;
    }
    Node node = this.getNode(index);
    return node.data;
  }

  public E set(int index, E newValue) {
    if (index < 0 || index >= n) {
      System.err.println("Index ouf of bounds");
      return null;
    }
    Node node = this.getNode(index);
    E oldValue = node.data;
    node.data = newValue;
    return oldValue;
  }

  public int indexOf(E e) {
    if (this.isEmpty()) {
      return -1;
    }
    Node current = this.first;
    for (int i = 0; current != null; i++) {
      if (e.equals(current.data)) return i;
      current = current.next;
    }
    return -1;
  }

  public boolean contains(E e) {
    if (this.isEmpty()) return false;
    Node current = this.first;
    while (current != null) {
      if (e.equals(current.data)) return true;
      current = current.next;
    }
    return false;
  }

  // is this list empty?
  public boolean isEmpty() {
    return this.n == 0;
  }

  // the size of this list (the number of nodes in it)
  public int size() {
    return this.n;
  }

  // returns a string representation of this list
  public String toString() {
    StringBuilder s = new StringBuilder();
    if (this.isEmpty()) return " ";
    Node current = this.first;
    while (current != null) {
      s.append(current.data).append(" ");
      current = current.next;
    }
    return s.toString();
  }

  public @NotNull Iterator<E> iterator() {
    return new LinkedIterator();
  }

  private class LinkedIterator implements Iterator<E> {
    private Node current = first;

    public boolean hasNext() {
      return current != null;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public E next() {
      if (!hasNext()) throw new NoSuchElementException();
      E value = current.data;
      current = current.next;
      return value;
    }
  }

  public static void main(String[] args) {

    SLListSimple<String> list = new SLListSimple<>();
    In in = new In("franklin1.txt");
    while (!in.isEmpty()) {
      list.addLast(in.readString());
    }

    System.out.println(list);
    System.out.println("   removing the last item, \"" + list.removeLast() + "\":");
    System.out.println(list);

    System.out.println();
    System.out.println("   removing the first item, \"" + list.removeFirst() + "\":");
    System.out.println(list);

    System.out.println("--------------");
    System.out.println(list.indexOf("sep"));

    // Testing iterator
    for (String element : list) {
      System.out.println(element + " ");
    }
  }

}
