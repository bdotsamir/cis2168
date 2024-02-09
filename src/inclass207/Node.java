package inclass207;

import org.jetbrains.annotations.Nullable;

// Generic Node data type
public class Node<E> {
  private E data;
  private @Nullable Node<E> next;

  public Node(E data) {
    this(data, null);
  }

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
    if (this.next != null) {
      nextNodeData = this.next.getData().toString();
    }

    return "Node data: \"" + this.data + "\". Next node's data: \"" + nextNodeData + "\"";
  }
}
