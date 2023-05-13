package main.pieces;

import main.board.Board;
import main.pieces.enums.PieceColor;

public abstract class Piece {
    protected int px;

    protected int py;

    protected PieceColor color;

    protected String notation;

    protected final String BLACKCOLOR = "\u001B[30m";

    protected final String WHITECOLOR = "\u001B[33m";

    public Piece(int px, int py, PieceColor color) {
        this.px = px;
        this.py = py;
        this.color = color;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public String getNotation() {
        return notation;
    }

    public String getNotationSide(String notation) {
        if (this.color == PieceColor.BLACK) {
            return  "b" + notation;
        } else {
            return  "w" + notation;
        }
    }

    public abstract boolean movementIsValid(int px, int py, Board board);

    protected boolean canCapture(int nextX, int nextY, Board board) {
        return board.getPieceAtPosition(nextX, nextY) != null
                && board.getPieceAtPosition(nextX, nextY).getColor() != this.color;
    }

}
