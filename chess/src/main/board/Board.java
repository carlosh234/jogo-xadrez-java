package main.board;

import main.exceptions.EmptyPositionException;
import main.chessgame.ChessGame;
import main.exceptions.WrongTurnException;
import main.utils.Message;
import main.utils.MsgColor;
import main.pieces.King;
import main.pieces.Piece;
import main.pieces.enums.PieceColor;
import main.exceptions.InvalidMovementException;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final Piece[][] pieces;
    private final List<Piece> movedPieces;
    private final List<Piece> piecesOnBoard;
    private final List<Piece> whiteCapturedPieces;
    private final List<Piece> blackCapturedPieces;

    public Board(int row, int column) {
        pieces = new Piece[row][column];
        movedPieces = new ArrayList<>();
        piecesOnBoard = new ArrayList<>();
        whiteCapturedPieces = new ArrayList<>();
        blackCapturedPieces = new ArrayList<>();
    }


    public Piece[][] getPieces() {
        return pieces;
    }

    public List<Piece> getMovedPieces() {return movedPieces;}

    public List<Piece> getPiecesOnBoard() {
        return piecesOnBoard;
    }

    public List<Piece> getWhiteCapturedPieces() {
        return whiteCapturedPieces;
    }

    public List<Piece> getBlackCapturedPieces() {
        return blackCapturedPieces;
    }

    public Piece getPieceAtPosition(int x, int y) {
        return getPieces()[x][y];
    }

    public void setPieceOnBoard(Piece piece) {
        pieces[piece.getPx()][piece.getPy()] = piece;
        piecesOnBoard.add(piece);
    }

    public boolean hasPieceInPosition(int px, int py) {
        return this.pieces[px][py] != null;
    }

    public void printBoardV1() {

        String BG_PURPLE = "\u001b[45m";
        String BG_BLACK = "\033[40m";
        String PURPLE = "\u001b[35m";
        String BG_WHITE = "\u001b[47m";
        String WHITE = "\u001B[37m";
        String RESET = "\u001B[0m";
        String fakePurplePiece = PURPLE + (char) Integer.parseInt(String.valueOf(0x2659));
        String fakeWhitePiece = WHITE + (char) Integer.parseInt(String.valueOf(0x2659)) ;

        System.out.println("Peças brancas capturadas: " + BG_BLACK + whiteCapturedPieces + RESET);
        System.out.println("  a   b   c   d   e   f   g   h");
        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j <= 7; j++) {
                if (pieces[j][i] == null) {
                    if ((i + j) % 2 == 0) {
                        System.out.print(BG_PURPLE + " " + fakePurplePiece + " " + RESET);
                    } else {
                        System.out.print(BG_WHITE + " " +  fakeWhitePiece  + " " + RESET);
                    }
                } else {
                    if ((i + j) % 2 == 0) {
                        System.out.print(BG_PURPLE + " " + pieces[j][i]  + " " + RESET);
                    } else {
                        System.out.print(BG_WHITE + " " + pieces[j][i] + " " + RESET);
                    }
                }
            }
            System.out.print(" " + (i + 1));
            System.out.println();
        }
        System.out.println("  a   b   c   d   e   f   g   h");
        System.out.println("Peças pretas capturadas: "+ BG_WHITE + blackCapturedPieces + RESET);
    }

    public void printBoardV2() {
        StringBuilder whitePieces = new StringBuilder();
        for( Piece p : whiteCapturedPieces) {
            whitePieces.append(" ").append(p.getNotation());
        }
        System.out.println("Peças brancas capturadas: "+ whitePieces);
        System.out.println("   a   b   c   d   e   f   g   h");
        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j <= 7; j++) {
                if (pieces[j][i] == null) {
                    if ((i + j) % 2 == 0) {
                        System.out.print(" -- ");
                    } else {
                        System.out.print(" -- ");
                    }
                } else {
                    if ((i + j) % 2 == 0) {
                        System.out.print(" " + pieces[j][i].getNotation()  + " ");
                    } else {
                        System.out.print(" " + pieces[j][i].getNotation() + " ");
                    }
                }
            }
            System.out.print(" " + (i + 1));
            System.out.println();
        }
        System.out.println("   a   b   c   d   e   f   g   h");

        StringBuilder blackPieces = new StringBuilder();
        for( Piece p : blackCapturedPieces) {
            blackPieces.append(" ").append(p.getNotation());
        }

        System.out.println("Peças pretas capturadas: "+ blackPieces);
    }
    public void movePiece(int currentX, int currentY, int nextX, int nextY, ChessGame chessGame)  {
        Piece pieceThatWillMove = this.getPieces()[currentX][currentY];
        assert pieceThatWillMove != null;

        if (!this.hasPieceInPosition(currentX, currentY)){
            throw new EmptyPositionException("Não há uma peça na posição!");
        }

        if(chessGame.getTurn() != pieceThatWillMove.getColor()) {
            throw new WrongTurnException("Não é a sua vez de jogar.");
        }

        if (pieceThatWillMove.movementIsValid(nextX, nextY, this)) {
            Piece capturedPiece = this.getPieceAtPosition(nextX,nextY);
            pieceThatWillMove.setPx(nextX);
            pieceThatWillMove.setPy(nextY);
            this.getPieces()[nextX][nextY] = pieceThatWillMove;
            this.getPieces()[currentX][currentY] = null;

            if(isKingInCheck(pieceThatWillMove.getColor())) {
                pieceThatWillMove.setPx(currentX);
                pieceThatWillMove.setPy(currentY);
                this.getPieces()[currentX][currentY] = pieceThatWillMove;
                this.getPieces()[nextX][nextY] = capturedPiece;
                throw new InvalidMovementException("Movimento inválido! Este movimento mantém ou coloca o rei em xeque.");
            } else {
                System.out.println("Movimento válido!");
                movedPieces.add(pieceThatWillMove);
                addCapturedPiece(capturedPiece);
                chessGame.changeTurn(pieceThatWillMove);
            }
        } else if (!pieceThatWillMove.movementIsValid(nextX, nextY, this)) {
            throw new InvalidMovementException("Movimento inválido!");
        }
    }

    public boolean successfulMove(int currentX, int currentY, int nextX, int nextY, ChessGame chessGame) {
        Piece pieceThatWillMove = this.getPieces()[currentX][currentY];
        assert pieceThatWillMove != null;

        if (!this.hasPieceInPosition(currentX, currentY)){
            return false;
        }

        if(chessGame.getTurn() != pieceThatWillMove.getColor()) {
            return false;
        }

        if (pieceThatWillMove.movementIsValid(nextX, nextY, this)) {
            Piece capturedPiece = this.getPieceAtPosition(nextX,nextY);
            pieceThatWillMove.setPx(nextX);
            pieceThatWillMove.setPy(nextY);
            this.getPieces()[nextX][nextY] = pieceThatWillMove;
            this.getPieces()[currentX][currentY] = null;

            if(isKingInCheck(pieceThatWillMove.getColor())) {
                pieceThatWillMove.setPx(currentX);
                pieceThatWillMove.setPy(currentY);
                this.getPieces()[currentX][currentY] = pieceThatWillMove;
                this.getPieces()[nextX][nextY] = capturedPiece;
                return false;
            } else {
                pieceThatWillMove.setPx(currentX);
                pieceThatWillMove.setPy(currentY);
                this.getPieces()[currentX][currentY] = pieceThatWillMove;
                this.getPieces()[nextX][nextY] = capturedPiece;
                return true;
            }
        } else if (!pieceThatWillMove.movementIsValid(nextX, nextY, this)) {
            return false;
        }
        return false;
    }

    private King findKing(PieceColor color) {
        for (Piece[] piece : this.pieces) {
            for (int i = 0; i < 8; i++) {
                if (piece[i] instanceof King && piece[i].getColor() == color) {
                    return (King) piece[i];
                }
            }
        }
        return null;
    }
    public boolean isKingInCheck(PieceColor color)  {
       King allyKing = findKing(color);
        assert allyKing != null;
        return allyKing.inCheck(this);
    }

    public boolean isCheckMate( ChessGame chessGame)  {
        King allyKing = findKing(chessGame.getTurn());
        assert allyKing != null;
        return allyKing.isMate( this,chessGame);
    }

    public void addCapturedPiece (Piece capturedPiece) {
        if(capturedPiece != null && capturedPiece.getColor() == PieceColor.WHITE) {
            whiteCapturedPieces.add(capturedPiece);
        }

        if(capturedPiece != null && capturedPiece.getColor() == PieceColor.BLACK) {
           blackCapturedPieces.add(capturedPiece);
        }
    }

}
