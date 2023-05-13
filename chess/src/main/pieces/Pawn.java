package main.pieces;

import main.board.Board;
import main.pieces.enums.PieceColor;

public class Pawn extends Piece {

    public Pawn(int px, int py, PieceColor color) {
        super(px, py, color);
        this.notation = getNotationSide("P");
    }

    @Override
    public String toString() {
        if (this.color == PieceColor.BLACK) {
            return BLACKCOLOR + (char) Integer.parseInt(String.valueOf(0x265F));
        }
        return WHITECOLOR + (char) Integer.parseInt(String.valueOf(0x2659));
    }

    @Override
    public boolean movementIsValid(int nextX, int nextY, Board board) {
        if (this.color == PieceColor.WHITE) {

            if (nextX == this.px && nextY == this.py + 1) {
                return !board.hasPieceInPosition(nextX, nextY);
            }

            if (nextX == this.px && nextY == this.py + 2 && nextY <= 3) {
                return !board.hasPieceInPosition(nextX, nextY - 1) && !board.hasPieceInPosition(nextX, nextY);
            }

            if (nextX == this.px + 1 && nextY == this.py + 1) {
                return canCapture(nextX, nextY, board);
            }

            if (nextX == this.px - 1 && nextY == this.py + 1) {
                return canCapture(nextX, nextY, board);
            }

        }

        if (this.color == PieceColor.BLACK) {

            if (nextX == this.px && nextY == this.py - 1) {
                return !board.hasPieceInPosition(nextX, nextY);
            }

            if (nextX == this.px && nextY == this.py - 2 && nextY >= 4) {
                return !board.hasPieceInPosition(nextX, nextY + 1) && !board.hasPieceInPosition(nextX, nextY);
            }

            if (nextX == this.px + 1 && nextY == this.py - 1) {
                return canCapture(nextX, nextY, board);
            }

            if (nextX == this.px - 1 && nextY == this.py - 1) {
                return canCapture(nextX, nextY, board);
            }
        }
        return false;
    }

}
