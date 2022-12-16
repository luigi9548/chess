package model.gameEnvironment;

import model.functionality.Position;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Queen;
import model.pieces.Rook;


public class Chessboard {
    
    private Chessboard chessboard;
    private Square[][] squares;
    public static final int ROW_UPPER_LIMIT = 7;
    public static final int ROW_LOWER_LIMIT = 0;
    public static final int COL_UPPER_LIMIT = 7;
    public static final int COL_LOWER_LIMIT = 0;
    
    public Chessboard(){
        /*this.squares = new Square[ROW_UPPER_LIMIT][COL_UPPER_LIMIT];
        for(int i = ROW_LOWER_LIMIT; i < squares.length; i++){
            for(int j = COL_LOWER_LIMIT; j < squares[i].length; i++){
                squares[i][j] =  new Square(new Position(i,j), null);
            }
        }*/
        
        
    }
    
    private void initializeChessboard(){
        Square[][] squares = new Square[Chessboard.ROW_UPPER_LIMIT][Chessboard.COL_UPPER_LIMIT];
        
        
        // prima riga bianca e nera
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){
            Position pW = new Position(Chessboard.ROW_LOWER_LIMIT, Chessboard.COL_LOWER_LIMIT + i);
            Position pB = new Position(Chessboard.ROW_UPPER_LIMIT, Chessboard.COL_LOWER_LIMIT + i);
            if( i == 0 || i == 7){ // creo i 4 rook alle estremità della scacchiera
                Rook roW = new Rook("ROOK_" + ((i==1)? 1 : 2), pW,0, chessboard);
                squares[pW.getRow()][pW.getCol()] = new Square(pW, roW);
                
                Rook roB = new Rook("ROOK_" + ((i==1)? 1 : 2), pW,1, chessboard);
                squares[pB.getRow()][pB.getCol()] = new Square(pB, roB);
            }else if(i == 1 || i == 6){ // creo i 4 knight
                Knight knW = new Knight("KNIGHT_" + ((i==1)? 1 : 2), pW,0, chessboard);
                squares[pW.getRow()][pW.getCol()] = new Square(pW, knW);
                
                Knight knB = new Knight("KNIGHT_" + ((i==1)? 1 : 2), pW,1, chessboard);
                squares[pB.getRow()][pB.getCol()] = new Square(pB, knB);     
            }else if(i == 2 || i == 5){ // creo i bishop 
                Bishop biW = new Bishop("BISHOP_" + ((i==1)? 1 : 2), pW,0, chessboard);
                squares[pW.getRow()][pW.getCol()] = new Square(pW, biW);
                
                Bishop biB = new Bishop("BISHOP_" + ((i==1)? 1 : 2), pW,1, chessboard);
                squares[pB.getRow()][pB.getCol()] = new Square(pB, biB);     
            }else if(i == 3){ // creo casella con queen
                Queen quW = new Queen("QUEEN", pW,0, chessboard);
                squares[pW.getRow()][pW.getCol()] = new Square(pW, quW);
                
                Queen quB = new Queen("QUEEN", pW,1, chessboard);
                squares[pB.getRow()][pB.getCol()] = new Square(pB, quB);   
            }else if( i== 4){ // creo casella con king
                King kiW = new King("KING", pW,0, chessboard);
                squares[pW.getRow()][pW.getCol()] = new Square(pW, kiW);
                
                King kiB = new King("KING", pW,1, chessboard);
                squares[pB.getRow()][pB.getCol()] = new Square(pB, kiB); 
            }
        } // end for per creare prima e ultima row
        
        // for pedoni
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){ // <= perchè da 0 a 7 considero le 8 colonne
            Position pW = new Position(1,Chessboard.COL_LOWER_LIMIT + i);
            Pawn paW    = new Pawn("PAWN_"+(i+1),pW,0,chessboard);
            squares[pW.getRow()][pW.getCol()] = new Square(pW, paW);
            
            Position pB = new Position(6,Chessboard.COL_LOWER_LIMIT + i);
            Pawn paB    = new Pawn("PAWN_"+(i+1),pB,1,chessboard);
            squares[pB.getRow()][pB.getCol()] = new Square(pW,paB);
        }
        
        // rimanenti caselle vuote
        for(int i = ROW_LOWER_LIMIT + 2; i < (squares.length - 2); i++){ // il 2 sta per le righe già fatte, <= o <? boh
            for(int j = COL_LOWER_LIMIT; j < squares[i].length; i++){
                squares[i][j] =  new Square(new Position(i,j), null);
            }
        }
    }
    
}
