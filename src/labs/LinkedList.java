package labs;

import inclass214.LinkedListPractice;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LinkedList {

  int value;
  @Nullable LinkedList next;

  public LinkedList(int value) {
    this(value, null);
  }

  public LinkedList(int value, @Nullable LinkedList next) {
    this.value = value;
    this.next = next;
  }

  public void dedupe(LinkedList first) {
    LinkedList current = first;

    while (current != null) {
      LinkedList runner = current;
      while (runner.next != null) {
        if (runner.next.value == current.value) {
          runner.next = runner.next.next;
        } else {
          runner = runner.next;
        }
      }
      current = current.next;
    }
  }

  public String toString() {
    return String.valueOf(this.value);
  }

  public String printList() {
    StringBuilder ret = new StringBuilder();
    for (LinkedList x = this; x != null; x = x.next)
      ret.append(x.value).append(" -> ");
    ret.append("null");

    return ret.toString();
  }

  public static void main(String[] args) {
    LinkedList list = new LinkedList(1,
            new LinkedList(1,
                    new LinkedList(3,
                            new LinkedList(4,
                                    new LinkedList(4,
                                            new LinkedList(4,
                                                    new LinkedList(5,
                                                            new LinkedList(6,
                                                                    new LinkedList(6, null)
                                                            ))))))));

    list.dedupe(list);

    System.out.println(list.printList());
  }

}
