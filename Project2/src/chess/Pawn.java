package chess;

/**
 * This is the Pawn chess class, extents AbstractChess class and override canMove and canKill methods
 */
public class Pawn extends AbstractChess {
  protected boolean isFirst;

  /**
   * constructor of Bishop chess class
   * add a new variable isFirst that indicate whether it's the first move
   * @param currentRow, row index of current pawn,
   *                    throw error if row < 1 for white and row > 6 for black
   * @param currentCol, col index of current pawn
   * @param color, row index of current pawn
   * isFirst is determined by color and position
   * if pawn color is white and at row 1, or pawn color is black and at row 6, it's first move
   * otherwise it's not first move
   */
  public Pawn (int currentRow, int currentCol, Color color) throws IllegalArgumentException {
    super(currentRow, currentCol, color);

    if (color == Color.WHITE && currentRow < 1) {
      throw new IllegalArgumentException("White Pawn cannot be below row 1");
    }
    if (color == Color.BLACK &&currentRow > 6) {
      throw new IllegalArgumentException("Black Pawn cannot be above row 6");
    }

    // determine if it's the first move based on the row
    this.isFirst = (this.color == Color.BLACK && this.currentRow == 6) ||
            (this.color == Color.WHITE && this.currentRow == 1);
  }

  /**
   * Pawn only moves "ahead", which means white goes up(row index +), black goes down(row index -)
   * can move 1 or 2 steps if first time move, else always move 1 step
   * @param row the row where the piece might be moved to.
   * @param col the column where the piece might be moved to.
   * @return true if in boundary and white moves up and black moves down, return false otherwise
   */
  @Override
  public boolean canMove(int row, int col) {
    boolean inBoundary = super.inBoundary(row, col);
    if (!inBoundary) {
      return false;
    }
    int colDiff = Math.abs(col - this.currentCol);
    if (colDiff != 0) {
      return false;
    }

    int rowStep = row - this.currentRow;
    if (this.color == Color.WHITE) {
      if (this.isFirst) {
        return rowStep == 1 || rowStep == 2;
      } else {
        return rowStep == 1;
      }
    } else {
      if (this.isFirst) {
        return rowStep == -1 || rowStep == -2;
      } else {
        return rowStep == -1;
      }
    }
  }

  /**
   * to kill, pawn must move one place "forward" diagonally
   * @param piece the piece that might be killed.
   * @return true if the target piece is in boundary and in different color, and also with 1 col
   * diff and 1 row diff (white row + and black row -)
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    int rowStep = piece.getRow() - this.currentRow;
    int colDiff = Math.abs(piece.getColumn() - this.currentCol);
    Color pieceColor = piece.getColor();
    if (pieceColor == getColor()) {
      return false;
    }
    else if (pieceColor == Color.WHITE && getColor() == Color.BLACK) {
      // black moves down (row-)
      return (colDiff == 1 && rowStep == -1);
    }
    else {
      // white moves up (row+)
      return (colDiff == 1 && rowStep == 1);
    }
  }
}
