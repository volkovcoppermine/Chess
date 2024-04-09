public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkBoundaries(line, column) && checkBoundaries(toLine, toColumn)) {
            // Первый ли это ход пешки?
            boolean start = (color.equals("White") && line == 1) || (color.equals("Black") && line == 6);
            // Пешка может двигаться только вперёд. "Вверх" - белые, "вниз" - чёрные.
            boolean forward = (color.equals("White") && toLine > line) || (color.equals("Black") && toLine < line);
            // Проверяем, что пешка не двигалась по горизонтали.
            boolean vertical = toColumn - column == 0;

            int dir = toLine - line;
            switch (dir) {
                case 1:
                case -1:
                    return forward && vertical && (isEmpty(chessBoard, toLine, toColumn) || isEnemy(chessBoard, toLine, toColumn));
                case 2:
                case -2:
                    boolean hasObstacle = dir > 0 ? chessBoard.board[toLine - 1][toColumn] != null : chessBoard.board[toLine + 1][toColumn] != null;
                    return start && forward && vertical && !hasObstacle && (isEmpty(chessBoard, toLine, toColumn) || isEnemy(chessBoard, toLine, toColumn));
                default:
                    return false;
            }
        }
        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
