package homework;

/* Builds a toy sequential tree of integers and prints it sideways.
   Example: ToyIntTree(7) is this complete tree, printed sideways:
          7
     3
          6
1
          5
     2
          4
*/

public class ToyIntTree {
  private final Node root;

  private static class Node {
    int data;           // value stored at this node
    Node left, right;   // left and right subtrees of this node

    public Node(int data) {
      this(data, null, null);
    }

    public Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  /* Constructor: builds a sequential tree with given number of nodes */
  public ToyIntTree(int max) {
    this.root = buildTree(1, max);
  } // YOUR CODE HERE

  private Node buildTree(int n, int max) {
    if (n > max) return null;

    Node left = this.buildTree(2 * n, max);
    Node right = this.buildTree(2 * n + 1, max);
    return new Node(n, left, right);

  } // YOUR CODE HERE

  /* Other methods to implement, as explained in class */
  public void printSideways() {
    this.printSideways(this.root, 0);
  } // YOUR CODE HERE

  // in order traversal
  private void printSideways(Node root, int level) {
    if (root != null) {
      this.printSideways(root.right, level + 1);
      for (int i = 0; i < level; i++) {
        System.out.print(" ".repeat(4));
      }
      System.out.println(root.data + " ");
      this.printSideways(root.left, level + 1);
    }
  }

  public void preOrderTraversal() {
    this.preOrderTraversal(this.root, 0);
    System.out.println();
  }

  private void preOrderTraversal(Node root, int level) {
    if (root != null) {
      System.out.print(root.data + " ");
      this.preOrderTraversal(root.left, level + 1);
      this.preOrderTraversal(root.right, level + 1);
    }
  }

  public void postOrderTraversal() {
    this.postOrderTraversal(this.root, 0);
    System.out.println();
  }

  private void postOrderTraversal(Node root, int level) {
    if (root != null) {
      this.postOrderTraversal(root.left, level + 1);
      this.postOrderTraversal(root.right, level + 1);
//      for(int i = 0; i < level; i++) {
//        System.out.print(" ".repeat(4));
//      }
      System.out.print(root.data + " ");
    }
  }

  public int countLeaves() {
    return this.countLeavesHelper(this.root);
  }

  private int countLeavesHelper(Node node) {
    if (node == null) return 0;
    else if (node.left == null && node.right == null) {
      return 1;
    } else return this.countLeavesHelper(node.left) + this.countLeavesHelper(node.right);
  }

  public int sum() {
    return this.sumHelper(this.root);
  }

  private int sumHelper(Node root) {
    if (root == null)
      return 0;
    else if (root.left == null && root.right == null)
      return root.data;
    else
      return this.sumHelper(root.left) + this.sumHelper(root.right) + root.data;
  }

  public int countLevels() {
    return this.countLevelsHelper(this.root, 0);
  }

  private int countLevelsHelper(Node root, int level) {
    if(root.left != null) {
      return this.countLevelsHelper(root.left, level + 1);
    } else if (root.right != null) {
      return this.countLevelsHelper(root.right, level + 1);
    } else return level + 1;
  }

  public static void main(String[] args) {
    ToyIntTree bt = new ToyIntTree(7);
    bt.printSideways();

    bt.postOrderTraversal();
//    bt.preOrderTraversal();

    System.out.println(bt.countLeaves());
    System.out.println(bt.sum());
    System.out.println(bt.countLevels());
  }
}
