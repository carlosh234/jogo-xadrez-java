package main.chessgame;

import main.exceptions.EmptyPositionException;
import main.exceptions.InvalidMovementException;
import main.exceptions.WrongTurnException;
import main.utils.Message;
import main.utils.MsgColor;
import main.board.Board;
import main.pieces.Bishop;
import main.pieces.King;
import main.pieces.Knight;
import main.pieces.Pawn;
import main.pieces.Piece;
import main.pieces.Queen;
import main.pieces.Rook;
import main.pieces.enums.PieceColor;
import main.utils.Convert;

import java.util.InputMismatchException;
import java.util.Scanner;


public class ChessGame {
    private final Board board;
    private PieceColor turn;

    public ChessGame() {
        this.board = new Board(8,8);
        this.turn = PieceColor.WHITE;
        setPieces();
    }

    public Board getBoard() {
        return board;
    }

    public PieceColor getTurn() {
        return turn;
    }

    public void setTurn(PieceColor turn) {
        this.turn = turn;
    }

    public void setPieces() {
        // WHITE PIECES

        Piece whitePawn1 = new Pawn(0, 1, PieceColor.WHITE);
        Piece whitePawn2 = new Pawn(1, 1, PieceColor.WHITE);
        Piece whitePawn3 = new Pawn(2, 1, PieceColor.WHITE);
        Piece whitePawn4 = new Pawn(3, 1, PieceColor.WHITE);
        Piece whitePawn5 = new Pawn(4, 1, PieceColor.WHITE);
        Piece whitePawn6 = new Pawn(5, 1, PieceColor.WHITE);
        Piece whitePawn7 = new Pawn(6, 1, PieceColor.WHITE);
        Piece whitePawn8 = new Pawn(7, 1, PieceColor.WHITE);

        board.setPieceOnBoard(whitePawn1);
        board.setPieceOnBoard(whitePawn2);
        board.setPieceOnBoard(whitePawn3);
        board.setPieceOnBoard(whitePawn4);
        board.setPieceOnBoard(whitePawn5);
        board.setPieceOnBoard(whitePawn6);
        board.setPieceOnBoard(whitePawn7);
        board.setPieceOnBoard(whitePawn8);

        Piece whiteKnight1 = new Knight(1, 0, PieceColor.WHITE);
        Piece whiteKnight2 = new Knight(6, 0, PieceColor.WHITE);

        board.setPieceOnBoard(whiteKnight1);
        board.setPieceOnBoard(whiteKnight2);

        Piece whiteRook1 = new Rook(0, 0, PieceColor.WHITE);
        Piece whiteRook2 = new Rook(7, 0, PieceColor.WHITE);

        board.setPieceOnBoard(whiteRook1);
        board.setPieceOnBoard(whiteRook2);

        Piece whiteBishop1 = new Bishop(2, 0, PieceColor.WHITE);
        Piece whiteBishop2 = new Bishop(5, 0, PieceColor.WHITE);

        board.setPieceOnBoard(whiteBishop1);
        board.setPieceOnBoard(whiteBishop2);

        Piece whiteQueen = new Queen(3, 0, PieceColor.WHITE);
        board.setPieceOnBoard(whiteQueen);

        Piece whiteKing = new King(4, 0, PieceColor.WHITE);
        board.setPieceOnBoard(whiteKing);

        // BLACK PIECES

        Piece blackPawn1 = new Pawn(0, 6, PieceColor.BLACK);
        Piece blackPawn2 = new Pawn(1, 6, PieceColor.BLACK);
        Piece blackPawn3 = new Pawn(2, 6, PieceColor.BLACK);
        Piece blackPawn4 = new Pawn(3, 6, PieceColor.BLACK);
        Piece blackPawn5 = new Pawn(4, 6, PieceColor.BLACK);
        Piece blackPawn6 = new Pawn(5, 6, PieceColor.BLACK);
        Piece blackPawn7 = new Pawn(6, 6, PieceColor.BLACK);
        Piece blackPawn8 = new Pawn(7, 6, PieceColor.BLACK);

        board.setPieceOnBoard(blackPawn1);
        board.setPieceOnBoard(blackPawn2);
        board.setPieceOnBoard(blackPawn3);
        board.setPieceOnBoard(blackPawn4);
        board.setPieceOnBoard(blackPawn5);
        board.setPieceOnBoard(blackPawn6);
        board.setPieceOnBoard(blackPawn7);
        board.setPieceOnBoard(blackPawn8);

        Piece blackKnight1 = new Knight(1, 7, PieceColor.BLACK);
        Piece blackKnight2 = new Knight(6, 7, PieceColor.BLACK);

        board.setPieceOnBoard(blackKnight1);
        board.setPieceOnBoard(blackKnight2);

        Piece blackRook1 = new Rook(0, 7, PieceColor.BLACK);
        Piece blackRook2 = new Rook(7, 7, PieceColor.BLACK);

        board.setPieceOnBoard(blackRook1);
        board.setPieceOnBoard(blackRook2);

        Piece blackBishop1 = new Bishop(2, 7, PieceColor.BLACK);
        Piece blackBishop2 = new Bishop(5, 7, PieceColor.BLACK);

        board.setPieceOnBoard(blackBishop1);
        board.setPieceOnBoard(blackBishop2);

        Piece blackQueen = new Queen(3, 7, PieceColor.BLACK);
        board.setPieceOnBoard(blackQueen);

        Piece blackKing = new King(4, 7, PieceColor.BLACK);
        board.setPieceOnBoard(blackKing);
    }

