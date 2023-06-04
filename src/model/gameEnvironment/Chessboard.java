package model.gameEnvironment;

import java.util.ArrayList;
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
    
    public void switchTurn(){
        turn = turn == false;
    }
    
    public boolean getTurn(){
        return this.turn;
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
    
    public Position enPassant(Pawn p){
        Position enPassant = null; 
        int row = p.getPosition().getRow();
        int col = p.getPosition().getCol();
        
        if(this.isValidPosition(row, col - 1)){
            if(this.getSquare(row,col - 1).getPiece().isPresent()){
                Piece pSx = this.getSquare(row, col - 1).getPiece().get();
                if(pSx != null && pSx instanceof Pawn && pSx.isEnemy(p) && ((Pawn)pSx).getEnPassant()){
                    enPassant = new Position(row, col - 1);
                }
            }
        }
        
        if(this.isValidPosition(row, col + 1)){
            if(this.getSquare(row,col + 1).getPiece().isPresent()){
                Piece pSx = this.getSquare(row, col + 1).getPiece().get();
                if(pSx != null && pSx instanceof Pawn && pSx.isEnemy(p) && ((Pawn)pSx).getEnPassant()){
                    enPassant = new Position(row, col + 1);
                }
            }
        }        
        
        return enPassant;
    }
    
    public boolean promotion(Pawn p){
        boolean ret = false;
        
        if((p.getColor() == 0 && p.getPosition().getRow() == Chessboard.ROW_UPPER_LIMIT) ||
           (p.getColor() == 1 && p.getPosition().getRow() == Chessboard.ROW_LOWER_LIMIT)){
            ret = true;
        }
        
        return ret;
    }
        
}
