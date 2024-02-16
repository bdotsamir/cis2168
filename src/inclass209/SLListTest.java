package inclass209;

public class SLListTest<E> {
   public static void main(String[] args) {
      SLListSimple<String> list;

      // TEST 1: testing addFirst() ... add your own tests ...
      list = new SLListSimple<>();
      System.out.println("\nCreated an empty list: [ " + list + "]" + "  // size = " + list.size());
      System.out.println("\tTesting addFirst: ");
      list.addFirst("hi");
      System.out.println("After adding an item at the front: [ " + list + "]" + "  // size = " + list.size());
      list.addFirst("there");
      System.out.println("After adding another item at the front: [ " + list + "]" + "  // size = " + list.size());

      // TEST 2: testing addLast() ... add your own tests ...
      list = new SLListSimple<>();
      System.out.println("\nCreated an empty list: [ " + list + "]" + "  // size = " + list.size());
      System.out.println("\tTesting addLast: ");
      list.addLast("to");
      System.out.println("After adding an item at the end: [ " + list + "]" + "  // size = " + list.size());
      list.addLast("be");
      System.out.println("After adding another item at the end: [ " + list + "]" + "  // size = " + list.size());
      list.addLast("or"); list.addLast("not"); list.addLast("to"); list.addLast("be");
      System.out.println("After adding 4 more items at the end: [ " + list + "]" + "  // size = " + list.size());

      // TEST 3: testing removeFirst() ... add your own tests ...
      list = new SLListSimple<>();
      list.addFirst("vibes"); list.addFirst("good"); list.addLast("only");
      System.out.println("\nThe new list is: [ " + list + "]" + "  // size = " + list.size());
      System.out.println("\tTesting removeFirst: ");
      list.removeFirst();
      System.out.println("After removing 1st: [ " + list + "]" + "  // size = " + list.size());
      list.removeFirst(); list.removeFirst();
      System.out.println("After removing two more: [ " + list + "]" + "  // size = " + list.size());
      //list.removeFirst(); // should throw a run-time exception
      //System.out.println("after removing from an empty list: [ " + list +"]" + "  // size = " + list.size());

      // TEST 4: testing removeLast() ... add your own tests ...
      list = new SLListSimple<>();
      list.addLast("vibes"); list.addFirst("good"); list.addLast("only");
      System.out.println("\nThe new list is: [ " + list + "]" + "  // size = " + list.size());
      System.out.println("\tTesting removeLast: ");
      String s = list.removeLast();
      System.out.println("removed value: " + s);
      System.out.println("After removing last: [ " + list + "]" + "  // size = " + list.size());
      list.removeLast(); list.removeFirst();
      System.out.println("After removing two more: [ " + list + "]" + "  // size = " + list.size());
   }
}
