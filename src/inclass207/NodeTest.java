package inclass207;

public class NodeTest {
   public static void main(String[] args) {
      // TODO: 1) create and initialize nodes with values 5, 1, and 2.
      // TODO: 2) link the nodes to create the list [2, 1, 5]
      // TODO: 3) Iterate through the list and print 2 -> 1 -> 5 -> null

     Node<Integer> nodeVal5 = new Node<>(5, null);
     Node<Integer> nodeVal1 = new Node<>(1, null);
     Node<Integer> nodeVal2 = new Node<>(2, null);

     nodeVal2.setNext(nodeVal1);
     nodeVal1.setNext(nodeVal5);

     for(Node<Integer> i = nodeVal2; i != null; i = i.getNext()) {
       System.out.print(i.getData() + " -> ");
     }
   }
}
