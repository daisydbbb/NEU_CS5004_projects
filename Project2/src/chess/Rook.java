package chess;

/**
 * This is the Rook chess class, extents AbstractChess class and overrides canMove method
 */
public class Rook extends AbstractChess{
  /**
   * constructor of Rook chess class
   * @param currentRow, row index of current rook
   * @param currentCol, col index of current rook
   * @param color, color of current rook (WHITE/BLACK)
   */
  public Rook(int currentRow, int currentCol, Color color) {
    super(currentRow, currentCol, color);
  }

  /**
   * Rook move horizontally or vertically, that means the row or the col is the same
   * @param row the row where the piece might be moved to.
   * @param col the column where the piece might be moved to.
   * @return true if in boundary and row diff or col diff is 0, return false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    boolean inBoundary = super.inBoundary(row, col);
    int rowDiff = Math.abs(row - this.currentRow);
    int colDiff = Math.abs(col - this.currentCol);
    return inBoundary && (rowDiff == 0 || colDiff == 0);
  }
}
