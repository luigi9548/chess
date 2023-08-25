package model.gameEnvironment.impl;

import model.gameEnvironment.api.ChessboardInt;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.functionality.impl.ColorChessboard;
import model.functionality.impl.Position;
import model.pieces.impl.Bishop;
import model.pieces.impl.King;
import model.pieces.impl.Knight;
import model.pieces.impl.Pawn;
import model.pieces.impl.Piece;
import model.pieces.impl.Queen;
import model.pieces.impl.Rook;


public class Chessboard implements ChessboardInt {
    
    private Square[][] squares; 
    private int turn; // 1 = turno giocatore nero, 0 = turno giocatore bianco
    public static final int ROW_UPPER_LIMIT = 7;
    public static final int ROW_LOWER_LIMIT = 0;
    public static final int COL_UPPER_LIMIT = 7;
    public static final int COL_LOWER_LIMIT = 0;
    
    public Chessboard(){
        this.initializeChessboard();
        this.turn = 0; //si parte del bianco
    }
    
    // costruttore utilizzato per i test
    public Chessboard(Position p){
        this.squares = new Square[Chessboard.ROW_UPPER_LIMIT + 1][Chessboard.COL_UPPER_LIMIT + 1];
        for(int i = ROW_LOWER_LIMIT; i <= ROW_UPPER_LIMIT; i++){ 
            for(int j = COL_LOWER_LIMIT; j <= COL_UPPER_LIMIT; j++){
                squares[i][j] =  new Square(null);
            }
        }
    }
    
    private void initializeChessboard(){
        this.squares = new Square[Chessboard.ROW_UPPER_LIMIT + 1][Chessboard.COL_UPPER_LIMIT + 1];
        
        // prima riga bianca e nera
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){
            Position pW = new Position(Chessboard.ROW_LOWER_LIMIT, Chessboard.COL_LOWER_LIMIT + i);
            Position pB = new Position(Chessboard.ROW_UPPER_LIMIT, Chessboard.COL_LOWER_LIMIT + i);
            switch (i) {
                case 0, 7 -> {
                    // creo i 4 rook alle estremità della scacchiera
                    Rook roW = new Rook(pW,ColorChessboard.WHITE, this, 'r');
                    roW.setIcon(".\\src\\images\\whiteRook.png");
                    squares[pW.getRow()][pW.getCol()] = new Square(roW);
                    Rook roB = new Rook(pB,ColorChessboard.BLACK, this, 'R');
                    roB.setIcon(".\\src\\images\\blackRook.png");
                    squares[pB.getRow()][pB.getCol()] = new Square(roB);
                }
                case 1, 6 -> {
                    // creo i 4 knight
                    Knight knW = new Knight(pW,ColorChessboard.WHITE, this, 'h');
                    knW.setIcon(".\\src\\images\\whiteKnight.png");
                    squares[pW.getRow()][pW.getCol()] = new Square(knW);
                    Knight knB = new Knight(pB,ColorChessboard.BLACK, this, 'H');
                    knB.setIcon(".\\src\\images\\blackKnight.png");
                    squares[pB.getRow()][pB.getCol()] = new Square(knB);
                }
                case 2, 5 -> {
                    // creo i bishop
                    Bishop biW = new Bishop(pW,ColorChessboard.WHITE, this, 'b');
                    biW.setIcon(".\\src\\images\\whiteBishop.png");
                    squares[pW.getRow()][pW.getCol()] = new Square(biW);
                    Bishop biB = new Bishop(pB,ColorChessboard.BLACK, this, 'B');
                    biB.setIcon(".\\src\\images\\blackBishop.png");
                    squares[pB.getRow()][pB.getCol()] = new Square(biB);
                }
                case 4 -> {
                    // creo casella con queen
                    Queen quW = new Queen(pW,ColorChessboard.WHITE, this, 'q');
                    quW.setIcon(".\\src\\images\\whiteQueen.png");
                    squares[pW.getRow()][pW.getCol()] = new Square(quW);
                    Queen quB = new Queen(pB,ColorChessboard.BLACK, this, 'Q');
                    quB.setIcon(".\\src\\images\\blackQueen.png");
                    squares[pB.getRow()][pB.getCol()] = new Square(quB);
                }
                case 3 -> {
                    // creo casella con king
                    King kiW = new King(pW,ColorChessboard.WHITE, this, 'k');
                    kiW.setIcon(".\\src\\images\\whiteKing.png");
                    squares[pW.getRow()][pW.getCol()] = new Square(kiW);
                    King kiB = new King(pB,ColorChessboard.BLACK, this, 'K');
                    kiB.setIcon(".\\src\\images\\blackKing.png");
                    squares[pB.getRow()][pB.getCol()] = new Square(kiB);
                }
            }
        } // end for per creare prima e ultima row
        
