package model.gameEnvironment;

import java.util.ArrayList;
import java.util.NoSuchElementException;
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
    public static final int ROW_UPPER_LIMIT = 7;
    public static final int ROW_LOWER_LIMIT = 0;
    public static final int COL_UPPER_LIMIT = 7;
    public static final int COL_LOWER_LIMIT = 0;
    
    public Chessboard(){
        this.initializeChessboard();     
    }
    
    private void initializeChessboard(){
        this.squares = new Square[Chessboard.ROW_UPPER_LIMIT + 1][Chessboard.COL_UPPER_LIMIT + 1];
        
        // prima riga bianca e nera
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){
            Position pW = new Position(Chessboard.ROW_LOWER_LIMIT, Chessboard.COL_LOWER_LIMIT + i);
            Position pB = new Position(Chessboard.ROW_UPPER_LIMIT, Chessboard.COL_LOWER_LIMIT + i);
            if( i == 0 || i == 7){ // creo i 4 rook alle estremità della scacchiera
                Rook roW = new Rook("ROOK_" + ((i==0)? 1 : 2), pW,0, this);
                roW.setIcon(".\\src\\images\\whiteRook.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, roW);
                
                Rook roB = new Rook("ROOK_" + ((i==0)? 1 : 2), pB,1, this);
                roB.setIcon(".\\src\\images\\blackRook.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, roB);
            }else if(i == 1 || i == 6){ // creo i 4 knight
                Knight knW = new Knight("KNIGHT_" + ((i==1)? 1 : 2), pW,0, this);
                knW.setIcon(".\\src\\images\\whiteKnight.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, knW);
                
                Knight knB = new Knight("KNIGHT_" + ((i==1)? 1 : 2), pB,1, this);
                knB.setIcon(".\\src\\images\\blackKnight.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, knB);     
            }else if(i == 2 || i == 5){ // creo i bishop 
                Bishop biW = new Bishop("BISHOP_" + ((i==2)? 1 : 2), pW,0, this);
                biW.setIcon(".\\src\\images\\whiteBishop.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, biW);
                
                Bishop biB = new Bishop("BISHOP_" + ((i==2)? 1 : 2), pB,1, this);
                biB.setIcon(".\\src\\images\\blackBishop.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, biB);     
            }else if(i == 4){ // creo casella con queen
                Queen quW = new Queen("QUEEN_WHITE", pW,0, this);
                quW.setIcon(".\\src\\images\\whiteQueen.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, quW);
                
                Queen quB = new Queen("QUEEN_BLACK", pB,1, this);
                quB.setIcon(".\\src\\images\\blackQueen.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, quB);   
            }else if( i == 3){ // creo casella con king
                King kiW = new King("KING_WHITE", pW,0, this);
                kiW.setIcon(".\\src\\images\\whiteKing.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, kiW);
                
                King kiB = new King("KING_BLACK", pB,1, this);
                kiB.setIcon(".\\src\\images\\blackKing.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, kiB); 
            }
        } // end for per creare prima e ultima row
        
        // for pedoni
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){ // <= perchè da 0 a 7 considero le 8 colonne
            Position pW = new Position(Chessboard.ROW_LOWER_LIMIT + 1,Chessboard.COL_LOWER_LIMIT + i);
            Position pB = new Position(Chessboard.ROW_UPPER_LIMIT - 1,Chessboard.COL_LOWER_LIMIT + i);
            Pawn paW    = new Pawn("PAWN_"+(i+1),pW,0,this);
            paW.setIcon(".\\src\\images\\whitePawn.png");
            squares[pW.getRow()][pW.getCol()] = new Square(pW, paW);
            
            
            Pawn paB    = new Pawn("PAWN_"+(i+1),pB,1,this);
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
    
    public ArrayList<Piece> getWPieces(){
        ArrayList<Piece> wPieces = new ArrayList<>();
        
        for(int i = 0; i <= Chessboard.ROW_UPPER_LIMIT; i++){
            for(int j = 0; j <= Chessboard.COL_UPPER_LIMIT; j++){
                if(this.squares[i][j].getPiece().isPresent() && this.squares[i][j].getPiece().get().getColor() == 0)
                {
                    wPieces.add(this.squares[i][j].getPiece().get());
                }
            }
        }
        return wPieces;
    }
    
    public ArrayList<Piece> getBPieces(){
        ArrayList<Piece> bPieces = new ArrayList<>();
        
        for(int i = 0; i <= Chessboard.ROW_UPPER_LIMIT; i++){
            for(int j = 0; j <= Chessboard.COL_UPPER_LIMIT; j++){
                if(this.squares[i][j].getPiece().isPresent() && this.squares[i][j].getPiece().get().getColor() == 1)
                {
                    bPieces.add(this.squares[i][j].getPiece().get());
                }
            }
        }
        return bPieces;
    }
    
    public boolean isValidPosition(int row, int col){
        return row >= Chessboard.ROW_LOWER_LIMIT && row <= Chessboard.ROW_UPPER_LIMIT
                && col >= Chessboard.COL_LOWER_LIMIT && col <=Chessboard.ROW_UPPER_LIMIT;
    }
    
    // ritorna la posizione della torre con cui può fare arrocco altrimenti null
    public Position canCastling(int colour){
        ArrayList<Piece> opposingPieces;
        Piece king, rook1, rook2;
        Position castling = null;
        boolean canCastling = true;
        if(colour == 0){
            opposingPieces = this.getBPieces();
            try{
                king = this.getSquare(0,3).getPiece().get();
                rook1 = this.getSquare(0, 0).getPiece().get();
                rook2 = this.getSquare(0, 7).getPiece().get();
                if(king instanceof King && king.getColor() == 0){
                    if(this.getSquare(0, 2).getPiece().isEmpty() && this.getSquare(0, 1).getPiece().isEmpty()){
                        if(rook1 instanceof Rook && rook1.getColor() == 0){
                            for(Piece p : opposingPieces){
                                if(this.attachedPosition(p, new Position(0,3)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(0,2)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(0,1)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(0,0)))
                                    canCastling = false;
                            }
                            if(canCastling)
                                castling = new Position(0,0);
                        }
                    }else if(this.getSquare(0, 4).getPiece().isEmpty() && this.getSquare(0, 5).getPiece().isEmpty() && this.getSquare(0, 6).getPiece().isEmpty()){
                        if(rook2 instanceof Rook && rook2.getColor() == 0){
                            for(Piece p : opposingPieces){
                                if(this.attachedPosition(p, new Position(0,3)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(0,4)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(0,5)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(0,6)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(0,7)))
                                    canCastling = false;
                            }
                            if(canCastling)
                                castling = new Position(0,7);
                        }
                    }
                }
            }catch(NoSuchElementException e){}
            return castling;
        }else{
            opposingPieces = this.getWPieces();
            try{
                king = this.getSquare(7,3).getPiece().get();
                rook1 = this.getSquare(7, 0).getPiece().get();
                rook2 = this.getSquare(7, 7).getPiece().get();
                if(king instanceof King && king.getColor() == 1){
                    if(this.getSquare(7, 2).getPiece().isEmpty() && this.getSquare(7, 1).getPiece().isEmpty()){
                        if(rook1 instanceof Rook && rook1.getColor() == 1){
                            for(Piece p : opposingPieces){
                                if(this.attachedPosition(p, new Position(7,3)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(7,2)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(7,1)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(7,0)))
                                    canCastling = false;
                            }
                            if(canCastling)
                                castling = new Position(7,0);
                        }
                    }else if(this.getSquare(7, 4).getPiece().isEmpty() && this.getSquare(7, 5).getPiece().isEmpty() && this.getSquare(7, 6).getPiece().isEmpty()){
                        if(rook2 instanceof Rook && rook2.getColor() == 1){
                            for(Piece p : opposingPieces){
                                if(this.attachedPosition(p, new Position(7,3)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(7,4)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(7,5)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(7,6)))
                                    canCastling = false;
                                if(this.attachedPosition(p, new Position(7,7)))
                                    canCastling = false;
                            }
                            if(canCastling)
                                castling = new Position(7,7);
                        }
                    }
                }
            }catch(NoSuchElementException e){}
        }
        return castling;
    }
    
    private boolean attachedPosition(Piece piece, Position p){
        ArrayList<Position> movements = piece.calculateMovement(null);
        for(Position pos : movements){
            if(pos.compare(p)){
                return true;
            }
        }
        return false;
    } 
}
