package controller.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.functionality.impl.ChessTimer;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Chessboard;
import model.gameEnvironment.impl.Player;
import model.pieces.impl.King;
import model.pieces.impl.Pawn;
import model.pieces.impl.Piece;
import model.pieces.impl.Rook;
import view.GameView;
import view.GameConclusion;
import view.Promotion;
import model.functionality.impl.ColorChessboard;

public class ControllerGameView {
    
    public static final int WHITE_SQUARE = -1317;
    public static final int BLACK_SQUARE = -6530040;
    public static final int GREEN_SQUARE = -16711936;
    public static final int RED_SQUARE = -65536;
    private final GameView gameView;
    private final Chessboard chessboard;
    private Promotion promotion;
    private GameConclusion gameConclusion;
    private final Player whiteP;
    private final Player blackP;
    private final ChessTimer timer;
    
    public ControllerGameView(final GameView gameView){
        this.chessboard = new Chessboard();
        this.timer = new ChessTimer(600 * 1000);
        this.startTimer();
        this.gameView = gameView;
        this.whiteP = new Player(ColorChessboard.WHITE);
        this.blackP = new Player(ColorChessboard.BLACK);
    }
    
    public Player getPlayer(ColorChessboard color){
        return (color == ColorChessboard.WHITE)? this.whiteP : blackP;
    }
  
    public Chessboard getChessboard(){
        return this.chessboard;
    }
    
    public void actions(java.awt.event.ActionEvent evt, int color){
        switch (color) {
            case WHITE_SQUARE, BLACK_SQUARE -> this.showMovement(evt);
            case RED_SQUARE -> 
                this.move(evt);
            case GREEN_SQUARE -> {
                Rook rook = (Rook) this.getEvtPiece(evt);
                if(rook != null)
                    this.castling(rook);
            }
        }       
    }
    
