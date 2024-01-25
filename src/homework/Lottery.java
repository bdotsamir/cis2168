package homework;

import java.util.*;

public class Lottery {
  public static void main(String[] args) {
    int n = 49; // 1 through n is the set of numbers to draw from
    int k = 6; // how many numbers will be drawn from this set
    int[] numbers = new int[n];
// fill the first array with numbers 1 2 3 . . . n
// YOUR CODE HERE
    for(int i = 0; i < numbers.length; i++) {
      numbers[i] = i + 1;
    }
    int[] result = new int[k];
// draw k numbers from the first array and put them into the second

    System.out.println("Draw the following integers " +
            "from the set 1 to " + (n + k) + ":");
    for(int i = 0; i < k; i++) {
      // Get a random index in n
      int randomN = (int) Math.floor(Math.random() * n);
      // Get the number at n
      int randomNumber = numbers[randomN];

      // Set the result at i to the random number
      result[i] = randomNumber;
      // Take the last number in n and place it at the index of randomNumber
      numbers[randomN] = n;
      // Decrement n to ensure we don't use that last number again.
      n--;

      System.out.print(randomNumber + " ");
    }

    // Technically I don't even need the result array here because I print
    // the number in the same array.
  }
}