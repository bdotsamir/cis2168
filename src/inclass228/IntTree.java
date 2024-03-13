package inclass228;

public class IntTree {
  private Node root;

  // Inner class: implements a binary tree node
  private static class Node {
    int data;          // value stored at this node
    Node left, right;  // left and right subtrees of this node

    /* Constructor: creates a Node referring to two null subtrees */
    public Node(int data) {
      this(data, null, null);
    }

    /* Constructor: creates a Node referring to two subtrees */
    public Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }
  }

  public IntTree(int size) {
    root = buildTree(1, size);
  }

  // Returns a sequential tree with n as its root when n <= size;
  //  if n > size, returns an empty tree.
  private Node buildTree(int n, int size) {
    if (n > size) return null;
    else {
      Node left = buildTree(2 * n, size);
      Node right = buildTree(2 * n + 1, size);
      return new Node(n, left, right);
    }
  }

  // prints the contents of this tree in inorder
  public void inorderTraversal() {
    System.out.print("inorder: ");
    inorderHelper(root);
  }

  // prints the inorder traversal of a tree with a given root
  private void inorderHelper(Node node) {
    if (node != null) {
      inorderHelper(node.left);
      System.out.print(" " + node.data);
      inorderHelper(node.right);
    }
  }

  // prints this tree "sideways," one node value per line. The
  // values are indented according to their levels in the tree.
  public void printSideways() {
    printSideways(root, 0);
  }

  // prints the tree rooted at a given node and a given level;
  // trees are printed "sideways," each node indented by its level
  private void printSideways(Node root, int level) {
    if (root != null) {
      printSideways(root.right, level + 1);
      for (int i = 0; i < level; i++) {
        System.out.print("     ");
      }
      System.out.println(root.data);
      printSideways(root.left, level + 1);
    }
  }

  // IntTree Client
  public static void main(String[] args) {
    IntTree intTree = new IntTree(7); // builds a sequential tree of size 7
    intTree.printSideways();               // prints the tree sideways
    intTree.inorderTraversal();            // prints "inorder: 4 2 5 1 6 3 7"
  }
}
