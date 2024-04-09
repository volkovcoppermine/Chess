public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkBoundaries(line, column) && checkBoundaries(toLine, toColumn)) {
            boolean hasObstacle = false;

            // Проверяем, что движение происходит либо только по горизонтали, либо только по вертикали
            boolean horizontal = column != toColumn;
            boolean vertical = line != toLine;

            if (horizontal == vertical) return false;

            int stepX = toColumn - column > 0 ? 1 : -1;
            int stepY = toLine - line > 0 ? 1 : -1;

            // Проверяем наличие фигур на пути в зависимости от направления движения.
            if (horizontal) {
                for (int i = column + stepX; i != toColumn; i += stepX) {
                    hasObstacle = chessBoard.board[line][i] != null;
                    if (hasObstacle) break;
                }
            } else {
                for (int i = line + stepY; i != toLine; i += stepY) {
                    hasObstacle = chessBoard.board[i][column] != null;
                    if (hasObstacle) break;
                }
            }

            return !hasObstacle && (isEmpty(chessBoard, toLine, toColumn) || isEnemy(chessBoard, toLine, toColumn));
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}