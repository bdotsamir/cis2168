package inclass221;

public class Calculator {
  public static void main(String[] args) {
    Stack<Character> operatorStack = new Stack<>();
    Stack<Integer> valueStack = new Stack<>();

    String expression = "(1+((2+3)*(4*5)))";

    for(int i = 0; i < expression.length(); i++) {
      char character = expression.charAt(i);

      switch(character) {
        case '(': continue;

        case '*':
        case '/':
        case '+':
        case '-': {
          operatorStack.push(character);
          break;
        }

        case '0': {
          valueStack.push(0);
          break;
        }
        case '1': {
          valueStack.push(1);
          break;
        }
        case '2': {
          valueStack.push(2);
          break;
        }
        case '3': {
          valueStack.push(3);
          break;
        }
        case '4': {
          valueStack.push(4);
          break;
        }
        case '5': {
          valueStack.push(5);
          break;
        }
        case '6': {
          valueStack.push(6);
          break;
        }
        case '7': {
          valueStack.push(7);
          break;
        }
        case '8': {
          valueStack.push(8);
          break;
        }
        case '9': {
          valueStack.push(9);
          break;
        }

        case ')': {
          var operator = operatorStack.pop();
          var ele1 = valueStack.pop();
          var ele2 = valueStack.pop();

          var result = Calculator.calculate(ele1, ele2, operator);
          valueStack.push(result);
          break;
        }
      }
    }

    System.out.println(valueStack.peek());
  }

  public static Integer calculate(Integer val1, Integer val2, char operator) {
    return switch (operator) {
      case '*' -> val1 * val2;
      case '/' -> val1 / val2;
      case '+' -> val1 + val2;
      case '-' -> val1 - val2;
      default -> throw new UnsupportedOperationException();
    };
  }
}
