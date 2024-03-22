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

    // this was GOING to strip all symbols, but i'm not quite there yet.
//    Pattern letterPattern = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);

    while (in.hasNext()) {
      String word = in.next();
      word = word.toLowerCase();

//      Matcher matcher = letterPattern.matcher(word);
//      matcher.

      if (word.startsWith("a") && word.length() == 3) {
        words.add(word);
      }

    }

    System.out.println(words);
    System.out.println(words.size());

  }
}
