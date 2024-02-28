package util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Pattern;

// In is a helper class written for CIS-2168 Labs. Its purpose is to help read
// a text file. The file can be remote (accessed through an URL) or local.
// Assignment instructions include an explanation of how to use this data type.
// The main() method shows an example of using the In class.
// You do not need to modify this file.
public final class In {
  private Scanner scanner;

  // Constructor. Initializes an input stream from a filename (local or URL).
  public In(String name) {
    if (name == null) throw new IllegalArgumentException("please tell me which file to open. exiting for now...");
    try {
      // first try to read file from local file system
      File file = new File(name);
      if (file.exists()) {
        FileInputStream fis = new FileInputStream(file);
        scanner = new Scanner(new BufferedInputStream(fis));
        return;
      }

      // resource on the web
      URL url = new URL(name);
      URLConnection site = url.openConnection();
      InputStream is = site.getInputStream();
      scanner = new Scanner(new BufferedInputStream(is));
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Could not open " + name, ioe);
    }
  }

  // main() tests the In data type
  public static void main(String[] args) {
    In in = new In("test09.txt");
//      while (!in.isEmpty())
//         System.out.print(in.readChar() + " ");
    String text = in.readLine();
    System.out.println(text);

  }

  // Reads all lines from this input stream and returns them as
  // an array of strings.
  public String[] readAllLines() {
    ArrayList<String> lines = new ArrayList<>();
    while (scanner.hasNextLine()) {
      lines.add(scanner.nextLine());
    }
    return lines.toArray(new String[lines.size()]);
  }

  // Reads the next token from this input stream, parses it as a int.
  public int readInt() {
    try {
      return scanner.nextInt();
    } catch (InputMismatchException e) {
      String token = scanner.next();
      throw new InputMismatchException("attempts to read an 'int' value from the input stream, "
              + "but the next token is \"" + token + "\"");
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("attempts to read an 'int' value from the input stream, "
              + "but no more tokens are available");
    }
  }

  // Reads all lines from this input stream, parses them as integers,
  // and returns them as an array of integers.
  public int[] readAllInts() {
    String[] fields = readAllLines();
    int[] vals = new int[fields.length];
    for (int i = 0; i < fields.length; i++)
      vals[i] = Integer.parseInt(fields[i].trim());
    return vals;
  }

  // Returns true if input stream is empty (except possibly whitespace).

  // Closes this input stream.
  public void close() {
    scanner.close();
  }

  // Returns true if input stream is empty
  public boolean isEmpty() {
    return !scanner.hasNext();
  }

  // Reads all remaining tokens from this input stream and returns them as
  public String[] readAllStrings() {
    // we could use readAll.trim().split(), but that's not consistent
    // since trim() uses characters 0x00..0x20 as whitespace
    String[] tokens = Pattern.compile("\\p{javaWhitespace}+").split(readAll());
    if (tokens.length == 0 || !tokens[0].isEmpty())
      return tokens;
    String[] decapitokens = new String[tokens.length - 1];
    for (int i = 0; i < tokens.length - 1; i++)
      decapitokens[i] = tokens[i + 1];
    return decapitokens;
  }

  // Reads and returns the remainder of this input stream as a string.
  public String readAll() {
    if (!scanner.hasNextLine())
      return "";

    return scanner.useDelimiter(Pattern.compile("\\A")).next();
  }

  // Reads the next token from this input stream and returns it as a String.
  public String readString() {
    try {
      return scanner.next();
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("attempts to read a 'String' value from the input stream, "
              + "but no more tokens are available");
    }
  }

  // Reads and returns the next line in this input stream
  public String readLine() {
    String line;
    try {
      line = scanner.nextLine();
    } catch (NoSuchElementException e) {
      line = null;
    }
    return line;
  }

  // Reads and returns the next character in this input stream
  public char readChar() {
    scanner.useDelimiter(Pattern.compile(""));
    try {
      String ch = scanner.next();
      assert ch.length() == 1 : "Internal (Std)In.readChar() error!"
              + " Please contact the authors.";
      scanner.useDelimiter(Pattern.compile("\\p{javaWhitespace}+"));
      return ch.charAt(0);
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("attempts to read a 'char' value from the input stream, "
              + "but no more tokens are available");
    }
  }
}
