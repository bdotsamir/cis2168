package inclass214;

/* A minimalistic, bare-bones linked list. It is not generic and can
   only add elements to its front. It is helpful as a starter file
   for practice with writing new linked list methods.                */

public class LinkedListPractice {
   Node first;  // reference to the first node
   int size;    // the number of items on this list

   /* Class Node: stores data and a link to another Node */
   class Node {
      private Object data;
      private Node next;
   }

   /* adds an item to the front of the list */
   void add(Object item) {
      Node oldFirst = first;
      first = new Node();
      first.data = item;
      first.next = oldFirst;
      size++;
   }

   Node getMiddleNode() {
      Node slow = this.first;
      Node fast = this.first;

      while(fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
     }

      return slow;
   }

   public static void main(String[] args) {
      LinkedListPractice list = new LinkedListPractice();
      list.add(8); list.add(6); list.add(1); list.add(2);

      for (Node x = list.first; x != null; x = x.next)
         System.out.print(x.data + " -> ");
      System.out.println("null");

      Node mid = list.getMiddleNode();
      System.out.println("middle node: " + mid.data);
   }
}
