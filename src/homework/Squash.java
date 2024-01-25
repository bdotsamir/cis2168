package homework;

import java.util.Arrays;

public class Squash {
  public static void main(String[] args) {
    int[] unsquashedArray = { 0, 0, 0, 0, 1, 1, 0, 0, 0, 7, 7, 7, 1, 1, 0 };

    int[] squashedArray = Squash.squash(unsquashedArray);
    System.out.println("Squashed array: " + Arrays.toString(squashedArray));
  }

  public static int[] squash(int[] arr) {
    int[] tempArray = new int[arr.length];

    // Initialize the proper variables
    int tempElement = arr[0];
    int n = 0;
    // (and make sure to set the first element of the tempArray
    tempArray[0] = tempElement;

    // We can start from index 1 because we already handled index 0.
    for(int i = 1; i < arr.length; i++) {
      // This element in arr is the same as the one before it
      if(arr[i] == tempElement) continue;

      // If the elements are not the same, we can push the new element.
      tempArray[++n] = arr[i];
      // And set the new temporary element
      tempElement = arr[i];
    }

    // For all the rest of the elements, set them to -1.
    for(int i = n + 1; i < tempArray.length; i++) {
      tempArray[i] = -1;
    }

    return tempArray;
  }
}
