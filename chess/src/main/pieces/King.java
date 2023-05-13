package main.pieces;

import main.board.Board;
import main.chessgame.ChessGame;
import main.pieces.enums.PieceColor;

import java.util.List;

public class King extends Piece {

    private final int[][] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { -1, -1 }, { 1, -1 },
            { -1, 1 } };

    public King(int px, int py, PieceColor color) {
        super(px, py, color);
        this.notation = getNotationSide("K");

    }

    @Override
    public String toString() {
        if (this.color == PieceColor.BLACK) {
            return BLACKCOLOR + (char) Integer.parseInt(String.valueOf(0x265A));
        }
        return WHITECOLOR + (char) Integer.parseInt(String.valueOf(0x2654));
    }

    @Override
    public boolean movementIsValid(int nextX, int nextY, Board board) {

        for (int[] direction : directions) {
            if (nextX == this.px + direction[0] && nextY == this.py + direction[1]) {
                return !board.hasPieceInPosition(nextX, nextY) || canCapture(nextX, nextY, board);
            }
        }
        return false;
    }

    public boolean inCheck(Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boolean isEnemyPiece = board.hasPieceInPosition(i,j) && board.getPieceAtPosition(i, j).getColor() != this.color;
                Piece piece = board.getPieceAtPosition(i, j);
                if (isEnemyPiece && piece.movementIsValid(this.px, this.py, board)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isMate(Board board, ChessGame chessGame) {
        List<Piece> allyPieceList = board.getPiecesOnBoard().stream().filter(p -> p.getColor() == this.color).toList();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                    for(Piece piece : allyPieceList) {
                        if(board.successfulMove(piece.getPx(),piece.getPy(),i,j,chessGame)) {
                            return false;
                        }
                    }
            }
        }
        return true;
    }

}
