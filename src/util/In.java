package util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Class In. This class provides methods for reading strings, characters,
 * and numbers from the standard input and file inputs.
 * <p>
 * The Locale used is: language = English, country = US.
 * <p>
 * Like Scanner, reading a token consumes preceding Java whitespace, reading
 * a full line consumes the following end-of-line delimiter, while reading a
 * character consumes nothing extra.
 *
 * @author david
 * Useful as a helper class for CIS-2168 demos and assignments.
 */
public final class In {

  // assume Unicode UTF-8 encoding
  private static final String CHARSET_NAME = "UTF-8";

  // we use: language = English, country = US for consistency with System.out.
  private static final Locale LOCALE = Locale.US;

  // the default token separator; invariant: this value is kept
  // by the scanner's delimiter between calls
  private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

  // makes whitespace characters significant
  private static final Pattern EMPTY_PATTERN = Pattern.compile("");

  // used to read the entire input source:
  private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");


  private Scanner scanner;

  // Initializes an input stream from the standard input.
  public In() {
    scanner = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
    scanner.useLocale(LOCALE);
  }

  // Initializes an input stream from a filename or web URL.
  public In(String name) {
    if (name == null) throw new IllegalArgumentException("argument is null");
    try {
      // first try to read file from local file system
      File file = new File(name);
      if (file.exists()) {
        // Wrap with BufferedInputStream instead of using file as argument to Scanner
        FileInputStream fis = new FileInputStream(file);
        scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
        scanner.useLocale(LOCALE);
        return;
      }
      // resource relative to .class file
      URL url = getClass().getResource(name);

      // resource relative to classloader root
      if (url == null) url = getClass().getClassLoader().getResource(name);

      // or URL from web
      if (url == null) url = new URL(name);

      URLConnection site = url.openConnection();

      InputStream is = site.getInputStream();
      scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
      scanner.useLocale(LOCALE);
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Could not open " + name, ioe);
    }
  }

  // Initializes an input stream from a given Scanner source.
  public In(Scanner scanner) {
    if (scanner == null) throw new IllegalArgumentException("scanner argument is null");
    this.scanner = scanner;
  }

  // Returns true if this input stream exists.
  public boolean exists() {
    return scanner != null;
  }

  // Returns true if input stream is empty (except possibly whitespace).
  public boolean isEmpty() {
    return !scanner.hasNext();
  }

  // Returns true if this input stream has a next line.
  public boolean hasNextLine() {
    return scanner.hasNextLine();
  }

  // Returns true if this input stream has more input (including whitespace).
  public boolean hasNextChar() {
    scanner.useDelimiter(EMPTY_PATTERN);
    boolean result = scanner.hasNext();
    scanner.useDelimiter(WHITESPACE_PATTERN);
    return result;
  }


  // Reads and returns the next line in this input stream.
  public String readLine() {
    String line;
    try {
      line = scanner.nextLine();
    } catch (NoSuchElementException e) {
      line = null;
    }
    return line;
  }

  // Reads and returns the next character in this input stream.
  public char readChar() {
    scanner.useDelimiter(EMPTY_PATTERN);
    try {
      String ch = scanner.next();
      assert ch.length() == 1 : "Internal error!"
              + " Please contact the author.";
      scanner.useDelimiter(WHITESPACE_PATTERN);
      return ch.charAt(0);
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("attempts to read a 'char' from the input stream, "
              + "but no more tokens are available");
    }
  }


  // Reads and returns the remainder of this input stream, as a string.
  public String readAll() {
    if (!scanner.hasNextLine())
      return "";

    String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
    // not that important to reset delimiter, since now scanner is empty
    scanner.useDelimiter(WHITESPACE_PATTERN); // but let's do it anyway
    return result;
  }


