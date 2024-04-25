package chess;

/**
 * This is the Queen chess class, extents AbstractChess class and overrides canMove method
 */
public class Queen extends AbstractChess {
  /**
   * constructor of Queen chess class
   * @param currentRow, row index of current queen
   * @param currentCol, col index of current queen
   * @param color, color of current queen (WHITE/BLACK)
   */
  public Queen (int currentRow, int currentCol, Color color) {
    super(currentRow, currentCol, color);
  }

  /**
   * Queen move horizontally，vertically or diagonally
   * @param row the row where the piece might be moved to.
   * @param col the column where the piece might be moved to.
   * @return true if in boundary and destination is in same row or same col or has same row
   * and col diff, return false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    boolean inBoundary = super.inBoundary(row, col);
    int rowDiff = Math.abs(row - this.currentRow);
    int colDiff = Math.abs(col - this.currentCol);
    return inBoundary && ((rowDiff == 0 || colDiff == 0) || (rowDiff == colDiff));
  }
}
