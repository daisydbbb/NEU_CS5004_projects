package chess;

/**
 * This is the King chess class, extents AbstractChess class and overrides canMove method
 */
public class King extends AbstractChess{
  /**
   * constructor of King chess class
   * @param currentRow, row index of current king
   * @param currentCol, col index of current king
   * @param color, color of current king (WHITE/BLACK)
   */
  public King (int currentRow, int currentCol, Color color) {
    super(currentRow, currentCol, color);
  }

  /**
   * King move 1 square in either horizontal，vertical or diagonal,
   * this means the row diff or col diff between current position and destination must be 1
   * @param row the row where the piece might be moved to.
   * @param col the column where the piece might be moved to.
   * @return true if in boundary and in 1 of the neighboring 8 positions, return false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    boolean inBoundary = super.inBoundary(row, col);
    int rowDiff = Math.abs(row - this.currentRow);
    int colDiff = Math.abs(col - this.currentCol);
    return inBoundary && ((rowDiff == 1 && colDiff == 1) ||
            (rowDiff == 0 && colDiff == 1) || (rowDiff == 1 && colDiff == 0));
  }
}
