package main.pieces;

import main.board.Board;
import main.pieces.enums.PieceColor;

public class Bishop extends Piece {
    public Bishop(int px, int py, PieceColor color) {
        super(px, py, color);
        this.notation = getNotationSide("B");
    }

    @Override
    public String toString() {
        if (this.color == PieceColor.BLACK) {
            return BLACKCOLOR + (char) Integer.parseInt(String.valueOf(0x265D));
        }
        return WHITECOLOR + (char) Integer.parseInt(String.valueOf(0x2657));
    }

    private final int[][] directions = { { 1, -1 }, { 1, 1 }, { -1, -1 }, { -1, 1 } };

    @Override
    public boolean movementIsValid(int nextX, int nextY, Board board) {
        for (int i = 1; i <= 7; i++) {
            for (int[] direction : directions) {
                if (nextX == this.px + i * direction[0] && nextY == this.py + i * direction[1]) {
                    for (int j = 1; j < i; j++) {
                        if (board.hasPieceInPosition(this.px + j * direction[0], this.py + j * direction[1])) {
                            return false;
                        }
                    }
                    return !board.hasPieceInPosition(nextX, nextY) || canCapture(nextX, nextY, board);
                }
            }
        }
        return false;
    }

}
