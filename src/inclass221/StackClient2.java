package inclass221;

public class StackClient2 {
   public static void main(String[] args) {
      Stack<String> stack = new Stack<>();
      String[] input = {"to", "be", "or", "not", "to",
                         "-", "be", "-", "-", "that",
                         "-", "-", "-", "is"};
      for (String s : input) {
         if (!s.equals("-")) stack.push(s);
         else if (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
      }
      System.out.print("(" + stack.size() + " left on stack)");
   }
}