  // Reads the next token from this input stream and returns it as a {@code String}.
  public String readString() {
    try {
      return scanner.next();
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("attempts to read a 'String' value from the input stream, "
              + "but no more tokens are available");
    }
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

  // Reads the next token from this input stream, parses it as a double.
  public double readDouble() {
    try {
      return scanner.nextDouble();
    } catch (InputMismatchException e) {
      String token = scanner.next();
      throw new InputMismatchException("attempts to read a 'double' value from the input stream, "
              + "but the next token is \"" + token + "\"");
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("attemps to read a 'double' value from the input stream, "
              + "but no more tokens are available");
    }
  }


  // Reads the next token from this input stream, parses it as a long.
  public long readLong() {
    try {
      return scanner.nextLong();
    } catch (InputMismatchException e) {
      String token = scanner.next();
      throw new InputMismatchException("attempts to read a 'long' value from the input stream, "
              + "but the next token is \"" + token + "\"");
    } catch (NoSuchElementException e) {
      throw new NoSuchElementException("attempts to read a 'long' value from the input stream, "
              + "but no more tokens are available");
    }
  }


  // Reads all remaining tokens from this input stream and returns them as
  // an array of strings
  public String[] readAllStrings() {
    // we could use readAll.trim().split(), but that's not consistent
    // since trim() uses characters 0x00..0x20 as whitespace
    String[] tokens = WHITESPACE_PATTERN.split(readAll());
    if (tokens.length == 0 || tokens[0].length() > 0)
      return tokens;
    String[] decapitokens = new String[tokens.length - 1];
    for (int i = 0; i < tokens.length - 1; i++)
      decapitokens[i] = tokens[i + 1];
    return decapitokens;
  }

  // Reads all remaining lines from this input stream and returns them as
  // an array of strings.
  public String[] readAllLines() {
    ArrayList<String> lines = new ArrayList<String>();
    while (hasNextLine()) {
      lines.add(readLine());
    }
    return lines.toArray(new String[lines.size()]);
  }


  // Reads all remaining tokens from this input stream, parses them as integers,
  // and returns them as an array of integers.
  public int[] readAllInts() {
    String[] fields = readAllStrings();
    int[] vals = new int[fields.length];
    for (int i = 0; i < fields.length; i++)
      vals[i] = Integer.parseInt(fields[i]);
    return vals;
  }


  // Reads all remaining tokens from this input stream, parses them as doubles,
  // and returns them as an array of doubles.
  public double[] readAllDoubles() {
    String[] fields = readAllStrings();
    double[] vals = new double[fields.length];
    for (int i = 0; i < fields.length; i++)
      vals[i] = Double.parseDouble(fields[i]);
    return vals;
  }

  // Closes this input stream.
  public void close() {
    scanner.close();
  }


  // Unit tests the In data type.
  public static void main(String[] args) {
    In in;
    /* Test 1: reading ints from a web resource. Initialize In with tinyT.txt data file (stored on github)*/
    in = new In("https://gist.githubusercontent.com/david-dobor/b4817f435bb4cc58adefc8f101f44efa/raw/d43fee7207dbac6fd70a1a111dd4992ffbd318ec/tinyT.txt");
    int[] a = in.readAllInts();
    System.out.println(Arrays.toString(a));

      /* The following tests rely on an input file called InTest.txt. It's contents are:

                 This is a test file.
                 Here    is line 2.

          To run the following tests, create InTest.txt and store it locally where In.java
          can find it. Then comment out the following lines and rerun In.java.
       */


    /* Test 2: read one line at a time from file in current directory (InTest.txt is stored locally)*/
//      System.out.println("readLine() from current directory");
//      System.out.println("---------------------------------------------------------------------------");
//      try {
//         in = new In("InTest.txt");
//         while (!in.isEmpty()) {
//            String s = in.readLine();
//            System.out.println(s);
//         }
//      }
//      catch (IllegalArgumentException e) {
//         System.out.println(e);
//      }
//      System.out.println();
//
//      /* Test 3: read one line at a time from file in current directory
//         using relative path (InTest.txt is srored locally)*/
//      System.out.println("readLine() from relative path");
//      System.out.println("---------------------------------------------------------------------------");
//      try {
//         in = new In("./InTest.txt");
//         while (!in.isEmpty()) {
//            String s = in.readLine();
//            System.out.println(s);
//         }
//      }
//      catch (IllegalArgumentException e) {
//         System.out.println(e);
//      }
//      System.out.println();
//
//      /* Test 4: read one character at a time from file in current directory (InTest.txt is srored locally)*/
//      System.out.println("readChar() from file");
//      System.out.println("---------------------------------------------------------------------------");
//      try {
//         in = new In("InTest.txt");
//         while (!in.isEmpty()) {
//            char c = in.readChar();
//            System.out.print(c);
//         }
//      }
//      catch (IllegalArgumentException e) {
//         System.out.println(e);
//      }
//      System.out.println();
//      System.out.println();
//
//      /* Test 4: read one line at a time from absolute OS X / Linux path (InTest.txt is srored locally)*/
//        System.out.println("readLine() from absolute OS X / Linux path");
//        System.out.println("---------------------------------------------------------------------------");
//        try {
//            in = new In("/Users/david/InTest.txt");
//            while (!in.isEmpty()) {
//                String s = in.readLine();
//                System.out.println(s);
//            }
//        }
//        catch (IllegalArgumentException e) {
//            System.out.println(e);
//        }
//        System.out.println();
  }
}

