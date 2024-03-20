package homework.BST;

import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<Key extends Comparable<Key>> {

  private Node root;

  private class Node {
    Key key;
    Node left, right;

    public Node(Key key) {
      this(key, null, null);
    }

    public Node(Key key, Node left, Node right) {
      this.key = key;
      this.left = left;
      this.right = right;
    }
  }

  public BinarySearchTree() {
  }

  public void add(Key key) {
    this.root = add(root, key);
  }

  private Node add(Node node, Key key) {
    if (node == null) return new Node(key);
    int cmp = key.compareTo(node.key);
    if (cmp <= 0) node.left = this.add(node.left, key);
    else node.right = this.add(node.right, key);

    return node;
  }

  public void inorderTraversal() {
    this.inorderHelper(this.root);
  }

  private void inorderHelper(Node node) {
    if(node == null) return;
    this.inorderHelper(node.left);
    System.out.printf("%s ", node.key);
    this.inorderHelper(node.right);
  }

  public void preorderTraversal() {
    this.preorderTraversalHelper(this.root);
  }

  private void preorderTraversalHelper(Node node) {
    if(node == null) return;
    System.out.printf("%s ", node.key);
    this.preorderTraversalHelper(node.left);
    this.preorderTraversalHelper(node.right);
  }

  public void postorderTraversal() {
    this.postorderTraversalHelper(this.root);
  }

  private void postorderTraversalHelper(Node node) {
    if(node == null) return;
    this.postorderTraversalHelper(node.left);
    this.postorderTraversalHelper(node.right);
    System.out.printf("%s ", node.key);
  }

  public @Nullable Key contains(Key searchFor) {
    Node node = this.containsHelper(this.root, searchFor);
    if(node == null) return null;
    else return node.key;
  }

  private Node containsHelper(Node root, Key searchFor) {
    if(root == null) return null;

    int cmp = searchFor.compareTo(this.root.key);
    if(cmp <= 0) return this.containsHelper(root.left, searchFor);
    else return this.containsHelper(root.right, searchFor);
  }

  public void levelOrder() {
    Queue<Node> queue = new LinkedList<>();

    queue.add(this.root);
    while(!queue.isEmpty()) {
      Node nextNode = queue.remove();
      System.out.printf("%s ", nextNode.key);
      if(nextNode.left != null) {
        queue.add(nextNode.left);
      }
      if(nextNode.right != null) {
        queue.add(nextNode.right);
      }
    }
  }


}
