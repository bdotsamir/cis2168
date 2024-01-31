package inclass126;

public class ListFilter {
   public static void main(String[] args) {
      // read the valid list into an int array (the next line reads valid1M.txt containing 1 million entries)
       In in = new In("https://gist.githubusercontent.com/david-dobor/8e72f21c733b677af60f35086e65924f/raw/2e6e4daa0b019b22de38b9e6572af29cf19b6139/valid1M.txt");

      // alternatively, read in a small file called valid.txt
//      In in = new In("https://gist.githubusercontent.com/david-dobor/1dc31f47f3c918c2285aa130e3a355d5/raw/b77c3953206c34feffd2c3a65361b0d5e029e17b/valid.txt");
      int[] a = in.readAllInts();

      // create a CollectionOfInts object that can process the input
      // IntCollection collection = new IntCollection(a); // uses binary-search under the hood (FAST)
      IntCollection collection = new IntCollection(a); // uses linear-search under the hood (SLOW)

      // read the data that needs to be filtered (the next line reads contenders1M.txt containing 1.5 million entries)
      in = new In("https://gist.githubusercontent.com/david-dobor/ac1a8528a0b8a5a88e41777c56e51bc4/raw/f7a65a9682f186ce25d2e9e2f6aa95b664c90c72/contenders1M.txt");

      // alternatively, read in a small file called contenders.txt
//      in = new In("https://gist.githubusercontent.com/david-dobor/58a0611eada862ee034f564268a7ce1e/raw/81824863976b58d8f4aa05e316cbc06207881cb3/contenders.txt");
      int[] keys = in.readAllInts();

      // Now filter the list by relying on the CollectionOfInts API
      final long startTime = System.currentTimeMillis();  // start timer
      for (int key : keys)
         if (!collection.contains(key))
            System.out.println(key);
      final long endTime = System.currentTimeMillis();  // end timer
      System.out.println("Total execution time: " + (endTime - startTime));
   }
}
