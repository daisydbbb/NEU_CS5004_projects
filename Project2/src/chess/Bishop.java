package chess;

/**
 * This is the Bishop chess class, extents AbstractChess class and overrides canMove method
 */
public class Bishop extends AbstractChess {
  /**
   * constructor of Bishop chess class
   * @param currentRow, row index of current bishop
   * @param currentCol, col index of current bishop
   * @param color, color of current bishop (WHITE/BLACK)
   */
  public Bishop(int currentRow, int currentCol, Color color) {
    super(currentRow, currentCol, color);
  }

  /**
   * Bishop move in diagonal, which means same row step diff and col step diff
   * e.g. [1, 1] and [3, -1] is at diagonal and have the diff = 2
   * @param row the row where the piece might be moved to.
   * @param col the column where the piece might be moved to.
   * @return true if in boundary and the row diff is the same as col diff, return false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    boolean inBoundary = super.inBoundary(row, col);
    int rowDiff = Math.abs(row - this.currentRow);
    int colDiff = Math.abs(col - this.currentCol);
    return inBoundary && (rowDiff == colDiff);
  }
}
