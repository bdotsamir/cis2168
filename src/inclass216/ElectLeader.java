package inclass216;

import inclass209.SLListSimple;

public class ElectLeader {

  private final SLListSimple<Integer> list;
  private final int m;

  public ElectLeader(SLListSimple<Integer> list, int m) {
    this.list = list;
    this.m = m;
  }

  public void elect() {
    while(!list.isEmpty()) {
      for(int i = 1; i <= m; i++) {
        var firstElement = list.removeFirst();
        if(i != m) list.addLast(firstElement);
      }
    }
  }

  public static void main(String[] args) {
    // Test ElectLeader

    SLListSimple<Integer> list = new SLListSimple<>();
    for(int i = 1; i < 9; i++) {
      list.addLast(i);
    }
    System.out.println(list);

    ElectLeader electLeader = new ElectLeader(list, 5);
    electLeader.elect();
    System.out.println(list);
  }

}
