package homework.BST;

public class TestBST {
  public static void main(String[] args) {
    BinarySearchTree<Integer> tree = new BinarySearchTree<>();

    Integer[] keys = {49, 28, 83, 18, 40, 71, 97, 11, 19, 32, 44, 69, 72, 92, 99};
    System.out.println("Inserting the following keys: ");
    for (Integer key : keys) {
      System.out.printf("%d ", key);
      tree.add(key);
    }
    System.out.println();

    System.out.println("Preorder traversal:");
    tree.preorderTraversal();
    System.out.println();

    System.out.println("Inorder traversal");
    tree.inorderTraversal();
    System.out.println();

    System.out.println("Postorder traversal");
    tree.postorderTraversal();
    System.out.println();

    System.out.println("Breadth-first traversal:");
    tree.levelOrder();
    System.out.println();

  }
}
