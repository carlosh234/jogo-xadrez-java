package main.pieces;

import main.board.Board;
import main.pieces.enums.PieceColor;

public class Knight extends Piece {

    public Knight(int px, int py, PieceColor color) {
        super(px, py, color);
        this.notation = getNotationSide("N");
    }

    private final int[][] directions = { { 1, 2 }, { -1, 2 }, { 1, -2 }, { -1, -2 }, { 2, 1 }, { 2, -1 }, { -2, 1 },
            { -2, -1 } };

    @Override
    public String toString() {
        if (this.color == PieceColor.BLACK) {
            return BLACKCOLOR + (char) Integer.parseInt(String.valueOf(0x265E));
        }
        return WHITECOLOR + (char) Integer.parseInt(String.valueOf(0x2658));
    }

    @Override
    public boolean movementIsValid(int nextX, int nextY, Board board) {

        for (int[] direction : directions) {
            int possibleX = this.px + direction[0];
            int possibleY = this.py + direction[1];
            if (nextX == possibleX && nextY == possibleY) {
                return !board.hasPieceInPosition(nextX, nextY) || canCapture(nextX, nextY, board);
            }
        }
        return false;
    }

}
