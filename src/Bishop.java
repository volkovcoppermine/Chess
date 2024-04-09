public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkBoundaries(line, column) && checkBoundaries(toLine, toColumn)) {
            // Проверяем, что движение происходит по диагонали и мы не ходим в начальную клетку.
            boolean diag = Math.abs(toLine - line) == Math.abs(toColumn - column) && toColumn - column != 0;
            // Проверяем, что на пути слона нет других фигур.
            boolean hasObstacle = false;

            if (diag) {
                int stepX = toColumn - column > 0 ? 1 : -1;
                int stepY = toLine - line > 0 ? 1 : -1;

                for (int i = line + stepY, j = column + stepX; i != toLine && j != toColumn; i += stepY, j += stepX) {
                    hasObstacle = chessBoard.board[i][j] != null;
                    if (hasObstacle) break;
                }
            }
            return diag && !hasObstacle && (isEmpty(chessBoard, toLine, toColumn) || isEnemy(chessBoard, toLine, toColumn));
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
