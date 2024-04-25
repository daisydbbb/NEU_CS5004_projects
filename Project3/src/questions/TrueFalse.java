package questions;

import java.util.Objects;

/**
 * This is the TrueFalse question class, implements Question interface and overrides compareTo method
 */
public class TrueFalse implements Question {
  private final String questionText;
  private final String correctAnswer;

  /**
   * constructor of TrueFalse question class
   * @param questionText, text string of the question
   * @param correctAnswer, "True" or "False" answer of the question
   */
  public TrueFalse(String questionText, String correctAnswer) {
    this.questionText = questionText;
    this.correctAnswer = correctAnswer;
  }

  /**
   * Determines if the answer is correct for the question
   * @param answer the answer given
   * @return CORRECT string if given answer matches the correctAnswer, otherwise return INCORRECT string
   */
  public String answer(String answer) {
    if (Objects.equals(answer, this.correctAnswer)) {
      return CORRECT;
    } else {
      return INCORRECT;
    }
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
   * @param o the object to be compared.
   * @return lexicographical order (letters sorted from A-Z) if two questions are the same
   * type (both TrueFalse); return -1 if two questions are different types according to the order
   */
  @Override
  public int compareTo(Question o) {
    if (o instanceof TrueFalse) {
      return this.getText().compareTo(o.getText()); // lexicographical order
    } else {
      return -1; // TF always comes the first
    }
  }
}
