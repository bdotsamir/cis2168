package inclass207;

import org.jetbrains.annotations.Nullable;

// Generic Node data type
public class Node<E> {
   private E data;
   private @Nullable Node<E> next;

   // Would a constructor be useful in this small class?
   // (A constructor might initialize node data to the value e
   // and the next Node reference to null.)

  public Node(E data, @Nullable Node<E> nextNode) {
    this.data = data;
    this.next = nextNode;
  }

  public void setData(E data) {
    this.data = data;
  }

  public E getData() {
    return data;
  }

  public void setNext(@Nullable Node<E> newNextNode) {
    this.next = newNextNode;
  }

  public @Nullable Node<E> getNext() {
    return this.next;
  }

  public String toString() {
    String nextNodeData = "null";
    if(this.next != null) {
      nextNodeData = this.next.getData().toString();
    }

    return "Node data: \"" + this.data + "\". Next node's data: \"" + nextNodeData + "\"";
  }
}
