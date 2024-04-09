public class Queen extends ChessPiece {
    public Queen(String color) {
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

            int dirX = toColumn - column;
            int dirY = toLine - line;
            boolean diag = Math.abs(dirX) == Math.abs(dirY) && dirX != 0;
            boolean horizontal = dirX != 0 && dirY == 0;
            boolean vertical = dirY != 0 && dirX == 0;

            int stepX = toColumn - column > 0 ? 1 : -1;
            int stepY = toLine - line > 0 ? 1 : -1;

            // Проверяем наличие фигур на пути в зависимости от направления движения.
            if (diag) {
                for (int i = line + stepY, j = column + stepX; i != toLine && j != toColumn; i += stepY, j += stepX) {
                    hasObstacle = chessBoard.board[i][j] != null;
                    if (hasObstacle) break;
                }
            } else if (horizontal) {
                for (int i = column + stepX; i != toColumn; i += stepX) {
                    hasObstacle = chessBoard.board[line][i] != null;
                    if (hasObstacle) break;
                }
            } else if (vertical) {
                for (int i = line + stepY; i != toLine; i += stepY) {
                    hasObstacle = chessBoard.board[i][column] != null;
                    if (hasObstacle) break;
                }
            }

            return (diag || horizontal || vertical) && !hasObstacle && (isEmpty(chessBoard, toLine, toColumn) || isEnemy(chessBoard, toLine, toColumn));
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