        // for pedoni
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){ // <= perchè da 0 a 7 considero le 8 colonne
            Position pW = new Position(Chessboard.ROW_LOWER_LIMIT + 1,Chessboard.COL_LOWER_LIMIT + i);
            Position pB = new Position(Chessboard.ROW_UPPER_LIMIT - 1,Chessboard.COL_LOWER_LIMIT + i);
            Pawn paW    = new Pawn(pW,ColorChessboard.WHITE,this, 'p');
            paW.setIcon(".\\src\\images\\whitePawn.png");
            squares[pW.getRow()][pW.getCol()] = new Square(paW);
            
            
            Pawn paB    = new Pawn(pB,ColorChessboard.BLACK,this, 'P');
            paB.setIcon(".\\src\\images\\blackPawn.png");
            squares[pB.getRow()][pB.getCol()] = new Square(paB);
        }
        
        // rimanenti caselle vuote
        for(int i = ROW_LOWER_LIMIT + 2; i <= ROW_UPPER_LIMIT - 2; i++){ // il 2 sta per le righe già fatte
            for(int j = COL_LOWER_LIMIT; j <= COL_UPPER_LIMIT; j++){
                squares[i][j] =  new Square(null);
            }
        }
    }
    
    
    @Override
    public Square getSquare(int row, int col){
        return this.squares[row][col];
    }
    
    @Override
    public void switchTurn(){
        if(this.turn == 0)
            this.turn = 1;
        else
            this.turn = 0;
    }
    
    public int getTurn(){
        return this.turn;
    }
    
    public ArrayList<Piece> getPiecesByColor(ColorChessboard color){
        ArrayList<Piece> pieces = new ArrayList<>();

        Arrays.stream(squares)
                .flatMap(Arrays::stream)
                .filter(square -> square.getPiece().isPresent())
                .map(square -> square.getPiece().get())
                .filter(piece -> piece.getColor() == color)
                .forEach(pieces::add);

        return pieces;
    }
    
    @Override
    public boolean isValidPosition(int row, int col){
        return row >= Chessboard.ROW_LOWER_LIMIT && row <= Chessboard.ROW_UPPER_LIMIT
                && col >= Chessboard.COL_LOWER_LIMIT && col <=Chessboard.ROW_UPPER_LIMIT;
    }
    
    // ritorna la posizione della torre con cui può fare arrocco altrimenti null    
    @Override
    public Position canCastling(ColorChessboard color) {
        ArrayList<Piece> opposingPieces;
        opposingPieces = getPiecesByColor((color == ColorChessboard.WHITE) ? ColorChessboard.BLACK : ColorChessboard.WHITE);

        int kingRow = (color == ColorChessboard.WHITE) ? 0 : 7;
        int kingCol = 3;

        Position castling = null;

        try {
            Piece king = this.getSquare(kingRow, kingCol).getPiece().orElse(null);
            Piece rook1 = this.getSquare(kingRow, 0).getPiece().orElse(null);
            Piece rook2 = this.getSquare(kingRow, 7).getPiece().orElse(null);

            if (king instanceof King && king.getColor() == color) {
                if (this.getSquare(kingRow, 2).getPiece().isEmpty() &&
                    this.getSquare(kingRow, 1).getPiece().isEmpty() &&
                    this.getSquare(kingRow, 0).getPiece().isPresent() &&
                    rook1 instanceof Rook && rook1.getColor() == color &&
                    opposingPieces.stream().noneMatch(p -> attachedPosition(p, new Position(kingRow, 3)))) {

                    castling = new Position(kingRow, 0);
                } else if (this.getSquare(kingRow, 4).getPiece().isEmpty() &&
                           this.getSquare(kingRow, 5).getPiece().isEmpty() &&
                           this.getSquare(kingRow, 6).getPiece().isEmpty() &&
                           this.getSquare(kingRow, 7).getPiece().isPresent() &&
                           rook2 instanceof Rook && rook2.getColor() == color &&
                           opposingPieces.stream().noneMatch(p -> attachedPosition(p, new Position(kingRow, 3)))) {

                    castling = new Position(kingRow, 7);
                }
            }
        } catch (NoSuchElementException e) {}

        return castling;
    }
    
    // ottimizzato
    private boolean attachedPosition(Piece piece, Position p) {
        ArrayList<Position> movements = piece.calculateMovement();
        return movements.stream().anyMatch(pos -> pos.equals(p));
    }
            
    // versione ottimizzata
    @Override
    public Position enPassant(Pawn p) {
        Position currentPosition = p.getPosition();
        int row = currentPosition.getRow();
        int col = currentPosition.getCol();

        Position leftPosition = new Position(row, col - 1);
        Position rightPosition = new Position(row, col + 1);

        List<Position> adjacentPositions = Stream.of(leftPosition, rightPosition)
                .filter(pos -> isValidPosition(pos.getRow(), pos.getCol()))
                .collect(Collectors.toList());

        Position enPassant = adjacentPositions.stream()
                .map(pos -> this.getSquare(pos.getRow(), pos.getCol()))
                .filter(square -> square.getPiece().isPresent())
                .map(square -> square.getPiece().get())
                .filter(piece -> piece instanceof Pawn && piece.isEnemy(p) && ((Pawn) piece).getEnPassant())
                .map(Piece::getPosition)
                .findFirst()
                .orElse(null);

        return enPassant;
    }
    
    // ottimizzato
    @Override
    public boolean promotion(Pawn p) {
    int row = p.getPosition().getRow();
    return (p.getColor() == ColorChessboard.WHITE && row == Chessboard.ROW_UPPER_LIMIT) ||
           (p.getColor() == ColorChessboard.BLACK && row == Chessboard.ROW_LOWER_LIMIT);
    }
    
    private Position kingPosition(ColorChessboard color){
        for(int i = 0; i <= Chessboard.ROW_UPPER_LIMIT; i++){
            for(int j = 0; j <= Chessboard.COL_UPPER_LIMIT; j++){
                if(this.squares[i][j].getPiece().isPresent() && this.squares[i][j].getPiece().get().getColor() == color && this.squares[i][j].getPiece().get() instanceof King)
                    return this.squares[i][j].getPiece().get().getPosition();
            }
        }
        return null;
    }
    
    // ottimizzato
    @Override
    public boolean isCheck(ColorChessboard color) {
        Position king = kingPosition(color);
        ArrayList<Piece> avvPieces;
        avvPieces = getPiecesByColor((color == ColorChessboard.WHITE) ? ColorChessboard.BLACK : ColorChessboard.WHITE);

        return avvPieces.stream().anyMatch(piece -> attachedPosition(piece, king));
    }
       
    @Override
    public ArrayList<Position> legalMovements(Piece p){
        ArrayList<Position> possiblePositions = p.calculateMovement();
        Piece piece;
        ArrayList<Position> legalPositions = new ArrayList();
        Position firstPosition = p.getPosition();
        for(Position position : possiblePositions){
            piece = null;
            if(this.getSquare(position.getRow(), position.getCol()).getPiece().isPresent()){
                piece = this.getSquare(position.getRow(), position.getCol()).getPiece().get();
            }
            
            this.getSquare(position.getRow(), position.getCol()).setPiece(p);
            p.setPosition(position);
            this.getSquare(firstPosition.getRow(), firstPosition.getCol()).setPiece(null);
            if(!this.isCheck(p.getColor())){
                legalPositions.add(position);
            }
            this.getSquare(firstPosition.getRow(), firstPosition.getCol()).setPiece(p);
            p.setPosition(firstPosition);
            if(piece != null)
                this.getSquare(position.getRow(), position.getCol()).setPiece(piece);
            else
                this.getSquare(position.getRow(), position.getCol()).setPiece(null);
        }
        return legalPositions;
    }

    
    // metodo per verificare se è patta o scacco matto (Scacco matto = 0, patta = 1, niente = 2;   
    // versione ottimizzata dal metodo
    @Override
    public int isCheckmateOrFlap(ColorChessboard color) {
        ArrayList<Piece> myPieces = getPiecesByColor(color);
        ArrayList<Piece> avvPieces;
        avvPieces = getPiecesByColor((color == ColorChessboard.WHITE) ? ColorChessboard.BLACK : ColorChessboard.WHITE);

        boolean checkmate = myPieces.stream().allMatch(p -> this.legalMovements(p).isEmpty());

        boolean attachedKing = avvPieces.stream().anyMatch(p -> this.attachedPosition(p, this.kingPosition(color)));

        if (!checkmate) {
            return 2;
        } else if (attachedKing) {
            return 0;
        } else {
            return 1;
        }
    }

}
