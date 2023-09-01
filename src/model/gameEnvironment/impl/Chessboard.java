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
    private int turn; // 0 represents the white player's turn and 1 represents the black player's turn.
    public static final int ROW_UPPER_LIMIT = 7;
    public static final int ROW_LOWER_LIMIT = 0;
    public static final int COL_UPPER_LIMIT = 7;
    public static final int COL_LOWER_LIMIT = 0;
    
    public Chessboard(){
        this.initializeChessboard();
        this.turn = 0; // Start with the white player's turn    
    }
    
    // Constructor used for testing purposes.
    public Chessboard(Position p){
        // Create an empty chessboard with the appropriate dimensions.
        this.squares = new Square[Chessboard.ROW_UPPER_LIMIT + 1][Chessboard.COL_UPPER_LIMIT + 1];
        for(int i = ROW_LOWER_LIMIT; i <= ROW_UPPER_LIMIT; i++){ 
            for(int j = COL_LOWER_LIMIT; j <= COL_UPPER_LIMIT; j++){
                squares[i][j] =  new Square(null);
            }
        }
    }
    
    /**
     * Initializes the chessboard by placing chess pieces in their starting positions.
     */
    private void initializeChessboard(){
        this.squares = new Square[Chessboard.ROW_UPPER_LIMIT + 1][Chessboard.COL_UPPER_LIMIT + 1];
        
        // Place chess pieces in the first and last rows
        for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){
            Position pW = new Position(Chessboard.ROW_LOWER_LIMIT, Chessboard.COL_LOWER_LIMIT + i);
            Position pB = new Position(Chessboard.ROW_UPPER_LIMIT, Chessboard.COL_LOWER_LIMIT + i);
            switch (i) {
                case 0, 7 -> {
                    // Create rooks at the corners of the chessboard.
                    Rook roW = new Rook(pW,ColorChessboard.WHITE, this, 'r');
                    roW.setIcon(".\\src\\images\\whiteRook.png");
                    squares[pW.getRow()][pW.getCol()] = new Square(roW);
                    
                    Rook roB = new Rook(pB,ColorChessboard.BLACK, this, 'R');
                    roB.setIcon(".\\src\\images\\blackRook.png");
                    squares[pB.getRow()][pB.getCol()] = new Square(roB);
                }
                case 1, 6 -> {
                    // Create knights on the second and second-to-last columns.
                    Knight knW = new Knight(pW,ColorChessboard.WHITE, this, 'h');
                    knW.setIcon(".\\src\\images\\whiteKnight.png");
                    squares[pW.getRow()][pW.getCol()] = new Square(knW);
                    
                    Knight knB = new Knight(pB,ColorChessboard.BLACK, this, 'H');
                    knB.setIcon(".\\src\\images\\blackKnight.png");
                    squares[pB.getRow()][pB.getCol()] = new Square(knB);
                }
                case 2, 5 -> {
                    // Create bishops on the third and third-to-last columns.
                    Bishop biW = new Bishop(pW,ColorChessboard.WHITE, this, 'b');
                    biW.setIcon(".\\src\\images\\whiteBishop.png");
                    
                    squares[pW.getRow()][pW.getCol()] = new Square(biW);
                    Bishop biB = new Bishop(pB,ColorChessboard.BLACK, this, 'B');
                    biB.setIcon(".\\src\\images\\blackBishop.png");
                    squares[pB.getRow()][pB.getCol()] = new Square(biB);
                }
                case 4 -> {
                    // Create queens in the center.
                    Queen quW = new Queen(pW,ColorChessboard.WHITE, this, 'q');
                    quW.setIcon(".\\src\\images\\whiteQueen.png");
                    squares[pW.getRow()][pW.getCol()] = new Square(quW);
                    
                    Queen quB = new Queen(pB,ColorChessboard.BLACK, this, 'Q');
                    quB.setIcon(".\\src\\images\\blackQueen.png");
                    squares[pB.getRow()][pB.getCol()] = new Square(quB);
                }
                case 3 -> {
                    // Create kings.
                    King kiW = new King(pW,ColorChessboard.WHITE, this, 'k');
                    kiW.setIcon(".\\src\\images\\whiteKing.png");
                    
                    squares[pW.getRow()][pW.getCol()] = new Square(kiW);
                    King kiB = new King(pB,ColorChessboard.BLACK, this, 'K');
                    kiB.setIcon(".\\src\\images\\blackKing.png");
                    squares[pB.getRow()][pB.getCol()] = new Square(kiB);
                }
            }
        } 
        
            // Place pawns in the second and second-to-last rows.
            for(int i = 0; i <= Chessboard.COL_UPPER_LIMIT; i++){ 
                Position pW = new Position(Chessboard.ROW_LOWER_LIMIT + 1,Chessboard.COL_LOWER_LIMIT + i);
                Position pB = new Position(Chessboard.ROW_UPPER_LIMIT - 1,Chessboard.COL_LOWER_LIMIT + i);
                
                Pawn paW    = new Pawn(pW,ColorChessboard.WHITE,this, 'p');
                paW.setIcon(".\\src\\images\\whitePawn.png");
                squares[pW.getRow()][pW.getCol()] = new Square(paW);

                Pawn paB    = new Pawn(pB,ColorChessboard.BLACK,this, 'P');
                paB.setIcon(".\\src\\images\\blackPawn.png");
                squares[pB.getRow()][pB.getCol()] = new Square(paB);
        }
        
        // Fill the remaining squares with null (no pieces).
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
        this.turn = (this.turn == 0)? 1 : 0;
    }
    
    public int getTurn(){
        return this.turn;
    }
    
    /**
    * Retrieves all pieces on the chessboard that match the specified color.
    *
    * @param color The color of pieces to retrieve.
    * @return An ArrayList containing all pieces of the specified color.
    */
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
    public boolean isValidPosition(Position p){
        return p.getRow() >= Chessboard.ROW_LOWER_LIMIT && p.getRow() <= Chessboard.ROW_UPPER_LIMIT
                && p.getCol() >= Chessboard.COL_LOWER_LIMIT && p.getCol() <=Chessboard.ROW_UPPER_LIMIT;
    }
    
    @Override
    public Position canCastling(ColorChessboard color) {
        // Create a list to store opposing pieces.
        ArrayList<Piece> opposingPieces;
        opposingPieces = getPiecesByColor((color == ColorChessboard.WHITE) ? ColorChessboard.BLACK : ColorChessboard.WHITE);

        // Initialize king's row and column.
        int kingRow = (color == ColorChessboard.WHITE) ? 0 : 7;
        int kingCol = 3;

        Position castling = null;

        try {
            Piece king = this.getSquare(kingRow, kingCol).getPiece().orElse(null);
            Piece rook1 = this.getSquare(kingRow, 0).getPiece().orElse(null);
            Piece rook2 = this.getSquare(kingRow, 7).getPiece().orElse(null);
            
            boolean isSafe = true;
            if (king instanceof King && king.getColor() == color) {
                // Check if the squares between the king and rook1 are empty and safe.
                for (int col = 0; col <= 3; col++) {
                    final int colToCheck = col;
                    if (opposingPieces.stream().anyMatch(p -> attachedPosition(p, new Position(kingRow, colToCheck)))) {
                        isSafe = false;
                        break;
                    }
                }
                // If the squares are empty, and the rook1 is present, and the king is safe, set castling position to rook1.
                if (this.getSquare(kingRow, 2).getPiece().isEmpty() &&
                    this.getSquare(kingRow, 1).getPiece().isEmpty() &&
                    this.getSquare(kingRow, 0).getPiece().isPresent() &&
                    rook1 instanceof Rook && rook1.getColor() == color &&
                    isSafe){

                    castling = new Position(kingRow, 0);
                }else{
                    // reset the flag
                    isSafe = true;
                    
                    // Check if the squares between the king and rook2 are empty and safe.
                    for (int col = 3; col <= 7; col++) {
                        final int colToCheck = col;
                        if (opposingPieces.stream().anyMatch(p -> attachedPosition(p, new Position(kingRow, colToCheck)))) {
                            isSafe = false;
                            break;
                        }
                    }
                    // If the squares are empty, and the rook2 is present, and the king is safe, set castling position to rook2.
                    if (this.getSquare(kingRow, 4).getPiece().isEmpty() &&
                        this.getSquare(kingRow, 5).getPiece().isEmpty() &&
                        this.getSquare(kingRow, 6).getPiece().isEmpty() &&
                        this.getSquare(kingRow, 7).getPiece().isPresent() &&
                        rook2 instanceof Rook && rook2.getColor() == color &&
                        isSafe) {

                        castling = new Position(kingRow, 7);
                    }
                }
            }
        } catch (NoSuchElementException e) {}

        return castling;
    }
    
    @Override
    public boolean configurePawn(Pawn pawn, int row, int col){
        boolean isEnPassant = false;
        
        // Handle en passat
        Position pos = this.enPassant(pawn);
        if(pos != null && pos.getCol() == col)
            isEnPassant = true;
        
        // Handle first move and en passant flag
        if (pawn.isFirstMove()){
            pawn.switchFirstMove();
            if((pawn.getColor() == ColorChessboard.WHITE && row == 3) ||
               (pawn.getColor() == ColorChessboard.BLACK && row == 4) ){
                pawn.setEnPassant(true);
            }
        }
        
        return isEnPassant;
    }
    
    /**
    * Checks if a given piece is attached to a specific position on the chessboard.
    *
    * @param piece The piece to check for attachment.
    * @param p     The position to check if the piece is attached to.
    * @return      True if the piece is attached to the given position, otherwise false.
    */
    private boolean attachedPosition(Piece piece, Position p) {
        ArrayList<Position> movements = piece.calculateMovement();
        return movements.stream().anyMatch(pos -> pos.equals(p));
    }
            
    @Override
    public Position enPassant(Pawn p) {
        Position currentPosition = p.getPosition();
        int row = currentPosition.getRow();
        int col = currentPosition.getCol();

        Position leftPosition = new Position(row, col - 1);
        Position rightPosition = new Position(row, col + 1);

         // Create a list of adjacent positions filtered for valid positions
         List<Position> adjacentPositions = Stream.of(leftPosition, rightPosition)
                .filter(pos -> isValidPosition(pos))
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
    
    @Override
    public boolean promotion(Pawn p) {
    int row = p.getPosition().getRow();
    return (p.getColor() == ColorChessboard.WHITE && row == Chessboard.ROW_UPPER_LIMIT) ||
           (p.getColor() == ColorChessboard.BLACK && row == Chessboard.ROW_LOWER_LIMIT);
    }
    
    /**
    * Retrieves the position of the king of the specified color on the chessboard.
    *
    * @param color The color of the king to locate.
    * @return The position of the king if found, or null if not present.
    */
    private Position kingPosition(ColorChessboard color){
        for(int i = 0; i <= Chessboard.ROW_UPPER_LIMIT; i++){
            for(int j = 0; j <= Chessboard.COL_UPPER_LIMIT; j++){
                if(this.squares[i][j].getPiece().isPresent() && this.squares[i][j].getPiece().get().getColor() == color && this.squares[i][j].getPiece().get() instanceof King)
                    return this.squares[i][j].getPiece().get().getPosition();
            }
        }
        return null;
    }
   
    @Override
    public boolean isCheck(ColorChessboard color) {
        // Get the position of the player's king
        Position king = kingPosition(color);
        
        // Get the pieces of the opposing player
        ArrayList<Piece> avvPieces;
        avvPieces = getPiecesByColor((color == ColorChessboard.WHITE) ? ColorChessboard.BLACK : ColorChessboard.WHITE);

        // Check if any of the opposing player's pieces can attack the king
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
            
            // Temporarily move the piece to the new position for validation
            this.getSquare(position.getRow(), position.getCol()).setPiece(p);
            p.setPosition(position);
            this.getSquare(firstPosition.getRow(), firstPosition.getCol()).setPiece(null);
            
            // Check if the move results in a check for the current player
            if(!this.isCheck(p.getColor())){
                legalPositions.add(position);
            }
            
            // Reset the board to its original state after checking
            this.getSquare(firstPosition.getRow(), firstPosition.getCol()).setPiece(p);
            p.setPosition(firstPosition);
            
            // Restore any captured piece, if applicable
            if(piece != null)
                this.getSquare(position.getRow(), position.getCol()).setPiece(piece);
            else
                this.getSquare(position.getRow(), position.getCol()).setPiece(null);
        }
        return legalPositions;
    }

    @Override
    public int isCheckmateOrFlap(ColorChessboard color) {
        ArrayList<Piece> myPieces = getPiecesByColor(color);
        ArrayList<Piece> avvPieces;
        avvPieces = getPiecesByColor((color == ColorChessboard.WHITE) ? ColorChessboard.BLACK : ColorChessboard.WHITE);

        // Check if the current player is in checkmate (no legal moves for any piece)
        boolean checkmate = myPieces.stream().allMatch(p -> this.legalMovements(p).isEmpty());

        // Check if the opposing player's pieces can attack the current player's king
        boolean attachedKing = avvPieces.stream().anyMatch(p -> this.attachedPosition(p, this.kingPosition(color)));

        if (!checkmate) {
            return 2; // Neither checkmate nor stalemate
        } else if (attachedKing) {
            return 0; // Checkmate
        } else {
            return 1; // Stalemate
        }
    }
    
    /**
    * Updates the position of a piece from its current location to a new location.
    *
    * @param row    The current row of the piece.
    * @param col    The current column of the piece.
    * @param newRow The new row where the piece will be placed.
    * @param newCol The new column where the piece will be placed.
    */
    public void updatePosition(int row, int col, int newRow, int newCol){     
        this.getSquare(newRow, newCol).setPiece(this.getSquare(row, col).getPiece().get());
        this.getSquare(newRow, newCol).getPiece().get().setPosition(new Position(newRow, newCol));
        this.getSquare(row, col).setPiece(null);
    }
    
    /**
    * Changes the en passant state of all pawns of a given color to false.
    *
    * @param color The color of the pawns whose en passant state will be changed.
    */
    public void changeEnPassant(ColorChessboard color){
        ArrayList<Piece> p = this.getPiecesByColor(color);
        for(Piece pa : p){
            if(pa instanceof Pawn pawn)
                pawn.setEnPassant(false);
        }
    }

}