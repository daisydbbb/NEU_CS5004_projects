package polynomial;

import java.util.Scanner;

/**
 * This is the PolynomialImpl class that implements Polynomial Interface
 * Name: XIN DING
 * Date: 3/26/2024
 */
public class PolynomialImpl implements Polynomial{
  private ListNode head;

  /**
   * constructor of PolynomialImpl class with no parameters
   */
  public PolynomialImpl() {
    head = null;
  }

  /**
   * constructor of PolynomialImpl class that takes a polynomial as a string
   * @param polynomial, input string that will be parsed and used for creating polynomial accordingly
   * The polynomial string must have each term separated by a space. e.g:"-3x^4 -2x^5 -5 +11x^1"
   * Throws IllegalArgumentException if the input String format is invalid
   * e.g.: "-3x^4-2x^5 -5 +11x^1", "-3x^4 +-2x^5 -5 +11x^1" are invalid strings
   */
  public PolynomialImpl(String polynomial) throws IllegalArgumentException {
      // Validate the polynomial string format
      if (!polynomial.matches("\\s*[+-]?\\s*(\\d*x\\^\\d+|\\d+)(\\s*[+-]\\s*(\\d*x\\^\\d+|\\d+))*\\s*")) {
        throw new IllegalArgumentException("The polynomial format is invalid");
      }

      Scanner scanner = new Scanner(polynomial);
      while (scanner.hasNext()) {
        String term = scanner.next();
        int coefficient = 0;
        int power = 0;
        if (term.contains("x")) {
          String[] parts = term.split("x\\^"); // split on x^
          coefficient = (parts.length > 0 && !parts[0].isEmpty()) ? Integer.parseInt(parts[0]) : 1;
          power = parts.length > 1 ? Integer.parseInt(parts[1]) : 1;
        } else {
          coefficient = Integer.parseInt(term);
        }
        addTerm(coefficient, power);
      }
      scanner.close();
  }


  /**
   * This method adds another Polynomial to current Polynomial and returns a new polynomial obtained
   * by adding the two polynomials, and this method does not mutate either polynomial.
   * @param other the other polynomial to be added
   * @return a new Polynomial with added terms of 2 Polynomials
   * @throws IllegalArgumentException if the other Polynomial is not the same class
   */
  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (!(other instanceof PolynomialImpl)) {
      throw new IllegalArgumentException("The other Polynomial object is not the same concrete " +
              "type as the current object");
    }
    PolynomialImpl otherPoly = (PolynomialImpl) other;
    ListNode current = head;
    ListNode otherCurrent = otherPoly.head;
    PolynomialImpl addedPoly = new PolynomialImpl();

    while (current != null) {
      addedPoly.addTerm(current.coefficient, current.power);
      current = current.next;
    }
    while (otherCurrent != null) {
      addedPoly.addTerm(otherCurrent.coefficient, otherCurrent.power);
      otherCurrent = otherCurrent.next;
    }
    return addedPoly;
  }

  /**
   * This method takes a coefficient and a power and adds the resulting term to the polynomial
   * @param coefficient, the coefficient of the term to be added
   * @param power, the power of the term to be added
   * @throws IllegalArgumentException if the power of the term is negative
   * This method builds the polynomial term-by-term, iterate from the head
   */
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power of the term cannot be negative");
    }
    if (coefficient == 0) {
      return;
    }
    ListNode newNode = new ListNode(coefficient, power);
    // add to the front of the linked list
    if (head == null || power > head.power) {
      newNode.next = head;
      head = newNode;
      return;
    }
    // remove current head if coefficient is 0
    if (power == head.power) {
      head.coefficient += coefficient;
      if (head.coefficient == 0) {
        head = head.next;
      }
      return;
    }

    // insert somewhere in the linked list
    ListNode current = head;
    ListNode dummy = null;
    while (current != null) {
      if (current.power == power) {
        // update
        current.coefficient += coefficient;
        if (current.coefficient == 0) {
          // remove current is coefficient becomes 0
          dummy.next = current.next;
        }
        return;
      } else if (current.power > power) {
        // move
        dummy = current;
        current = current.next;
      } else {
        // insert
        dummy.next = newNode;
        newNode.next = current;
        return;
      }
    }
    // reaches the end of the linked list
    dummy.next = newNode;

  }

  /**
   * This method checks if another polynomial belongs to the same concrete type and has the same terms
   * @param poly, another polynomial for comparison
   * @return true if the 2 polynomials are the same type and has the same terms, return false otherwise
   */
  @Override
  public boolean isSame(Polynomial poly) {
    if (poly instanceof PolynomialImpl) {
      PolynomialImpl otherPoly = (PolynomialImpl) poly;
      ListNode current = head;
      ListNode otherCurrent = otherPoly.head;

      while (current != null && otherCurrent != null) {
        if (current.coefficient != otherCurrent.coefficient || current.power != otherCurrent.power) {
          return false;
        }
        current = current.next;
        otherCurrent = otherCurrent.next;
      }
      return current == null && otherCurrent == null;
    }
    return false;
  }

  /**
   * This method takes a double number and returns a double-precision result
   * @param x, the value at which the polynomial is to be evaluated
   * @return the calculated result with value of x
   */
  @Override
  public double evaluate(double x) {
    double result = 0;
    ListNode current = head;
    while (current != null) {
        result += current.coefficient * Math.pow(x, current.power);
        current = current.next;
    }
    return result;
  }

  /**
   * This method takes a power and returns the coefficient of the term with that power
   * @param power, the power whose coefficient is sought
   * @return the coefficient of target power, return 0 if the coefficient of the power does not exist
   */
  @Override
  public int getCoefficient(int power) {
    ListNode current = head;
    while (current != null && current.power != power) {
      current = current.next;
    }
    return (current==null) ? 0: current.coefficient;
  }

  /**
   * @return the degree of current polynomial, which is the power of the head node
   */
  @Override
  public int getDegree() {
    return head == null? 0: head.power;
  }

  /**
   * @return the string format of current polynomial
   */
  @Override
  public String toString() {
    if (head == null) {
      return "0";
    }
    ListNode current = head;
    StringBuilder acc = new StringBuilder();
    while (current != null) {
      acc.append(current.coefficient > 0 && current != head ? " +" : "")
              .append(current.coefficient < 0 && current != head ? " "+ current.coefficient : current.coefficient )
              .append(current.power > 0 ? "x^"+current.power : "");
      current = current.next;
    }
    return acc.toString();
  }


  /**
   * This is the static ListNode class that is used for generating LinkedList
   */
  private static class ListNode {
    private int coefficient;
    private final int power;
    private ListNode next;

    /**
     * constructor of ListNode class with 3 input parameters
     * @param coefficient, coefficient of the polynomial term, int number in front of x
     * @param power, power of the polynomial term, int number on top right corner of x
     * @param next, next ListNode that current one is pointing towards
     */
    private ListNode(int coefficient, int power, ListNode next) {
      this.coefficient = coefficient;
      this.power = power;
      this.next = next;
    }

    /**
     * constructor of ListNode class with 2 input parameters, default next to be null
     * @param coefficient, coefficient of the polynomial term
     * @param power, power of the polynomial term
     */
    private ListNode(int coefficient, int power) {
      this(coefficient, power,null);
    }
  }



}
