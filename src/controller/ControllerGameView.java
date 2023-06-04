/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import model.pieces.Pawn;
import model.pieces.Piece;
import view.GameView;
import view.Promotion;
/**
 *
 * @author luigi
 */
public class ControllerGameView {
    private GameView gameView;
    private Chessboard chessboard = new Chessboard();
    private Promotion promotion;
    
    public ControllerGameView(GameView gameView){
        this.gameView = gameView;
    }
    
    public void actions(java.awt.event.ActionEvent evt, int color){
        if(color == -1317 || color == -6530040) //bianco e nero
            this.showMovement(evt);
        else if(color == -65536) //rosso
            this.move(evt);
        
    }
    private void showMovement(java.awt.event.ActionEvent evt) { 
        int turnInt = chessboard.getTurn() ? 1 : 0; //converto boolean in intero (metodologia provvisoria)
        
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                // controllo che la casella non sia vuota (se non faccio così da errore)
                if(chessboard.getSquare(row, col).getPiece().isPresent()){
                    Piece p = chessboard.getSquare(row, col).getPiece().get();
                    // per visualizzare la mossa del pezzo deve essere il turno giusto
                    if(gameView.getButtonGrid(row, col) == evt.getSource() && p.getColor() == turnInt){
                       gameView.resetColors();
                       this.searchPiece();
                       p.setInAction(true);
                       //System.out.println(row + " " + col);
                       this.changeBottonColor(p.calculateMovement(null));
                    }
                }
            }
        }
    } 
    
    @SuppressWarnings("empty-statement")
    private void move(java.awt.event.ActionEvent evt){
        //System.out.println("turn: " + turn);
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(gameView.getButtonGrid(row, col) == evt.getSource()){
                    Piece p = this.searchPiece();
                    int turnInt = chessboard.getTurn() ? 1 : 0; //converto boolean in intero (metodologia provvisoria)
                  
                    if(p != null && p.getColor() == turnInt){
                        gameView.resetColors();
                        Icon icon = new ImageIcon(chessboard.getSquare(p.getPosition().getRow(), p.getPosition().getCol()).getPiece().get().getIcon());
                        gameView.getButtonGrid(row, col).setIcon(icon);
                        gameView.getButtonGrid(p.getPosition().getRow(), p.getPosition().getCol()).setIcon(null);
                        chessboard.getSquare(row, col).setPiece(p);
                        chessboard.getSquare(p.getPosition().getRow(), p.getPosition().getCol()).setPiece(null);
                        //verifico se è avvenuto enPassant
                        // devo verificarlo prima di cambiare la posizione di p 
                        if(p instanceof Pawn){
                            Position pos = chessboard.enPassant((Pawn) p);
                            if(pos != null && pos.getCol() == col){
                                gameView.getButtonGrid(pos.getRow(), pos.getCol()).setIcon(null);
                                //System.out.println(pos.getRow() + " " + pos.getCol());
                            }
                        }
                        chessboard.getSquare(row, col).getPiece().get().setPosition(new Position(row,col));
                        
                        // dopo un turno devo impostare enPassant a false perché è così la regola
                        if(turnInt == 0)
                            changeEnPassant(chessboard.getWPieces());
                        else
                            changeEnPassant(chessboard.getBPieces());
                        
                        // se abbiamo pedone devo controllare se era la sua prima mossa, 
                        // in caso positivo devo mettere che ha già mosso (quindi da adesso in poi si muove di 1 casella) 
                        // e metto che può avvenire enPassant (solo se si è mosso di 2 caselle)
                        if(p instanceof Pawn pawn){
                            if (pawn.isFirstMove()){
                                pawn.switchFirstMove();
                                if((pawn.getColor() == 0 && pawn.getPosition().getRow() == 3) ||
                                   (pawn.getColor() == 1 && pawn.getPosition().getRow() == 4) ){
                                    // questo if è perché durante la prima mossa si deve muovere di 2
                                    pawn.setEnPassant(true);
                                }
                            }
                            
                            // controllo se il pedone è arrivato a fine scacchiera
                            if(chessboard.promotion(pawn)){
                                promotion = new Promotion(pawn, gameView, chessboard);
                                promotion.setVisible(true);
                            }
                        }
                        
                        // cambio turno
                        chessboard.switchTurn();
                    }
                }
            }
        }
    }
    
    private Piece searchPiece(){
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(chessboard.getSquare(row, col).getPiece().isPresent()){
                    if(chessboard.getSquare(row, col).getPiece().get().isInAction()== true){
                        chessboard.getSquare(row, col).getPiece().get().setInAction(false);
                        return chessboard.getSquare(row, col).getPiece().get();
                    }
                }
            }
        }
        return null;
    }
    private void changeBottonColor(ArrayList<Position> positions){
        for(Position p : positions){
            System.out.println(p.getRow() + " " + p.getCol());
            gameView.getButtonGrid(p.getRow(), p.getCol()).setBackground(Color.red);
        }
        System.out.println("");
    }
    
    private void changeEnPassant(ArrayList<Piece> p){
        for(Piece pa : p){
            if(pa instanceof Pawn pawn)
                pawn.setEnPassant(false);
        }
    }

    
    
}
