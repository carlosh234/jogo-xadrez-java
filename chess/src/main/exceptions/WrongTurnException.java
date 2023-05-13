package main.exceptions;

import main.pieces.enums.PieceColor;

public class WrongTurnException extends RuntimeException{
    public WrongTurnException(String msg) {
        super(msg);
    }
}
