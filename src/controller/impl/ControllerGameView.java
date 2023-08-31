package controller.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.functionality.impl.ChessTimer;
import model.functionality.impl.Position;
import model.pieces.impl.King;
import model.pieces.impl.Pawn;
import model.pieces.impl.Piece;
import model.pieces.impl.Rook;
import view.GameView;
import view.GameConclusion;
import view.Promotion;
import model.functionality.impl.ColorChessboard;
import model.gameEnvironment.impl.Chessboard;
import model.gameEnvironment.impl.Match;
import model.gameEnvironment.impl.Player;

public class ControllerGameView {
    
    public static final int WHITE_SQUARE = -1317;
    public static final int BLACK_SQUARE = -6530040;
    public static final int GREEN_SQUARE = -16711936;
    public static final int RED_SQUARE = -65536;
    private final GameView gameView;
    private final Match match;
    private Promotion promotion;
    private GameConclusion gameConclusion;
        
    public ControllerGameView(final GameView gameView){
        this.match = new Match(new Chessboard(),new Player(ColorChessboard.WHITE),new Player(ColorChessboard.BLACK), new ChessTimer(600 * 1000));
        this.startTimer();
        this.gameView = gameView;
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
    
    public GameView getGameView(){
        return this.gameView;
    }
    
    public Match getMatch(){
        return this.match;
    }
    
    private void showMovement(java.awt.event.ActionEvent evt) {
        Position castling;
        ArrayList<Position> legalMovements;

        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if (this.match.getChessboard().getSquare(row, col).getPiece().isPresent()) {
                    Piece p = this.match.getChessboard().getSquare(row, col).getPiece().get();
                    if (gameView.getButtonGrid(row, col) == evt.getSource() && p.getColor().ordinal() == this.match.getChessboard().getTurn()) {
                        gameView.resetColors();
                        this.searchPiece();
                        legalMovements = this.match.getChessboard().legalMovements(p);
                        if (!legalMovements.isEmpty()) {
                            p.setInAction(true);
                            this.gameView.changeBottonColor(legalMovements);
                        }
                        if (p instanceof King) {
                            castling = this.match.getChessboard().canCastling(p.getColor());
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
        Icon rookIcon = new ImageIcon(this.match.getChessboard().getSquare(rook.getPosition().getRow(), rook.getPosition().getCol()).getPiece().get().getIcon());
        Icon kingIcon;
        int row;
        int newColKing;
        int newColRook;
        String castling;        
        
        if(rook.getColor() == ColorChessboard.WHITE){
           kingIcon = new ImageIcon(this.match.getChessboard().getSquare(0, 3).getPiece().get().getIcon()); 
           castling = (rook.getPosition().equals(new Position(0,0))) ? "0 - 0" : "0 - 0 - 0";
           newColKing = (rook.getPosition().equals(new Position(0,0))) ? 1 : 5;
           newColRook = (rook.getPosition().equals(new Position(0,0))) ? 2 : 4;
           row = 0;
           this.match.getPlayer(ColorChessboard.WHITE).addToHistory(castling);
        }else{
            kingIcon = new ImageIcon(this.match.getChessboard().getSquare(7, 3).getPiece().get().getIcon());
            castling = (rook.getPosition().equals(new Position(7,0))) ? "0 - 0" : "0 - 0 - 0";
            newColKing = (rook.getPosition().equals(new Position(7,0))) ? 1 : 5;
            newColRook = (rook.getPosition().equals(new Position(7,0))) ? 2 : 4;
            row = 7;
            this.match.getPlayer(ColorChessboard.BLACK).addToHistory(castling);            
        }
        
        configureCastling(row, newColKing, newColRook, kingIcon, rookIcon);
        
        this.handlerSwitchTurn();
        gameView.resetColors();
    }
    
    private void configureCastling(int row, int newColKing, int newColRook, Icon kingIcon, Icon rookIcon){ 
        this.updatePositionAndIcon(row, 3, row, newColKing, kingIcon);
        this.updatePositionAndIcon(row, (newColRook == 2)? 0 : 7, row, newColRook, rookIcon);
    }

    private void move(java.awt.event.ActionEvent evt){
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(gameView.getButtonGrid(row, col) == evt.getSource()){
                    Piece p = this.searchPiece();
                  
                    if(p != null && p.getColor().ordinal() == this.match.getChessboard().getTurn()){
                        // "pulisco" l'ambiente sistemando i colori della scacchiera e impostando gli enPassant a false nel nuovo turno
                        gameView.resetColors();
                        
                        Position positionEnPassant = this.match.turnHandler(p, row, col);
                        
                        if(positionEnPassant != null){
                            gameView.getButtonGrid(positionEnPassant.getRow(), positionEnPassant.getCol()).setIcon(null);
                            this.updatePositionAndIcon(p.getPosition().getRow(), p.getPosition().getCol(), 
                                       positionEnPassant.getRow(), positionEnPassant.getCol(), new ImageIcon(this.match.getChessboard().getSquare(p.getPosition().getRow(), p.getPosition().getCol()).getPiece().get().getIcon()));
                        }else{
                            // imposto la posizione
                            this.updatePositionAndIcon(p.getPosition().getRow(), p.getPosition().getCol(), 
                            row, col, new ImageIcon(this.match.getChessboard().getSquare(p.getPosition().getRow(), p.getPosition().getCol()).getPiece().get().getIcon()));
                        }
                        // azioni che devo fare necessariamente dopo aver aggiornato posizione
                        // dopo aver cambiato posizione, devo controllare se la nuova posizione fa scacco al re
                        // controllare se è stata fatta promotion
                        this.match.checkHandler();
                         
                        if(p instanceof Pawn pawn)
                        if(this.match.getChessboard().promotion(pawn)){
                            promotion = new Promotion(pawn, this, this.match);
                            promotion.setVisible(true);
                            this.match.getTimer().switchTurn();
                        }
            
                        // cambio turno
                        this.updateCemetery((this.match.getChessboard().getTurn() == 0) ? this.match.getPlayer(ColorChessboard.BLACK) : this.match.getPlayer(ColorChessboard.WHITE));
                        this.handlerSwitchTurn();
                    }
                    
                    if(p.getColor() == ColorChessboard.WHITE)
                        handleVictoryOrDraw(ColorChessboard.BLACK);
                    else
                        handleVictoryOrDraw(ColorChessboard.WHITE);     
                }
            }
        }
    }
    
    private void handlerSwitchTurn(){
        this.updateHistory();
        this.match.getChessboard().switchTurn();
        this.match.getTimer().switchTurn();
    }
    
    private void updatePositionAndIcon(int row, int col, int newRow, int newCol, Icon icon){
        this.gameView.updateIcon(row, col, newRow, newCol, icon);
        this.match.getChessboard().updatePosition(row, col,newRow, newCol);
    }
    
    private void handleVictoryOrDraw(ColorChessboard color) {
        int checkmateOrFlapResult = this.match.getChessboard().isCheckmateOrFlap(color);
        String message;

        switch (checkmateOrFlapResult) {
            case 0 -> message = (color == ColorChessboard.WHITE) ? "Il giocatore nero ha vinto per Scacco Matto" :
                                                                   "Il giocatore bianco ha vinto per Scacco Matto";
            case 1 -> message = "Patta";
            default -> {
                return; // Nessun risultato significativo
            }
        }

        this.match.getTimer().stopTimer();
        gameConclusion = new GameConclusion(message, gameView);
        gameConclusion.setVisible(true);        
    }
    
    private Piece getEvtPiece(java.awt.event.ActionEvent evt){
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(gameView.getButtonGrid(row, col) == evt.getSource()){
                    return this.match.getChessboard().getSquare(row, col).getPiece().get();
                }
            }
        }
        return null;
    }
    
