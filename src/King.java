public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkBoundaries(line, column) && checkBoundaries(toLine, toColumn)) {
            int xDiff = Math.abs(toLine - line);
            int yDiff = Math.abs(toColumn - column);
            return (xDiff < 2) && (yDiff < 2) && ((xDiff > 0) || (yDiff > 0)) && (isEmpty(chessBoard, toLine, toColumn) || isEnemy(chessBoard, toLine, toColumn));
        }
        return false;
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        if (checkBoundaries(line, column) && checkBoundaries(line, column)) {
            String colorToCheck = this.color.equals("White") ? "Black" : "White";
            boolean ret = false;
            loop:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    ChessPiece current = board.board[i][j];
                    if (current != null && current.getColor().equals(colorToCheck)) {
                        ret = current.canMoveToPosition(board, i, j, line, column);
                        if (ret) break loop;
                    }
                }
            }
            return ret;
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
