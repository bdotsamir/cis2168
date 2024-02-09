package inclass207;

public class NodeTest {
  public static void main(String[] args) {
    Node<Integer> last = new Node<>(5);
    Node<Integer> middle = new Node<>(1, last);
    Node<Integer> first = new Node<>(2, middle);

    for(Node<Integer> x = first; x != null; x = x.getNext()) {
      System.out.print(x.getData() + " -> ");
    }
    System.out.println("null");
  }
}
