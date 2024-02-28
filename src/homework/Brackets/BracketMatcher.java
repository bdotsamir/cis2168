package homework.Brackets;

import org.jetbrains.annotations.Nullable;
import util.In;

import java.util.Stack;

public class BracketMatcher {

  // this small nested class (lines 7 - 25) can help you solve the problem.
  // (if you feel it doesn't, feel free to delete it.)
  private static class Bracket {
    BracketType type;
    char character;
    int position;

    public Bracket(BracketType type, char character, int position) {
      this.type = type;
      this.character = character;
      this.position = position;
    }

    public enum BracketType {
      SQUARE,
      BRACE,
      ROUND
    }

    public static @Nullable BracketType matchesOpening(char c) {
      if (c == '[')
        return BracketType.SQUARE;
      if (c == '{')
        return BracketType.BRACE;
      if (c == '(')
        return BracketType.ROUND;
      return null;
    }

    public static @Nullable BracketType matchesClosing(char c) {
      if (c == ']')
        return BracketType.SQUARE;
      if (c == '}')
        return BracketType.BRACE;
      if (c == ')')
        return BracketType.ROUND;
      return null;
    }

    public static char findComplement(char c) {
      return switch (c) {
        case '[' -> ']';
        case '{' -> '}';
        case '(' -> ')';
        case ']' -> '[';
        case '}' -> '{';
        case ')' -> '(';
        default -> '_';
      };
    }
  }

  public static void main(String[] args) {
    In in = new In("test09.txt");
    String text = in.readLine();
//    System.out.println("processing: " + text); // comment out or delete this line before submitting

    Stack<Bracket> openingBrackets = new Stack<>();
    for (int position = 1; position <= text.length(); ++position) {
      char next = text.charAt(position - 1);
//      System.out.println(next);

      /* YOUR CODE HERE */
      Bracket.BracketType opening = Bracket.matchesOpening(next);
      Bracket.BracketType closing = Bracket.matchesClosing(next);

      // If the complement of this bracket is not equal to the one currently on the stack,
      // error out.

      if(opening != null) {
        openingBrackets.push(new Bracket(opening, next, position));
//        System.out.println("Pushing: " + next);
      }
      if(closing != null) {
        char complement = Bracket.findComplement(next);
        if(complement != openingBrackets.peek().character && complement != '_') {
          System.out.println("Unmatched bracket at location " + openingBrackets.peek().position);
          break;
        }
        openingBrackets.pop();
//        System.out.println("Popping: " + next);
      }

//      System.out.println(openingBrackets);
    }

    if(openingBrackets.isEmpty()){
      System.out.println("Success");
    } else {
      System.out.println("Unmatched bracket at location " + openingBrackets.peek().position);
    }

    // Print "Success" if brackets are balanced
    /* YOUR CODE HERE */

  }
}