    public void turnMessageV1() {
        if (this.turn.equals(PieceColor.WHITE)) {
            Message.println("AS BRANCAS JOGAM", MsgColor.GREEN);
        } else {
            Message.println("AS PRETAS JOGAM", MsgColor.GREEN);
        }
    }

    public void turnMessageV2() {
        if (this.turn.equals(PieceColor.WHITE)) {
            System.out.println("AS BRANCAS JOGAM");
        } else {
            System.out.println("AS PRETAS JOGAM");
        }
    }

    public void changeTurn(Piece piece) {
        if (piece.getColor().equals(PieceColor.WHITE)) {
            setTurn(PieceColor.BLACK);
        } else {
            setTurn(PieceColor.WHITE);
        }
    }

    public void startGameV1()  {
        Scanner input = new Scanner(System.in);
        String currentPosition, nextPosition;
        PieceColor turn = this.getTurn();
        Board board = this.getBoard();
        board.printBoardV1();
        this.turnMessageV1();

        do {
            try{
                Message.println("Escolha a posição da peça que deseja mover : ", MsgColor.YELLOW);

                currentPosition = input.next();

                Message.println("Agora escolha a posição que deseja mover a peça: ", MsgColor.YELLOW);

                nextPosition = input.next();

                int currentX =  (Convert.coordinateToInt(currentPosition) - Convert.coordinateToInt(currentPosition) % 10) / 10 - 1;
                int currentY = Convert.coordinateToInt(currentPosition) % 10 - 1;
                int nextX = (Convert.coordinateToInt(nextPosition) - Convert.coordinateToInt(nextPosition) % 10) / 10 - 1;
                int nextY = Convert.coordinateToInt(nextPosition) % 10 - 1;
                board.movePiece(currentX, currentY, nextX, nextY, this);
            } catch (InputMismatchException | NumberFormatException | StringIndexOutOfBoundsException e1 ) {
                Message.println("Oops você digitou algo errado!",MsgColor.RED);
            } catch (ArrayIndexOutOfBoundsException e2) {
                Message.println("A casa que você digitou não existe no tabuleiro!",MsgColor.RED);
            } catch (InvalidMovementException | EmptyPositionException | WrongTurnException e3) {
                Message.println(e3.getMessage(),MsgColor.RED);
            }

            if(this.getBoard().isKingInCheck(turn)) {
                Message.println("CHECK",MsgColor.RED);
            }

            if(this.getBoard().isCheckMate(this)) {
                Message.println("CHECKMATE!",MsgColor.RED);
                this.getBoard().printBoardV1();
                if (this.getTurn() == PieceColor.WHITE) {
                    Message.println("Vitória das pretas!", MsgColor.GREEN);
                }else {
                    Message.println("Vitória das brancas!", MsgColor.GREEN);
                }
                break;
            }
            board.printBoardV1();
            this.turnMessageV1();
            System.out.print("\u000C");
        } while (true);
        input.close();
    }

    public void startGameV2() {
        Scanner input = new Scanner(System.in);
        String currentPosition, nextPosition;
        PieceColor turn = this.getTurn();
        Board board = this.getBoard();
        board.printBoardV2();
        this.turnMessageV2();

        do {
            try{
                System.out.println("Escolha a posição da peça que deseja mover : ");

                currentPosition = input.next();

                System.out.println("Agora escolha a posição que deseja mover a peça: ");

                nextPosition = input.next();

                int currentX =  (Convert.coordinateToInt(currentPosition) - Convert.coordinateToInt(currentPosition) % 10) / 10 - 1;
                int currentY = Convert.coordinateToInt(currentPosition) % 10 - 1;
                int nextX = (Convert.coordinateToInt(nextPosition) - Convert.coordinateToInt(nextPosition) % 10) / 10 - 1;
                int nextY = Convert.coordinateToInt(nextPosition) % 10 - 1;

                board.movePiece(currentX, currentY, nextX, nextY, this);
            } catch (InputMismatchException | NumberFormatException | StringIndexOutOfBoundsException e1 ) {
                System.out.println("Oops você digitou algo errado!");
            } catch (ArrayIndexOutOfBoundsException e2) {
                System.out.println("A casa que você digitou não existe no tabuleiro!");
            } catch (InvalidMovementException | EmptyPositionException | WrongTurnException e3) {
                System.out.println(e3.getMessage());
            }

            if(this.getBoard().isKingInCheck(turn)) {
                System.out.println("CHECK");
            }

            if(this.getBoard().isCheckMate(this)) {
                System.out.println("CHECKMATE!");
                this.getBoard().printBoardV2();
                if (this.getTurn() == PieceColor.WHITE) {
                    System.out.println("Vitória das pretas!");
                }else {
                    System.out.println("Vitória das brancas!");
                }
                break;
            }
            board.printBoardV2();
            this.turnMessageV2();
            System.out.println("===================================================================================");
        } while (true);
        input.close();
    }

}
