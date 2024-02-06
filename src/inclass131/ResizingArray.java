package inclass131;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ResizingArray<E> implements Iterable<E> {
  // the initial capacity of the underlying resizing array
  private static final int INIT_CAPACITY = 10;

  private E[] elements;   // the underlying array of generic elements
  private int n;          // the number of elements in the list

  /* Constructor: Initializes an empty list. */
  @SuppressWarnings({"unchecked"})
  public ResizingArray() {
    // Java does not support creating an array of type generic E.
    // It *must* be an Object, then cast to E.
    // This is not an issue, as any class we throw into this thing will intrinsically extend Object.
    elements = (E[]) new Object[INIT_CAPACITY];
    n = 0;
  }

  // Returns the number of elements in the list.
  public int size() {
    return n;
  }

  // Is this list empty?
  public boolean isEmpty() {
    return n == 0;
  }

  // Resizes the underlying array holding the elements
  private void resize(int capacity) {
    assert capacity >= n;
    elements = Arrays.copyOf(elements, capacity);
  }

  // Adds the item to the end of this list.
  public void add(E element) {
    if (this.n == this.elements.length) {
      this.resize(this.elements.length * 2);
    }

    this.elements[n++] = element;
  }

  // Adds the element at index.
//  public void add(int index, E element) {
//    if (index < 0 || index >= n) {
//      throw new ArrayIndexOutOfBoundsException();
//    }
//
//    if(n == this.elements.length) {
//      this.resize(2 * this.elements.length);
//    }
//
//    for(int i = n; i > index; i--) {
//      this.elements[i] = this.elements[i - 1];
//    }
//    this.elements[index] = element;
//    n++;
//  }

  public void add(int index, E element) {
    if(index < 0 || index >= n) {
      throw new ArrayIndexOutOfBoundsException();
    }

    if (n == this.elements.length) {
      this.resize(2 * this.elements.length);
    }

    System.arraycopy(
            this.elements, // This is the array we're copying
            index, // Extract a slice of the array at this index
            this.elements, // The array we're copying the slice into
            index + 1, // Copy the slice starting at this index
            ++n - index // And only copy this many elements (length - index)
    );

    this.elements[index] = element;
  }
  // Inspired by java's native ArrayList#add(int index, E element) method <3
  // We're using System.arraycopy over Arrays.copyOf because, internally,
  // System#arraycopy calls C/C++ `memmove`, which will *literally* shift
  // the array *in memory*. Arrays#copyOf will create an entirely new array
  // and copy the elements in.
  // And, as it turns out, Arrays#copyOf calls System#arraycopy in its
  // implementation anyway, so I'm just cutting out the middleman
  // See other: https://stackoverflow.com/a/2589797/8916706

  // Replaces the element at index with the given element
  public void set(int index, E element) {
    if (index < 0 || index >= n) {
      throw new ArrayIndexOutOfBoundsException();
    }

    this.elements[index] = element;
  }

  // Returns the item at index without removing it
  public E get(int index) {
    return elements[index];
  }

//  // Removes the element at position index
//  public void remove(int index) {
//    if (index < 0 || index >= n) {
//      throw new ArrayIndexOutOfBoundsException();
//    }
//
//    for (int i = index + 1; i < n; i++) {
//      this.elements[i - 1] = this.elements[i];
//    }
//    n--;
//  }

  public void remove(int index) {
    if(index < 0 || index >= n) {
      throw new ArrayIndexOutOfBoundsException();
    }

    System.arraycopy(
            this.elements, // This is the array we're copying
            index + 1, // Extract a slice of the array at this index
            this.elements, // The array we're copying the slice into
            index, // Copy the slice starting at this index
            --n - index // And only copy this many elements (length - index)
    );
//    n--;
  }


  // Returns the index of the first occurrence of element or -1 if not found
  public int indexOf(E element) {
    for (int i = 0; i < this.elements.length; i++) {
      if(this.elements[i] == element) return i;
    }

    return -1;
  }

  @Override
  // Returns an iterator that iterates over the items in this list
  public @NotNull Iterator<E> iterator() {
    return new ResizingArrayIterator();
  }

  // an iterator, doesn't implement remove() since it's optional
  private class ResizingArrayIterator implements Iterator<E> {
    private int i = 0;

    public boolean hasNext() {
      return i < n;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }

    public E next() {
      if (!hasNext()) throw new NoSuchElementException();
      return elements[i++];
    }
  }

  @Override
  // returns a string representation of this list
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    if (n == 0) return "This list is empty";
    for (E value : this)
      s.append(value).append(" ");
    s.append("]");
    return s.toString();
  }

  public static void main(String[] args) {
    ResizingArray<String> list = new ResizingArray<>();
    System.out.println(list.isEmpty());
    list.add("to");
    list.add("be");
    list.add("or");
    list.add("not");
    list.add("to");
    list.add("be");
    for (String s : list)
      System.out.print(s + " ");
    System.out.println();   // to be or not to be
    System.out.println(list);
    list.remove(3); // remove the element at index 3
    System.out.println(list);
    list.add(2, "awesome"); // add an element at index 2
    System.out.println(list);
    list.add(0, "Or");    // add an element at the beginning
    list.add(3, "really"); // add an element at index 2
    list.add("great"); // and an element at the end
    System.out.println(list);
    list.set(list.size() - 1, "good");  // set the last element
    System.out.println(list);
    list.set(7, "do");
    System.out.println(list);
  }
}
