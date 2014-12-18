/*Copyright (c) 2014 C.Wahl

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package p;

import java.rmi.UnexpectedException;
import java.util.Scanner;
import java.util.function.BinaryOperator;

/**
 * @author C. Wahl <709679@gmail.com>
 */
public class Calculator {

  /**
   * @param line
   * @param operator
   * @param string
   * @return
   * @throws UnexpectedException
   */
  private static double evaluateLineOp(String line, String operatorString)
      throws UnexpectedException {
    final BinaryOperator<Double> operator;
    switch (operatorString) {
      case "-":
        operator = (x, y) -> x - y;
        break;
      case "+":
        operator = (x, y) -> x + y;
        break;
      case "/":
        operator = (x, y) -> x / y;
        break;
      case "*":
        operator = (x, y) -> x * y;
        break;
      default:
        operator = (x, y) -> 0.0;
        break;
    }

    final String[] syntaxElements = Calculator.splitAt(operatorString, line);
    Double currentValue = 0.0;
    boolean initDouble = false;
    for (final String syntaxElement : syntaxElements) {
      if (syntaxElement.matches(Calculator.isANumber)) {
        if ((currentValue == 0.0) && (initDouble == false)) {
          currentValue = Double.valueOf(syntaxElement);
          initDouble = true;
        } else {
          currentValue = operator.apply(currentValue,
              Double.valueOf(syntaxElement));
        }
      } else {
        final ReturnTwoValues<String, Integer> value = Calculator.indexFirstOf(
            syntaxElement, Calculator.OPERATORS);
        if (value.getSecondValue() > -1) {
          currentValue = operator.apply(currentValue,
              Calculator.evaluateLineOp(syntaxElement, value.getFirstValue()));
        } else {
          throw new UnexpectedException(syntaxElement + " is Not a number");
        }
      }

    }
    return currentValue;
  }

  public static ReturnTwoValues<String, Integer> indexFirstOf(String string,
      String[] elements) {
    final ReturnTwoValues<String, Integer> returnValue = new ReturnTwoValues<String, Integer>(
        "", -1);
    for (final String searchForElement : elements) {
      if (string.indexOf(searchForElement) > -1) {
        returnValue.setFirstValue(searchForElement);
        returnValue.setSecondValue(string.indexOf(searchForElement));
        return returnValue;
      }
    }
    return returnValue;
  }

  /**
   * @param line
   * @return
   */
  private static boolean isInputCorrect(String line) {
    return line.matches("[\\-\\+\\*\\/\\(\\)0-9]{3,}");
  }

  public static void main(String[] args) throws UnexpectedException {
    Calculator.scanner.useDelimiter("\n");
    System.out.print("Write your expression:\n > ");
    final String line = Calculator.scanner.nextLine();
    System.out.println("Result: " + Calculator.searchBrackets(line));
  }

  private static double searchBrackets(String line) throws UnexpectedException {
    if (Calculator.isInputCorrect(line)) {
      while (line.indexOf("(") > -1) {
        final int lastOpeningBracket = line.lastIndexOf("(");
        final int firstClosingBracketAfterLastOpeningBracket = line
            .lastIndexOf(")", line.length() - lastOpeningBracket);
        final String lineSubstring = line.substring(lastOpeningBracket + 1,
            firstClosingBracketAfterLastOpeningBracket);
        final double value = Calculator.evaluateLineOp(lineSubstring,
            Calculator.indexFirstOf(lineSubstring, Calculator.OPERATORS)
            .getFirstValue());
        line = line.replace("(" + lineSubstring + ")", Double.toString(value));
      }
      return Calculator.evaluateLineOp(line,
          Calculator.indexFirstOf(line, Calculator.OPERATORS).getFirstValue());
    } else {
      return 0;
    }
  }

  /**
   * @param string
   * @param line
   * @return
   */
  private static String[] splitAt(String splitAt, String line) {
    if (splitAt.trim().isEmpty()) {
      return new String[] { line };
    }
    return line.split("\\" + splitAt);
  }

  /**
   *
   */
  private static final String[] OPERATORS = new String[] { "*", "/", "+", "-" };

  private final static String   isANumber = "[\\-]?[\\d]*.[\\d]*";

  private final static Scanner  scanner   = new java.util.Scanner(System.in);
}
