package questions;
import java.util.Objects;

/**
 * This is the MultipleChoice question class, implements Question interface and overrides compareTo method
 */
public class MultipleChoice implements Question {
  private final String questionText;
  private final String correctAnswer;

  /**
   * constructor of MultipleChoice question class
   * @param questionText, text string of the question
   * @param correctAnswer, int string of the correct answer, only 1 option is correct. e.g. "1" or "3"
   * @param options, answer options of the String that are given
   * throw IllegalArgumentException error if there are less than 3 or more than 8 options
   */
  public MultipleChoice(String questionText, String correctAnswer, String... options) {
    if (options.length < 3 || options.length > 8) {
      throw new IllegalArgumentException("Number of options for the question must be 3-8.");
    }
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
   * type (both MultipleChoice); return 1 if the other question is TrueFalse type; return -1 if the
   * other question is MultipleSelect or Likert
   */
  @Override
  public int compareTo(Question o) {
    if (o instanceof MultipleChoice) {
      return this.getText().compareTo(o.getText()); // lexicographical order
    } else if (o instanceof TrueFalse) {
      return 1; // after TF
    } else {
      return -1; // before other types
    }
  }

}
