package inclass403;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.Set;

public class ReduceTest {

  @Contract(pure = true)
  public HashMap<String, Integer> reduce(HashMap<String, Integer> map1,
                                         HashMap<String, Integer> map2) {

    HashMap<String, Integer> newMap = new HashMap<>(map1);

    Set<String> map1Keys = map1.keySet();
    for(String key : map1Keys) {
      if(map2.containsKey(key)) newMap.put(key, map1.get(key) + map2.get(key));
    }

    for(String key : map2.keySet()) {
      if(!newMap.containsKey(key)) newMap.put(key, map2.get(key));
    }

    return newMap;
  }

  public static void main(String[] args) {
    HashMap<String, Integer> map1 = new HashMap<>();
    map1.put("aiko", 2);
    map1.put("fluffy", 5);
    map1.put("zugzwang", 10);
    System.out.println("map1: " + map1);

    HashMap<String, Integer> map2 = new HashMap<>();
    map2.put("fluffy", 1);
    map2.put("aiko", 5);
    map2.put("panda", 2222);
    System.out.println("map2: " + map2);

    var result = new ReduceTest().reduce(map1, map2);
    // THE NEXT LINE PRODUCES THE CORRECT OUTPUT ONCE YOU IMPLEMENT the reduce() method
    System.out.println("result: " + result);


  }
}
