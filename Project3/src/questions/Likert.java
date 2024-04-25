package questions;

/**
 * This is the Likert question class, implements Question interface and overrides compareTo method
 */
public class Likert implements Question {

  private final String questionText;

  /**
   * constructor of Likert question class
   * @param questionText, text string of the question
   */
  public Likert(String questionText) {
    this.questionText = questionText;
  }

  /**
   * Determines if the answer is correct for the question
   * For Likert, question can be answered in 5-point scale: 5-Strongly Agree, 4-Agree, 3-Neither Agree
   * nor Disagree, 2-Disagree, 1-Strongly Disagree. Any valid option number is a "correct" answer
   *
   * @param answer the answer given
   * @return CORRECT string if answer within range 1-5, otherwise return INCORRECT string
   * Catch NumberFormatException error if the given answer is not an integer
   */
  public String answer(String answer) {
    try {
      int answerInt = Integer.parseInt(answer);
      if (answerInt >= 1 && answerInt <=5 ) {
        return CORRECT;
      }
    } catch (NumberFormatException e) {
      System.out.println("The answer is not valid!");
    }
    return INCORRECT;
  }

  /**
   *
   * @return the text string of the question
   */
  public String getText() {
    return this.questionText;
  }

  /**
   * Compare current question with another question and determine the order
   * Different question types order: true/false < multiple-choice < multiple-select < likert
   * @param o the object to be compared
   * @return lexicographical order (letters sorted from A-Z) if two questions are the same
   * type (both Likert); return 1 if two questions are different types according to the order
   */
  @Override
  public int compareTo(Question o) {
    if (o instanceof Likert) {
      return this.getText().compareTo(o.getText()); // lexicographical order
    } else {
      return 1; // Likert always comes the last
    }
  }
}
