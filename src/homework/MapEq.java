package homework;

import java.util.*;

public class MapEq {
  public static void main(String[] args) {
    Map<ToDos, String> m = new HashMap<>();
    ToDos t1 = new ToDos("Monday");
    ToDos t2 = new ToDos("Monday");
    ToDos t3 = new ToDos("Tuesday");

    System.out.println(t1);
    System.out.println(t2);
    System.out.println(t3);

    m.put(t1, "doLaundry");
    m.put(t2, "cleanAttic");
    m.put(t3, "goToClassAgain");
    System.out.println(m.size());
  }
}

class ToDos {
  String day;

  ToDos(String d) {
    day = d;
  }

  public boolean equals(Object o) {
    return ((ToDos) o).day == this.day;
  }

  public int hashCode() {
    return 9;
  }
}