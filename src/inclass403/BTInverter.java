package inclass403;

import java.util.LinkedList;
import java.util.Queue;

public class BTInverter {
  // O(n) time, O(n) space
  public static void invert(BinaryTree tree) {
    // YOUR CODE HERE
    if(tree == null) return;

    exchangeLeftAndRight(tree);
    invert(tree.left);
    invert(tree.right);
  }

  private static void exchangeLeftAndRight(BinaryTree tree) {
    BinaryTree temp = tree.left;
    tree.left = tree.right;
    tree.right = temp;
  }


  // inner class, represents a binary tree
  public static class BinaryTree {
    int key;            // node data
    BinaryTree left;    // this node's left subtree
    BinaryTree right;   // this node's right subtree

    // constructor: initializes data, sets subtrees to null
    public BinaryTree(int key) {
      this.key = key;
    }
  }

  public static void main(String[] args) {
    /* TEST 1 */
    BinaryTree rt = new BinaryTree(2);
    // left subtree of rt
    rt.left = new BinaryTree(1);
    rt.left.left = new BinaryTree(8);
    rt.left.right = new BinaryTree(4);
    rt.left.right.left = new BinaryTree(7);
    rt.left.right.right = new BinaryTree(9);
    // right subtree of rt
    rt.right = new BinaryTree(6);
    rt.right.left = new BinaryTree(5);
    show(rt);
    System.out.println();

    // WHEN DONE, THE FOLLOWING TWO LINES WILL OUTPUT THE INVERTED TREE
    BTInverter.invert(rt);
    show(rt);
  }

  public static void show(BinaryTree bt) {
    Queue<BinaryTree> q = new LinkedList<>();
    q.add(bt);
    while (!q.isEmpty()) {
      BinaryTree t = q.remove();
      if (t == null) continue;
      System.out.printf("%s ", t.key); // output node data
      q.add(t.left);
      q.add(t.right);
    }
  }
}

