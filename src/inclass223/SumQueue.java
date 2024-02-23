package inclass223;

import java.util.LinkedList;
import java.util.Queue;

public class SumQueue {
   public static void main(String[] args) {
      Queue<Integer> queue = new LinkedList<>();  // Create a Queue here
      for (int i = 1; i <= 5 ; i++)
         queue.add(i);
      System.out.println(queue); // [1, 2, 3, 4, 5]
      System.out.println(sum(queue));
      System.out.println(queue);
   }

   /* returns the sum of the elements in Queue q */
   public static int sum(Queue<Integer> q) {
      int sum = 0;
      for(int i = 0; i < q.remove(); i++) {
        int n = q.remove();
        sum += n;
        q.add(n);
      }
      return sum;
   }
}
