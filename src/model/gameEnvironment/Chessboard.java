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
                Rook roW = new Rook(pW,0, this, 'r');
                roW.setIcon(".\\src\\images\\whiteRook.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, roW);
                
                Rook roB = new Rook(pB,1, this, 'R');
                roB.setIcon(".\\src\\images\\blackRook.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, roB);
            }else if(i == 1 || i == 6){ // creo i 4 knight
                Knight knW = new Knight(pW,0, this, 'h');
                knW.setIcon(".\\src\\images\\whiteKnight.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, knW);
                
                Knight knB = new Knight(pB,1, this, 'H');
                knB.setIcon(".\\src\\images\\blackKnight.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, knB);     
            }else if(i == 2 || i == 5){ // creo i bishop 
                Bishop biW = new Bishop(pW,0, this, 'b');
                biW.setIcon(".\\src\\images\\whiteBishop.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, biW);
                
                Bishop biB = new Bishop(pB,1, this, 'B');
                biB.setIcon(".\\src\\images\\blackBishop.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, biB);     
            }else if(i == 4){ // creo casella con queen
                Queen quW = new Queen(pW,0, this, 'q');
                quW.setIcon(".\\src\\images\\whiteQueen.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, quW);
                
                Queen quB = new Queen(pB,1, this, 'Q');
                quB.setIcon(".\\src\\images\\blackQueen.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, quB);   
            }else if( i == 3){ // creo casella con king
                King kiW = new King(pW,0, this, 'k');
                kiW.setIcon(".\\src\\images\\whiteKing.png");
                squares[pW.getRow()][pW.getCol()] = new Square(pW, kiW);
                
                King kiB = new King(pB,1, this, 'K');
                kiB.setIcon(".\\src\\images\\blackKing.png");
                squares[pB.getRow()][pB.getCol()] = new Square(pB, kiB); 
            }
        } // end for per creare prima e ultima row
        
        // for pedoni
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){ // <= perchè da 0 a 7 considero le 8 colonne
            Position pW = new Position(Chessboard.ROW_LOWER_LIMIT + 1,Chessboard.COL_LOWER_LIMIT + i);
            Position pB = new Position(Chessboard.ROW_UPPER_LIMIT - 1,Chessboard.COL_LOWER_LIMIT + i);
            Pawn paW    = new Pawn(pW,0,this, 'p');
            paW.setIcon(".\\src\\images\\whitePawn.png");
            squares[pW.getRow()][pW.getCol()] = new Square(pW, paW);
            
            
            Pawn paB    = new Pawn(pB,1,this, 'P');
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
        ArrayList<Position> movements = piece.calculateMovement();
        for(Position pos : movements){
            if(pos.compare(p)){
                return true;
            }
        }
        return false;
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
    
    private Position kingPosition(int color){
        for(int i = 0; i <= Chessboard.ROW_UPPER_LIMIT; i++){
            for(int j = 0; j <= Chessboard.COL_UPPER_LIMIT; j++){
                if(this.squares[i][j].getPiece().isPresent() && this.squares[i][j].getPiece().get().getColor() == color && this.squares[i][j].getPiece().get() instanceof King)
                    return this.squares[i][j].getPiece().get().getPosition();
            }
        }
        return null;
    }
    
    public boolean isCheck(int color){
        boolean check = false;
        ArrayList<Piece> avvPieces;
        Position king;
        if(color == 0){
            avvPieces = this.getBPieces();
            king = this.kingPosition(0);
        }else{
            avvPieces = this.getWPieces();
            king = this.kingPosition(1);
        }
        for(Piece piece: avvPieces){
            if(this.attachedPosition(piece, king))
                check = true;
        }
        return check;
    }
       
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
    
    // metodo per verificare se è patta o scacco matto (Scacco matto = 0, patta = 1, ninente = 2;
    public int isCheckmateOrFlap(int color){
        ArrayList<Piece> myPieces;
        ArrayList<Piece> avvPieces;
        boolean checkmate = true;
        boolean attachedKing = false;
        if(color == 0){
            myPieces = this.getWPieces();
            avvPieces = this.getBPieces();
        }
        else{
            myPieces = this.getBPieces();
            avvPieces = this.getWPieces();
        }
        for(Piece p : myPieces){
            if(this.legalMovements(p).size() != 0){
                checkmate = false;
            }
        }
        for(Piece p : avvPieces){
            if(this.attachedPosition(p, this.kingPosition(color)))
                attachedKing = true;
        }
        if(!checkmate)
            return 2;
        else if(checkmate && attachedKing)
            return 0;
        else return 1;
    }
}