    private void showMovement(java.awt.event.ActionEvent evt) {
        Position castling;
        ArrayList<Position> legalMovements;

        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if (chessboard.getSquare(row, col).getPiece().isPresent()) {
                    Piece p = chessboard.getSquare(row, col).getPiece().get();
                    if (gameView.getButtonGrid(row, col) == evt.getSource() && p.getColor().ordinal() == chessboard.getTurn()) {
                        gameView.resetColors();
                        this.searchPiece();
                        legalMovements = this.chessboard.legalMovements(p);
                        if (!legalMovements.isEmpty()) {
                            p.setInAction(true);
                            this.changeBottonColor(legalMovements);
                        }
                        if (p instanceof King) {
                            castling = chessboard.canCastling(p.getColor());
                            if (castling != null)
                                gameView.getButtonGrid(castling.getRow(), castling.getCol()).setBackground(Color.GREEN);
                        }
                        return;
                    }
                }
            }
        }
    }

    // versione ottimizzata
    private void castling(Rook rook){
        Icon rookIcon = new ImageIcon(chessboard.getSquare(rook.getPosition().getRow(), rook.getPosition().getCol()).getPiece().get().getIcon());
        Icon kingIcon;
        int row;
        int newColKing;
        int newColRook;
        String castling;        
        
        if(rook.getColor() == ColorChessboard.WHITE){
           kingIcon = new ImageIcon(chessboard.getSquare(0, 3).getPiece().get().getIcon()); 
           castling = (rook.getPosition().equals(new Position(0,0))) ? "0 - 0" : "0 - 0 - 0";
           newColKing = (rook.getPosition().equals(new Position(0,0))) ? 1 : 5;
           newColRook = (rook.getPosition().equals(new Position(0,0))) ? 2 : 4;
           row = 0;
           this.whiteP.addToHistory(castling);
        }else{
            kingIcon = new ImageIcon(chessboard.getSquare(7, 3).getPiece().get().getIcon());
            castling = (rook.getPosition().equals(new Position(7,0))) ? "0 - 0" : "0 - 0 - 0";
            newColKing = (rook.getPosition().equals(new Position(7,0))) ? 1 : 5;
            newColRook = (rook.getPosition().equals(new Position(7,0))) ? 2 : 4;
            row = 7;
            this.blackP.addToHistory(castling);            
        }
        
        configureCastling(row, newColKing, newColRook, kingIcon, rookIcon);
        
        this.updateHistory();
        chessboard.switchTurn();
        timer.switchTurn();
        gameView.resetColors();
    }
    
    private void configureCastling(int row, int newColKing, int newColRook, Icon kingIcon, Icon rookIcon){                
        configurePosition(row, 3, row, newColKing, kingIcon);
        configurePosition(row, (newColRook == 2)? 0 : 7, row, newColRook, rookIcon);
    }

    private void move(java.awt.event.ActionEvent evt){
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(gameView.getButtonGrid(row, col) == evt.getSource()){
                    Piece p = this.searchPiece();
                  
                    if(p != null && p.getColor().ordinal() == chessboard.getTurn()){
                        // "pulisco" l'ambiente sistemando i colori della scacchiera e impostando gli enPassant a false nel nuovo turno
                        gameView.resetColors();
                        if(chessboard.getTurn() == 0)
                            changeEnPassant(chessboard.getPiecesByColor(ColorChessboard.WHITE));
                        else
                            changeEnPassant(chessboard.getPiecesByColor(ColorChessboard.BLACK));
                                                
                        // aggiornamento cronologia e cimitero
                        updateHistory(p, row, col);
                        
                        // imposto la posizione
                        configurePosition(p.getPosition().getRow(), p.getPosition().getCol(), 
                                        row, col, new ImageIcon(chessboard.getSquare(p.getPosition().getRow(), p.getPosition().getCol()).getPiece().get().getIcon()));
                        
                        // dopo aver cambiato posizione, devo controllare se la nuova posizione fa scacco al re
                        // prima non posso farlo
                        String checkString;
                        if(this.chessboard.isCheck(ColorChessboard.BLACK)){
                            checkString = this.whiteP.getHistory().get(this.whiteP.getHistory().size() - 1).concat("+");
                            this.whiteP.removeLastString();
                            this.whiteP.addToHistory(checkString);
                        }else if(this.chessboard.isCheck(ColorChessboard.WHITE)){
                            checkString = this.blackP.getHistory().get(this.blackP.getHistory().size() - 1).concat("+");
                            this.blackP.removeLastString();
                            this.blackP.addToHistory(checkString);
                        }
                         
                        // se abbiamo pedone devo controllare se era la sua prima mossa, 
                        // in caso positivo devo mettere che ha già mosso (quindi da adesso in poi si muove di 1 casella) 
                        // e metto che può avvenire enPassant (solo se si è mosso di 2 caselle)
                        if(p instanceof Pawn pawn){
                            if (pawn.isFirstMove()){
                                pawn.switchFirstMove();
                                if((pawn.getColor() == ColorChessboard.WHITE && pawn.getPosition().getRow() == 3) ||
                                   (pawn.getColor() == ColorChessboard.BLACK && pawn.getPosition().getRow() == 4) ){
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
                        this.updateHistory();
                        chessboard.switchTurn();
                        timer.switchTurn();
                    }
                    
                    if(p.getColor() == ColorChessboard.WHITE)
                        handleVictoryOrDraw(ColorChessboard.BLACK);
                    else
                        handleVictoryOrDraw(ColorChessboard.WHITE);
                    
                }
            }
        }
    }
    
    private void handleVictoryOrDraw(ColorChessboard color) {
        int checkmateOrFlapResult = this.chessboard.isCheckmateOrFlap(color);
        String message;

        if (checkmateOrFlapResult == 0) {
            message = (color == ColorChessboard.WHITE) ? "Il giocatore nero ha vinto per Scacco Matto" :
                                                         "Il giocatore bianco ha vinto per Scacco Matto";
        } else if (checkmateOrFlapResult == 1) {
            message = "Patta";
        } else {
            return; // Nessun risultato significativo
        }

        this.timer.stopTimer();
        gameConclusion = new GameConclusion(message, gameView);
        gameConclusion.setVisible(true);        
    }

    private void configurePosition(int row, int col, int newRow, int newCol, Icon icon){
        // scambio icona
        gameView.getButtonGrid(newRow, newCol).setIcon(icon);
        gameView.getButtonGrid(row, col).setIcon(null);       
        // imposto pezzo e posizione
        chessboard.getSquare(newRow, newCol).setPiece(chessboard.getSquare(row, col).getPiece().get());
        chessboard.getSquare(newRow, newCol).getPiece().get().setPosition(new Position(newRow, newCol));
        chessboard.getSquare(row, col).setPiece(null);
    }
    
    private void updateHistory(Piece p, int row, int col){
        Position pos = null;
        boolean hasEaten = false;
        String history;
        
        // prima controllo enPassant
        if(p instanceof Pawn pawn)
            pos = chessboard.enPassant(pawn);
        
        if(pos != null && pos.getCol() == col){
            hasEaten = true;
            if(p.getColor() == ColorChessboard.WHITE)
                history = p.getPosition().numToLetterBySubstr() + "x" + new Position(row,col).getStringPosition() + " e. p.";
            else
                history = p.getPosition().numToLetterBySubstr() + "x" + pos.getStringPosition() + " e. p."; 
        }else{
            if(chessboard.getSquare(row, col).getPiece().isPresent())
                hasEaten = true;
                
            history = this.moveString(p.getPosition(), new Position(row, col), hasEaten, p.getPieceSign());
            
        }
        
        if(p.getColor() == ColorChessboard.WHITE){
            this.whiteP.addToHistory(history);
            if(hasEaten){
                if(pos != null && pos.getCol() == col){
                    this.blackP.addPieceCemetery(this.chessboard.getSquare(pos.getRow(), pos.getCol()).getPiece().get()); 
                    gameView.getButtonGrid(pos.getRow(), pos.getCol()).setIcon(null);
                    chessboard.getSquare(pos.getPosition().getRow(), pos.getPosition().getCol()).setPiece(null);
                }else
                    this.blackP.addPieceCemetery(this.chessboard.getSquare(row, col).getPiece().get());
                    
                this.updateCemetery(blackP);
            }
        }else{
            this.blackP.addToHistory(history);
            if(hasEaten){
                if(pos != null && pos.getCol() == col){
                    this.whiteP.addPieceCemetery(this.chessboard.getSquare(pos.getRow(), pos.getCol()).getPiece().get());
                    gameView.getButtonGrid(pos.getRow(), pos.getCol()).setIcon(null);
                    chessboard.getSquare(pos.getPosition().getRow(), pos.getPosition().getCol()).setPiece(null);
                }else
                    this.whiteP.addPieceCemetery(this.chessboard.getSquare(row, col).getPiece().get());
                   
                this.updateCemetery(whiteP);  
            }
        }
    }
    
    private Piece getEvtPiece(java.awt.event.ActionEvent evt){
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(gameView.getButtonGrid(row, col) == evt.getSource()){
                    return chessboard.getSquare(row, col).getPiece().get();
                }
            }
        }
        return null;
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
            gameView.getButtonGrid(p.getRow(), p.getCol()).setBackground(Color.red);
        }
    }
    
    private void changeEnPassant(ArrayList<Piece> p){
        for(Piece pa : p){
            if(pa instanceof Pawn pawn)
                pawn.setEnPassant(false);
        }
    }
    
    private void updateCemetery(Player p){
        ArrayList<Piece> pCemetery = p.getCemetery();
        String str=new String();
        for (int i = 0; i < pCemetery.size(); i++) {
            str+=pCemetery.get(i).pieceSwap();
        }
        if(p.getColor() == ColorChessboard.WHITE)
            gameView.getjLabelCemeteryWhite().setText(str);
        else
            gameView.getjLabelCemeteryBlack().setText(str);
    }
    
    private String moveString(Position initial, Position finalP, boolean hasEaten, char type){
        String move;
        char typeUpper = Character.toUpperCase(type);
        if(typeUpper == 'P'){
            if(hasEaten)
                move = initial.getStringPosition() + "x" + finalP.getStringPosition();
            else
                move = initial.getStringPosition() + " - " + finalP.getStringPosition();
        }else{
            if(hasEaten)
                move = typeUpper + initial.getStringPosition() + "x" + finalP.getStringPosition();
            else
                move = typeUpper + initial.getStringPosition() + " - " + finalP.getStringPosition();
        }
        return move;
    }
    
    public void updateHistory() {
        this.gameView.getHistory().setText("");
        this.gameView.getHistory().append("                Bianco \t\tNero");
        int it = 0;
        while (true){
            this.gameView.getHistory().append("\n"+it+". ");
            if(it < whiteP.getHistory().size())
                this.gameView.getHistory().append(whiteP.getHistory().get(it)+"\t\t     ");
            else 
                this.gameView.getHistory().append("        "+"\t");
            
            if(it < blackP.getHistory().size())
                this.gameView.getHistory().append(blackP.getHistory().get(it));
            else 
                this.gameView.getHistory().append("        ");
            
            if(it >= whiteP.getHistory().size() && it >= whiteP.getHistory().size()){
                break;
            }
            it++;
        }
    }
    
    private void startTimer() {
        this.timer.getTimer().scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            if (timer.getIsPlayer1Turn()) {
                    timer.setPlayer1RemainingTime(timer.getPlayer1RemainingTime() - 1000); //viene sottratto un secondo dal totale
                    if (timer.getPlayer1RemainingTime() < 0) {
                        //se il tempo termina allora viene invocato il metodo che termina il timer
                        timer.stopTimer();
                        gameConclusion = new GameConclusion("Il giocatore nero vince per fine del tempo", gameView);
                        gameConclusion.setVisible(true);
                    } else {
                        //aggiornamento del timer
                        gameView.getTimerPlayerW().setText(timer.formatTime(timer.getPlayer1RemainingTime()));
                    }
                } else {
                    timer.setPlayer2RemainingTime(timer.getPlayer2RemainingTime() - 1000); // Subtract one second
                    if (timer.getPlayer2RemainingTime() < 0) {
                        timer.stopTimer();
                        gameConclusion = new GameConclusion("Il giocatore bianco vince vinto per fine del tempo", gameView);
                        gameConclusion.setVisible(true);
                    } else {
                        gameView.getTimerPlayerB().setText(timer.formatTime(timer.getPlayer2RemainingTime()));
                    }
                }
            }
        }, 1000, 1000);
    }
}
