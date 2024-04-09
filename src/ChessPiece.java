public abstract class ChessPiece {
    protected String color;
    protected boolean check = true;

    protected ChessPiece(String color) {
        this.color = color;
    }

    // Пуста ли клетка, в которую мы перемещаем фигуру?
    protected boolean isEmpty(ChessBoard chessBoard, int line, int column) {
        return chessBoard.board[line][column] == null;
    }

    // Если клетка непустая, то это фигура противника?
    protected boolean isEnemy(ChessBoard chessBoard, int line, int column) {
        if (!isEmpty(chessBoard, line, column)) {
            return !chessBoard.board[line][column].getColor().equals(color);
        } else return false;
    }

    public abstract String getColor();

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    public abstract String getSymbol();

    protected boolean checkBoundaries(int line, int column) {
        return (line >= 0 && line < 8) && (column >= 0 && column < 8);
    }
}