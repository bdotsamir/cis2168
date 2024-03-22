package inclass322;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner in = new Scanner(new File("mobydick.txt"));
    Set<String> words = new TreeSet<>();

    while(in.hasNext()) {
      String word = in.next();
      word = word.toLowerCase();

      words.add(word);
    }

    System.out.println(words);

  }
}
