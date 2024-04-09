public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (checkBoundaries(line, column) && checkBoundaries(toLine, toColumn)) {
            // Конь смещается на две клетки по горизонтали и одну по вертикали.
            boolean yCondition = (Math.abs(toLine - line) == 2) && (Math.abs(toColumn - column) == 1);
            // Или на две клетки по вертикали и одну по горизонтали.
            boolean xCondition = (Math.abs(toLine - line) == 1) && (Math.abs(toColumn - column) == 2);
            // Всё и сразу.
            return (xCondition || yCondition) && (isEmpty(chessBoard, toLine, toColumn) || isEnemy(chessBoard, toLine, toColumn));
        }
        return false;
    }
}