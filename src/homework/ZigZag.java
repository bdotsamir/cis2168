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
  private final int width; // X length
  private final int height; // Y length

  public ZigZag(T[][] elements) {
    this.elements = elements;

    this.width = this.elements[0].length - 1;
    this.height = this.elements.length - 1;
  }

  public ArrayList<T> calculate() {
    ArrayList<T> result = new ArrayList<>();
    int row = 0;
    int col = 0;

    boolean goingDown = true;

    while(!ZigZag.outOfBounds(row, col, this.height, this.width)) {
      result.add(this.elements[row][col]);

      if(goingDown) {
        if(col == 0 || row == height) {
          goingDown = false;
          if(row == height) col++;
          else row++;
        } else {
          row++;
          col--;
        }
      } else {
        if(row == 0 || col == width) {
          goingDown = true;
          if(col == width) row++;
          else col++;
        } else {
          row--;
          col++;
        }
      }
    }

    return result;
  }

  private static boolean outOfBounds(int row, int col, int height, int width) {
    return row < 0 || row > height || col < 0 || col > width;
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
    var calculated = zigzag.calculate();
    System.out.println(calculated);
  }

}