    private Piece searchPiece(){
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(this.match.getChessboard().getSquare(row, col).getPiece().isPresent()){
                    if(this.match.getChessboard().getSquare(row, col).getPiece().get().isInAction()== true){
                        this.match.getChessboard().getSquare(row, col).getPiece().get().setInAction(false);
                        return this.match.getChessboard().getSquare(row, col).getPiece().get();
                    }
                }
            }
        }
        return null;
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
    
    public void updateHistory() {
        this.gameView.getHistory().setText("");
        this.gameView.getHistory().append("                Bianco \t\tNero");
        int it = 0;
        while (true){
            this.gameView.getHistory().append("\n"+it+". ");
            if(it < this.match.getPlayer(ColorChessboard.WHITE).getHistory().size())
                this.gameView.getHistory().append(this.match.getPlayer(ColorChessboard.WHITE).getHistory().get(it)+"\t\t     ");
            else 
                this.gameView.getHistory().append("        "+"\t");
            
            if(it < this.match.getPlayer(ColorChessboard.BLACK).getHistory().size())
                this.gameView.getHistory().append(this.match.getPlayer(ColorChessboard.BLACK).getHistory().get(it));
            else 
                this.gameView.getHistory().append("        ");
            
            if(it >= this.match.getPlayer(ColorChessboard.WHITE).getHistory().size() && it >= this.match.getPlayer(ColorChessboard.WHITE).getHistory().size()){
                break;
            }
            it++;
        }
    }
    
    private void startTimer() {
        this.match.getTimer().getTimer().scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            if (match.getTimer().getIsPlayer1Turn()) {
                    match.getTimer().setPlayer1RemainingTime(match.getTimer().getPlayer1RemainingTime() - 1000); //viene sottratto un secondo dal totale
                    if (match.getTimer().getPlayer1RemainingTime() < 0) {
                        //se il tempo termina allora viene invocato il metodo che termina il match.getTimer()
                        match.getTimer().stopTimer();
                        gameConclusion = new GameConclusion("Il giocatore nero vince per fine del tempo", gameView);
                        gameConclusion.setVisible(true);
                    } else {
                        //aggiornamento del Timer
                        gameView.getTimerPlayerW().setText(match.getTimer().formatTime(match.getTimer().getPlayer1RemainingTime()));
                    }
                } else {
                    match.getTimer().setPlayer2RemainingTime(match.getTimer().getPlayer2RemainingTime() - 1000); // Subtract one second
                    if (match.getTimer().getPlayer2RemainingTime() < 0) {
                        match.getTimer().stopTimer();
                        gameConclusion = new GameConclusion("Il giocatore bianco vince vinto per fine del tempo", gameView);
                        gameConclusion.setVisible(true);
                    } else {
                        gameView.getTimerPlayerB().setText(match.getTimer().formatTime(match.getTimer().getPlayer2RemainingTime()));
                    }
                }
            }
        }, 1000, 1000);
    }
}
