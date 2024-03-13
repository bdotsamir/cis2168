package inclass228;

// BST client: outputs a sequence of integers in sorted order:
public class TestBST {
  public static void main(String[] args) {
    BST<Integer> bst = new BST<>();
    Integer[] keys = {21, 68, 11, 13, 17, 66, 99};
    System.out.println("Building a BST from the following keys:");
    for (Integer key : keys) {
      System.out.printf("%d ", key);
      bst.add(key);
    }

    System.out.printf("%n%nKeys in sorted order:%n");
    bst.inorderTraversal(); // prints 11 13 17 21 66 68 99
  }
}
