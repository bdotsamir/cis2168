package inclass131;

public class TestResizingArray {
   public static void main(String[] args) {
      ResizingArray<Integer> list = new ResizingArray<>();
      System.out.println(list.size());  // 0
      System.out.println(list.isEmpty()); // true

      // UNCOMMENT THE FOLLOWING TESTS ONE-BY-ONE TO TEST YOUR METHODS
      /* TEST SET 1: testing add() */
      list.add(-12);  // [-12]
      for (int i = 1; i <= 8; i++)
         list.add(i);
      System.out.println(list);  // [ -12 1 2 3 4 5 6 7 8 ]


      /* TEST SET 2: testing remove() */
      list.remove(0);  // [ 1 2 3 4 5 6 7 8 ]
      list.remove(6);  // [ 1 2 3 4 5 6 8 ]
      System.out.println(list);

      /* TEST SET 3: testing add(index, element) */
      list.add(2, -1); // add -1 at position 2: [ 1 2 -1 3 4 5 6 8 ]
      list.add(0,-3); // add -1 at position 0: [ -3 1 2 -1 3 4 5 6 8 ]
      System.out.println(list);
      //list.add(22, 9);  // throws an exception
      //list.add(-22, 9); // throws an exception

      /* TEST SET 4: testing set() and get() */
      System.out.println(list.get(0)); // -3
      // System.out.println(list.get(-10)); // throws an exception
      // System.out.println(list.get(22)); // throws an exception
      System.out.println(list.get(7)); // 6

      list.set(0, 1000); // [ 1000 1 2 -1 3 4 5 6 8 ]
      list.set(3, 2000); // [ 1000 1 2 2000 3 4 5 6 8 ]
      System.out.println(list);
      //list.set(list.size() + 10000, 8); // throws an exception
      //list.set(-1, 8); // throws an exception
      list.set(list.size() - 1, 99);
      System.out.println(list); // [ 1000 1 2 2000 3 4 5 6 99 ]

      /* TEST SET 5: testing indexOf() */
      System.out.println(list.indexOf(99)); // 8
      list.set(0, 8); list.set(3, 8); // [ 8 1 2 8 3 4 5 6 99 ] - two values equal to 8
      System.out.println(list.indexOf(8)); // 0
      System.out.println(list.indexOf(5555)); // -1 (value not found)

      /* TEST SET 6: testing iterator() for-each must work */
      for (Integer k : list)
         System.out.print(k + " ");
      //should print 8 1 2 8 3 4 5 6 99
   }
}
