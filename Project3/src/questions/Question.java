package questions;

/**
 * This interface represents a general question on a computer-based test, and it applies to all four
 * types of questions: Likert, TrueFalse, MultipleChoice and MultipleSelect
 * Name: Xin Ding
 * Date: 3/11/2024
 */
public interface Question extends Comparable<Question> {
  
  final String CORRECT = "Correct";
  final String INCORRECT = "Incorrect";

  /**
   * Determines if the answer is correct for a given question. If the answer is
   * correct, this method returns "Correct"; and "Incorrect" otherwise.
   * 
   * @param answer the answer given
   * @return "Correct" or "Incorrect"
   */
  String answer(String answer);

  /**
   * Returns the text of the question.
   * 
   * @return the text
   */
  String getText();
}
