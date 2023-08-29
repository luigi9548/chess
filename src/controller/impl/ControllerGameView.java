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
        //this.timer = new ChessTimer(600 * 1000);
        this.startTimer();
        this.gameView = gameView;
    }
    
    public Player getPlayer(ColorChessboard color){
        return (color == ColorChessboard.WHITE)? this.match.getWhiteP() : this.match.getBlackP();
    }
  
    public Chessboard getChessboard(){
        return this.match.getChessboard();
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
                if (this.match.getChessboard().getSquare(row, col).getPiece().isPresent()) {
                    Piece p = this.match.getChessboard().getSquare(row, col).getPiece().get();
                    if (gameView.getButtonGrid(row, col) == evt.getSource() && p.getColor().ordinal() == this.match.getChessboard().getTurn()) {
                        gameView.resetColors();
                        this.searchPiece();
                        legalMovements = this.match.getChessboard().legalMovements(p);
                        if (!legalMovements.isEmpty()) {
                            p.setInAction(true);
                            this.changeBottonColor(legalMovements);
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
           this.match.getWhiteP().addToHistory(castling);
        }else{
            kingIcon = new ImageIcon(this.match.getChessboard().getSquare(7, 3).getPiece().get().getIcon());
            castling = (rook.getPosition().equals(new Position(7,0))) ? "0 - 0" : "0 - 0 - 0";
            newColKing = (rook.getPosition().equals(new Position(7,0))) ? 1 : 5;
            newColRook = (rook.getPosition().equals(new Position(7,0))) ? 2 : 4;
            row = 7;
            this.match.getBlackP().addToHistory(castling);            
        }
        
        configureCastling(row, newColKing, newColRook, kingIcon, rookIcon);
        
        this.updateHistory();
        this.match.getChessboard().switchTurn();
        this.match.getTimer().switchTurn();
        gameView.resetColors();
    }
    
    private void configureCastling(int row, int newColKing, int newColRook, Icon kingIcon, Icon rookIcon){                
        updatePosition(row, 3, row, newColKing, kingIcon);
        updatePosition(row, (newColRook == 2)? 0 : 7, row, newColRook, rookIcon);
    }

    private void move(java.awt.event.ActionEvent evt){
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(gameView.getButtonGrid(row, col) == evt.getSource()){
                    Piece p = this.searchPiece();
                  
                    if(p != null && p.getColor().ordinal() == this.match.getChessboard().getTurn()){
                        // "pulisco" l'ambiente sistemando i colori della scacchiera e impostando gli enPassant a false nel nuovo turno
                        gameView.resetColors();
                        if(this.match.getChessboard().getTurn() == 0)
                            changeEnPassant(this.match.getChessboard().getPiecesByColor(ColorChessboard.WHITE));
                        else
                            changeEnPassant(this.match.getChessboard().getPiecesByColor(ColorChessboard.BLACK));
                        
                        boolean isEnPassant = false;
                                                
                        // configuro pedone per: enPassant, prima mossa, promotion
                        if(p instanceof Pawn pawn)
                            isEnPassant = configurePawn(pawn, row, col);
                        
                        // determino cronologia
                        String history = calculateHistory(isEnPassant, p, row, col);

                        // aggiornamento cronologia e cimitero
                        if(p.getColor() == ColorChessboard.WHITE)
                            updateHistory(p, row, col, history, this.match.getWhiteP(), this.match.getBlackP(), isEnPassant);
                        else
                            updateHistory(p, row, col, history, this.match.getBlackP(), this.match.getWhiteP(), isEnPassant);
                            
                        // imposto la posizione
                        updatePosition(p.getPosition().getRow(), p.getPosition().getCol(), 
                                       row, col, new ImageIcon(this.match.getChessboard().getSquare(p.getPosition().getRow(), p.getPosition().getCol()).getPiece().get().getIcon()));
                        
                        
                        // azioni che devo fare necessariamente dopo aver aggiornato posizione
                        // dopo aver cambiato posizione, devo controllare se la nuova posizione fa scacco al re
                        // controllare se è stata fatta promotion
                        String checkString;
                        if(this.match.getChessboard().isCheck(ColorChessboard.BLACK)){
                            checkString = this.match.getWhiteP().getHistory().get(this.match.getWhiteP().getHistory().size() - 1).concat("+");
                            this.match.getWhiteP().removeLastString();
                            this.match.getWhiteP().addToHistory(checkString);
                        }else if(this.match.getChessboard().isCheck(ColorChessboard.WHITE)){
                            checkString = this.match.getBlackP().getHistory().get(this.match.getBlackP().getHistory().size() - 1).concat("+");
                            this.match.getBlackP().removeLastString();
                            this.match.getBlackP().addToHistory(checkString);
                        }
                         
                        if(p instanceof Pawn pawn)
                        if(this.match.getChessboard().promotion(pawn)){
                            promotion = new Promotion(pawn, gameView, this.match.getChessboard());
                            promotion.setVisible(true);
                        }
            
                        // cambio turno
                        this.updateHistory();
                        this.match.getChessboard().switchTurn();
                        this.match.getTimer().switchTurn();
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
        int checkmateOrFlapResult = this.match.getChessboard().isCheckmateOrFlap(color);
        String message;

        if (checkmateOrFlapResult == 0) {
            message = (color == ColorChessboard.WHITE) ? "Il giocatore nero ha vinto per Scacco Matto" :
                                                         "Il giocatore bianco ha vinto per Scacco Matto";
        } else if (checkmateOrFlapResult == 1) {
            message = "Patta";
        } else {
            return; // Nessun risultato significativo
        }

        this.match.getTimer().stopTimer();
        gameConclusion = new GameConclusion(message, gameView);
        gameConclusion.setVisible(true);        
    }

    // aggiorno la nuova posizione
    private void updatePosition(int row, int col, int newRow, int newCol, Icon icon){
        // scambio icona
        gameView.getButtonGrid(newRow, newCol).setIcon(icon);
        gameView.getButtonGrid(row, col).setIcon(null);       
        // imposto pezzo e posizione
        this.match.getChessboard().getSquare(newRow, newCol).setPiece(this.match.getChessboard().getSquare(row, col).getPiece().get());
        this.match.getChessboard().getSquare(newRow, newCol).getPiece().get().setPosition(new Position(newRow, newCol));
        this.match.getChessboard().getSquare(row, col).setPiece(null);
    }
    
    private boolean configurePawn(Pawn pawn, int row, int col){
        boolean isEnPassant = false;
        
        // con il pedone ci sono diverse azioni da eseguire
        // 1) vedere se è avvenuto enPassant (per scriverlo nella cronologia)
        // 2) verificare se è avvenuta la prima mossa del pezzo
        // 3) impostare enPassant a true nel caso avesse eseguito mossa tale da rendere possibile enPassant nel turno successivo
        // 4) controllare se è arrivato a fine scacchiera per effettuare promotion
        
        // 1)
        Position pos = this.match.getChessboard().enPassant(pawn);
        if(pos != null && pos.getCol() == col)
            isEnPassant = true;
        
        // 2) - 3)
        if (pawn.isFirstMove()){
            pawn.switchFirstMove();
            if((pawn.getColor() == ColorChessboard.WHITE && row == 3) ||
               (pawn.getColor() == ColorChessboard.BLACK && row == 4) ){
                // questo if è perché durante la prima mossa si deve muovere di 2
                pawn.setEnPassant(true);
            }
        }
        
        // 4)
        /*if(this.match.getChessboard().promotion(pawn)){
            promotion = new Promotion(pawn, gameView, this.match.getChessboard());
            promotion.setVisible(true);
        }*/
        
        return isEnPassant;
    }
    
    private String calculateHistory(boolean isEnPassant, Piece p, int row, int col){
        boolean hasEaten = false;
        String history;
        
        // se è avvenuto enPassant aggiungo nella fine della stringa "e.p."
        if(isEnPassant){
            hasEaten = true;
            if(p.getColor() == ColorChessboard.WHITE)
                history = p.getPosition().numToLetterBySubstr() + "x" + new Position(row,col).getStringPosition() + " e. p.";
            else
                history = p.getPosition().numToLetterBySubstr() + "x" + this.match.getChessboard().enPassant((Pawn) p).getStringPosition() + " e. p."; 
        }else{
            if(this.match.getChessboard().getSquare(row, col).getPiece().isPresent())
                hasEaten = true;
                
            history = this.moveString(p.getPosition(), new Position(row, col), hasEaten, p.getPieceSign());   
        }
        return history;
    }
    
    private void updateHistory(Piece p, int row, int col, String history, Player current, Player enemy, boolean isEnPassant){
        
        current.addToHistory(history);
        
        // controllo se ha mangiato così modifico anche il cimitero
        if(this.match.getChessboard().getSquare(row, col).getPiece().isPresent()){
            enemy.addPieceCemetery(this.match.getChessboard().getSquare(row, col).getPiece().get());
            this.updateCemetery(enemy);
        }
        
        // controllo se ho mangiato con enPassant perchè la posizione del nemico mangiato sarà diversa
        if(isEnPassant){
            Position pos = this.match.getChessboard().enPassant((Pawn) p);
            enemy.addPieceCemetery(this.match.getChessboard().getSquare(pos.getRow(), pos.getCol()).getPiece().get()); 
            gameView.getButtonGrid(pos.getRow(), pos.getCol()).setIcon(null);
            this.match.getChessboard().getSquare(pos.getPosition().getRow(), pos.getPosition().getCol()).setPiece(null);
            this.updateCemetery(enemy);
        }
        
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
        if(typeUpper == 'P'){ // se è un pedone non devo scrivere il segno P davanti alle coordinate
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
            if(it < this.match.getWhiteP().getHistory().size())
                this.gameView.getHistory().append(this.match.getWhiteP().getHistory().get(it)+"\t\t     ");
            else 
                this.gameView.getHistory().append("        "+"\t");
            
            if(it < this.match.getBlackP().getHistory().size())
                this.gameView.getHistory().append(this.match.getBlackP().getHistory().get(it));
            else 
                this.gameView.getHistory().append("        ");
            
            if(it >= this.match.getWhiteP().getHistory().size() && it >= this.match.getWhiteP().getHistory().size()){
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
