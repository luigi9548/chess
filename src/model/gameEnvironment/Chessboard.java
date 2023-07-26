package model.gameEnvironment;

import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.functionality.Position;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.pieces.Queen;
import model.pieces.Rook;


public class Chessboard {
    
    private Square[][] squares; 
    private boolean turn;
    public static final int ROW_UPPER_LIMIT = 7;
    public static final int ROW_LOWER_LIMIT = 0;
    public static final int COL_UPPER_LIMIT = 7;
    public static final int COL_LOWER_LIMIT = 0;
    
    public Chessboard(){
        this.initializeChessboard();
        this.turn = false; //si parte del bianco
    }
    
    private void initializeChessboard(){
        this.squares = new Square[Chessboard.ROW_UPPER_LIMIT + 1][Chessboard.COL_UPPER_LIMIT + 1];
        
        // prima riga bianca e nera
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){
            Position pW = new Position(Chessboard.ROW_LOWER_LIMIT, Chessboard.COL_LOWER_LIMIT + i);
            Position pB = new Position(Chessboard.ROW_UPPER_LIMIT, Chessboard.COL_LOWER_LIMIT + i);
            if( i == 0 || i == 7){ // creo i 4 rook alle estremità della scacchiera
                Rook roW = new Rook("ROOK_" + ((i==0)? 1 : 2), pW,0, this, 'r');
                roW.setIcon(".\\src\\images\\whiteRook.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, roW);
                
                Rook roB = new Rook("ROOK_" + ((i==0)? 1 : 2), pB,1, this, 'R');
                roB.setIcon(".\\src\\images\\blackRook.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, roB);
            }else if(i == 1 || i == 6){ // creo i 4 knight
                Knight knW = new Knight("KNIGHT_" + ((i==1)? 1 : 2), pW,0, this, 'h');
                knW.setIcon(".\\src\\images\\whiteKnight.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, knW);
                
                Knight knB = new Knight("KNIGHT_" + ((i==1)? 1 : 2), pB,1, this, 'H');
                knB.setIcon(".\\src\\images\\blackKnight.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, knB);     
            }else if(i == 2 || i == 5){ // creo i bishop 
                Bishop biW = new Bishop("BISHOP_" + ((i==2)? 1 : 2), pW,0, this, 'b');
                biW.setIcon(".\\src\\images\\whiteBishop.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, biW);
                
                Bishop biB = new Bishop("BISHOP_" + ((i==2)? 1 : 2), pB,1, this, 'B');
                biB.setIcon(".\\src\\images\\blackBishop.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, biB);     
            }else if(i == 4){ // creo casella con queen
                Queen quW = new Queen("QUEEN_WHITE", pW,0, this, 'q');
                quW.setIcon(".\\src\\images\\whiteQueen.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, quW);
                
                Queen quB = new Queen("QUEEN_BLACK", pB,1, this, 'Q');
                quB.setIcon(".\\src\\images\\blackQueen.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, quB);   
            }else if( i == 3){ // creo casella con king
                King kiW = new King("KING_WHITE", pW,0, this, 'k');
                kiW.setIcon(".\\src\\images\\whiteKing.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, kiW);
                
                King kiB = new King("KING_BLACK", pB,1, this, 'K');
                kiB.setIcon(".\\src\\images\\blackKing.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, kiB); 
            }
        } // end for per creare prima e ultima row
        
        // for pedoni
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){ // <= perchè da 0 a 7 considero le 8 colonne
            Position pW = new Position(Chessboard.ROW_LOWER_LIMIT + 1,Chessboard.COL_LOWER_LIMIT + i);
            Position pB = new Position(Chessboard.ROW_UPPER_LIMIT - 1,Chessboard.COL_LOWER_LIMIT + i);
            Pawn paW    = new Pawn("PAWN_"+(i+1),pW,0,this, 'p');
            paW.setIcon(".\\src\\images\\whitePawn.png");
            squares[pW.getRow()][pW.getCol()] = new Square(pW, paW);
            
            
            Pawn paB    = new Pawn("PAWN_"+(i+1),pB,1,this, 'P');
            paB.setIcon(".\\src\\images\\blackPawn.png");
            squares[pB.getRow()][pB.getCol()] = new Square(pB,paB);
        }
        
        // rimanenti caselle vuote
        for(int i = ROW_LOWER_LIMIT + 2; i <= ROW_UPPER_LIMIT - 2; i++){ // il 2 sta per le righe già fatte
            for(int j = COL_LOWER_LIMIT; j <= COL_UPPER_LIMIT; j++){
                squares[i][j] =  new Square(new Position(i,j), null);
            }
        }
    }
    
    
    public Square getSquare(int row, int col){
        return this.squares[row][col];
    }
    
    public void switchTurn(){
        turn = turn == false;
    }
    
    public boolean getTurn(){
        return this.turn;
    }
    
    public ArrayList<Piece> getWPieces() {
        return this.getPiecesByColor(0);
    }
    
    public ArrayList<Piece> getBPieces() {
        return this.getPiecesByColor(1);
    }
    
    public ArrayList<Piece> getPiecesByColor(int color){
        ArrayList<Piece> pieces = new ArrayList<>();

        Arrays.stream(squares)
                .flatMap(Arrays::stream)
                .filter(square -> square.getPiece().isPresent())
                .map(square -> square.getPiece().get())
                .filter(piece -> piece.getColor() == color)
                .forEach(pieces::add);

        return pieces;
    }
    
    public boolean isValidPosition(int row, int col){
        return row >= Chessboard.ROW_LOWER_LIMIT && row <= Chessboard.ROW_UPPER_LIMIT
                && col >= Chessboard.COL_LOWER_LIMIT && col <=Chessboard.ROW_UPPER_LIMIT;
    }
    
    // ritorna la posizione della torre con cui può fare arrocco altrimenti null    
    public Position canCastling(int color) {
        ArrayList<Piece> opposingPieces = getPiecesByColor((color == 0) ? 1 : 0);

        int kingRow = (color == 0) ? 0 : 7;
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
        ArrayList<Position> movements = piece.calculateMovement(null);
        return movements.stream().anyMatch(pos -> pos.compare(p));
    }
        
    // versione ottimizzata
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
    public boolean promotion(Pawn p) {
    int row = p.getPosition().getRow();
    return (p.getColor() == 0 && row == Chessboard.ROW_UPPER_LIMIT) ||
           (p.getColor() == 1 && row == Chessboard.ROW_LOWER_LIMIT);
    }
    
    private Position kingPosition(int color){
        for(int i = 0; i <= Chessboard.ROW_UPPER_LIMIT; i++){
            for(int j = 0; j <= Chessboard.COL_UPPER_LIMIT; j++){
                if(this.squares[i][j].getPiece().isPresent() && this.squares[i][j].getPiece().get().getColor() == color && this.squares[i][j].getPiece().get() instanceof King)
                    return this.squares[i][j].getPiece().get().getPosition();
            }
        }
        return null;
    }
    
    // ottimizzato
    public boolean isCheck(int color) {
        Position king = kingPosition(color);
        //ArrayList<Piece> avvPieces = color == 0 ? getBPieces() : getWPieces();
        ArrayList<Piece> avvPieces = getPiecesByColor(color == 0 ? 1 : 0);

        return avvPieces.stream().anyMatch(piece -> attachedPosition(piece, king));
    }
       
    public ArrayList<Position> legalMovements(Piece p){
        ArrayList<Position> possiblePositions = p.calculateMovement(null);
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
    public int isCheckmateOrFlap(int color) {
        ArrayList<Piece> myPieces = getPiecesByColor(color);
        ArrayList<Piece> avvPieces = getPiecesByColor((color == 0) ? 1 : 0);

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
