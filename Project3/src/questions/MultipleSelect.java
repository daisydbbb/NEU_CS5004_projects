package questions;

import java.util.Arrays;

/**
 * This is the MultipleSelect question class, implements Question interface and overrides compareTo method
 */
public class MultipleSelect implements Question{
  private final String questionText;
  private final String correctAnswer;

  /**
   * constructor of MultipleSelect question class
   * @param questionText, text string of the question
   * @param correctAnswer, string of the correct answer, multiple answers could be correct, e.g.
   * "1", "1 3", "4 1" are all valid correct answers
   * @param options, answer options of the String that are given
   * throw IllegalArgumentException error if there are less than 3 or more than 8 options
   */
  public MultipleSelect(String questionText, String correctAnswer, String... options) {
    if (options.length < 3 || options.length > 8) {
      throw new IllegalArgumentException("Number of options for the question must be 3-8.");
    }
    this.questionText = questionText;
    this.correctAnswer = correctAnswer;
  }

  /**
   * Determines if the answer is correct for the question
   * @param answer the answer given
   * @return CORRECT string if given answer matches the correctAnswer in ANY order, otherwise
   * return INCORRECT string
   * implementation: convert user given answer and correct answer into arrays, sort then in ascending
   * order and then convert them back to strings and compare if they are equal
   */
  public String answer(String answer) {
    char[] answerArray = answer.toCharArray();
    Arrays.sort(answerArray);
    char[] correctAnswerArray = correctAnswer.toCharArray();
    Arrays.sort(correctAnswerArray);
    if (new String(answerArray).equals(new String(correctAnswerArray))) {
      return CORRECT;
    }
    else {
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
   * type (both MultipleChoice); return -1 if the other question is Likert type; return 1 if the
   * other question is MultipleChoice or TrueFalse
   */
  @Override
  public int compareTo(Question o) {
    if (o instanceof MultipleSelect) {
      return this.getText().compareTo(o.getText()); // lexicographical order
    } else if (o instanceof Likert) {
      return -1; // before likert
    } else {
      return 1; // after other types
    }
  }
}
