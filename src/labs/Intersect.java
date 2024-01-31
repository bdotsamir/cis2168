package labs;

import java.util.ArrayList;
import java.util.List;

// References:
// https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
// https://stackoverflow.com/a/29143977/8916706

public class Intersect {
  public static void main(String[] args) {
    ArrayList<Integer> list1 = new ArrayList<>(
            List.of(1, 4, 8, 9, 11, 15, 17, 28, 41, 59)
    );
    ArrayList<Integer> list2 = new ArrayList<>(
            List.of(4, 7, 11, 17, 19, 20, 23, 28, 37, 59, 81)
    );

    // SORTS IN PLACE
    list1.sort(Comparable::compareTo);
    list2.sort(Comparable::compareTo);

    ArrayList<Integer> intersected = Intersect.intersection(list1, list2);
    System.out.println("intersection of two lists: " + intersected);
  }

  /**
   * Find the intersection of two ArrayLists.
   * @param list1 SORTED first list
   * @param list2 SORTED second list
   * @return Intersection of the two lists
   */
  public static <T> ArrayList<T> intersection(ArrayList<T> list1, ArrayList<T> list2) {
    ArrayList<T> tempList = new ArrayList<>();

    for(T item : list1) {
      if(list2.contains(item)) tempList.add(item);
    }

    return tempList;
  }
  // java generics are WEIRD
}
