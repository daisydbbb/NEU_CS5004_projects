package chess;

/**
 * This is the abstract class for all the chess pieces, they all share the same construction,
 * getRow(), getColumn(), getColor() and canKill() methods (except Pawn). canMove() method varies
 * based on different types of the chess
 * Name: Xin Ding
 * Edited: 3/12/2024
 */
public abstract class AbstractChess implements ChessPiece {
  protected int currentRow;
  protected int currentCol;
  protected Color color;

  /**
   * constructor of abstract chess class
   * @param currentRow, the row index of chess
   * @param currentCol, the col index of chess
   * @param color, the color of chess, from enum, only WHITE or BLACK
   * throw IllegalArgumentException if the starting position is out of the chess boundary [0,7]
   */
  public AbstractChess(int currentRow, int currentCol, Color color) throws IllegalArgumentException {
    if (currentRow < 0 || currentRow > 7 || currentCol < 0 || currentCol > 7) {
      throw new IllegalArgumentException("The chess piece position is out of board boundary");
    }
    this.currentRow = currentRow;
    this.currentCol = currentCol;
    this.color = color;
  }

  /**
   * get the current row index and return the value
   * @return the value of this.currentRow
   */
  public int getRow() {
    return this.currentRow;
  }

  /**
   * get the current col index and return the value
   * @return the value of this.currentCol
   */
  public int getColumn() {
    return this.currentCol;
  }

  /**
   * get the color of the chess and return the Color
   * @return the Color enum of this.color
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * determine whether the chess piece is in boundary of the chess board
   * @param row the row where the piece might be moved to.
   * @param col the column where the piece might be moved to.
   * @return true if target position is in boundary and is different from current position,
   * return false otherwise
   */
  protected boolean inBoundary(int row, int col) {
    boolean isSame = (row == this.currentRow && col == this.currentCol);
    // dest needs to be at a different position and in boundary
    return !isSame && (row >= 0 && row <= 7 && col >= 0 && col <= 7);
  }

  /**
   * determine whether the current chess piece can kill another chess piece
   * @param piece the piece that might be killed.
   * @return true if current chess is in different color and can move to the position of another chess
   * this method is the same across all the types except for Pawn
   */
  public boolean canKill(ChessPiece piece) {
    // return true if it can move to the destinations and 2 pieces are different colors
    return canMove(piece.getRow(), piece.getColumn()) && getColor() != piece.getColor();
  }


}
