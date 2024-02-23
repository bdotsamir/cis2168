package inclass221;

public class StackClient {
   public static void main(String[] args) {
      Stack<Integer> stack = new Stack<>();

      // Add a few items to the stack
      stack.push(8);
      stack.push(6);
      stack.push(1);
      stack.push(2);
      System.out.println(stack); // prints 2 1 6 8

      // Remove items from stack one at a time (line 16).
      // Each time an item is removed, print it (line 17),
      // then print items remaining on the stack (line 18).
      while (!stack.isEmpty()) {
         System.out.print("popped item: " + stack.pop());
         System.out.println(" stack contents: " + stack.toString());
      }  // Note that the items are popped in the order reverse to
   }     // the one they were added to the stack:
}        // first 2 is popped, then  1, then 6, then 8
