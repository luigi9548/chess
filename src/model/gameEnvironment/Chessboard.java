package model.gameEnvironment;

import model.functionality.Position;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
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
        this.squares = new Square[Chessboard.ROW_UPPER_LIMIT][Chessboard.COL_UPPER_LIMIT];
          
        // prima riga bianca e nera
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){
            Position pW = new Position(Chessboard.ROW_LOWER_LIMIT, Chessboard.COL_LOWER_LIMIT + i);
            Position pB = new Position(Chessboard.ROW_UPPER_LIMIT, Chessboard.COL_LOWER_LIMIT + i);
            if( i == 0 || i == 7){ // creo i 4 rook alle estremità della scacchiera
                Rook roW = new Rook("ROOK_" + ((i==0)? 1 : 2), pW,0, this);
                squares[pW.getRow()][pW.getCol()] = new Square(pW, roW);
                
                Rook roB = new Rook("ROOK_" + ((i==0)? 1 : 2), pW,1, this);
                squares[pB.getRow()][pB.getCol()] = new Square(pB, roB);
            }else if(i == 1 || i == 6){ // creo i 4 knight
                Knight knW = new Knight("KNIGHT_" + ((i==1)? 1 : 2), pW,0, this);
                squares[pW.getRow()][pW.getCol()] = new Square(pW, knW);
                
                Knight knB = new Knight("KNIGHT_" + ((i==1)? 1 : 2), pW,1, this);
                squares[pB.getRow()][pB.getCol()] = new Square(pB, knB);     
            }else if(i == 2 || i == 5){ // creo i bishop 
                Bishop biW = new Bishop("BISHOP_" + ((i==2)? 1 : 2), pW,0, this);
                squares[pW.getRow()][pW.getCol()] = new Square(pW, biW);
                
                Bishop biB = new Bishop("BISHOP_" + ((i==2)? 1 : 2), pW,1, this);
                squares[pB.getRow()][pB.getCol()] = new Square(pB, biB);     
            }else if(i == 3){ // creo casella con queen
                Queen quW = new Queen("QUEEN_WHITE", pW,0, this);
                squares[pW.getRow()][pW.getCol()] = new Square(pW, quW);
                
                Queen quB = new Queen("QUEEN_BLACK", pW,1, this);
                squares[pB.getRow()][pB.getCol()] = new Square(pB, quB);   
            }else if( i== 4){ // creo casella con king
                King kiW = new King("KING_WHITE", pW,0, this);
                squares[pW.getRow()][pW.getCol()] = new Square(pW, kiW);
                
                King kiB = new King("KING_BLACK", pW,1, this);
                squares[pB.getRow()][pB.getCol()] = new Square(pB, kiB); 
            }
        } // end for per creare prima e ultima row
        
        // for pedoni
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){ // <= perchè da 0 a 7 considero le 8 colonne
            Position pW = new Position(Chessboard.ROW_LOWER_LIMIT + 1,Chessboard.COL_LOWER_LIMIT + i);
            Pawn paW    = new Pawn("PAWN_"+(i+1),pW,0,this);
            squares[pW.getRow()][pW.getCol()] = new Square(pW, paW);
            
            Position pB = new Position(Chessboard.ROW_UPPER_LIMIT - 1,Chessboard.COL_LOWER_LIMIT + i);
            Pawn paB    = new Pawn("PAWN_"+(i+1),pB,1,this);
            squares[pB.getRow()][pB.getCol()] = new Square(pW,paB);
        }
        
        // rimanenti caselle vuote
        for(int i = ROW_LOWER_LIMIT + 2; i < ROW_UPPER_LIMIT - 2; i++){ // il 2 sta per le righe già fatte
            for(int j = COL_LOWER_LIMIT; j < COL_UPPER_LIMIT; i++){
                squares[i][j] =  new Square(new Position(i,j), null);
            }
        }
    }
    
}
