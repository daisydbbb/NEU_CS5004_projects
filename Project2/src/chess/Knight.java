package chess;

/**
 * This is the Knight chess class, extents AbstractChess class and overrides canMove method
 */
public class Knight extends AbstractChess {
  /**
   * constructor of Knight chess class
   * @param currentRow, row index of current knight
   * @param currentCol, col index of current knight
   * @param color, color of current knight (WHITE/BLACK)
   */
  public Knight(int currentRow, int currentCol, Color color) {
    super(currentRow, currentCol, color);
  }

  /**
   * Knight move in L shape, which is 2 row step diff and 1 col step diff,
   * or 1 row step diff and 2 col step diff
   * @param row the row where the piece might be moved to.
   * @param col the column where the piece might be moved to.
   * @return true if in boundary and dest is in L distance, else return false
   */
  @Override
  public boolean canMove(int row, int col) {
    // inherit from abstract class
    boolean inBoundary = super.inBoundary(row, col);
    int rowDiff = Math.abs(row - this.currentRow);
    int colDiff = Math.abs(col - this.currentCol);
    return inBoundary && ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2));
  }

}
