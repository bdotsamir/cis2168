package homework;

import java.util.ArrayList;

public class ZigZag<T> {

  /*
  [
  0  [0, 1, 2, 3],
  1  [4, 5, 6, 7],
  2  [8, 9, 10, 11],
  3  [12, 13, 14, 15]
  ]
   */

  private final T[][] elements;
  private final int xLength; // X length
  private final int yLength; // Y length

  public ZigZag(T[][] elements) {
    this.elements = elements;

    this.xLength = this.elements[0].length;
    this.yLength = this.elements.length;
  }

  private enum SIDE {
    X,
    Y
  }

  public ArrayList<CoordinatePair> calculateCoordinates() {
    ArrayList<CoordinatePair> coordinateList = new ArrayList<>();
    int x = 0;
    int y = 0;
    int upToX = 1;
    int upToY = 1;
    // Start with Y coordinate
    SIDE side = SIDE.Y;
//    SIDE prevSide = SIDE.X;

    coordinateList.add(new CoordinatePair(0, 0));

    for (int i = 0; i < xLength * yLength; i++) {

      if(side == SIDE.Y) {
        y++;
        if(x > 0) x--;

        if(y == upToY) {
          upToY++;
          side = SIDE.X;
          continue;
        }
      }

      if(side == SIDE.X) {
        x++;
        if(y > 0) y--;

        if(x == upToX) {
          upToX++;
          side = SIDE.Y;
          continue;
        }
      }

    }

    return coordinateList;
  }

  public record CoordinatePair(int x, int y) {

    public String toString() {
      return "(" + this.x + ", " + this.y + ")";
    }
  }

  public static void main(String[] args) {
    // grr can't use primitives. oh well
    Integer[][] elements = {
            {0, 1, 2, 3},
            {4, 5, 6, 7},
            {8, 9, 10, 11},
            {12, 13, 14, 15}
    };

    ZigZag<Integer> zigzag = new ZigZag<>(elements);
    ArrayList<CoordinatePair> coordinates = zigzag.calculateCoordinates();
    System.out.println(coordinates);
  }

}
