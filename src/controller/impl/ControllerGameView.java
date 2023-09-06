package controller.impl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Optional;
import java.util.TimerTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
import model.enumerations.ColorChessboardEnum;
import view.Promotion;
import model.gameEnvironment.impl.Chessboard;
import model.gameEnvironment.impl.Match;
import model.gameEnvironment.impl.Player;

public class ControllerGameView {

    public static final int WHITE_SQUARE = -1317;
    public static final int BLACK_SQUARE = -6530040;
    public static final int GREEN_SQUARE = -16711936;
    public static final int RED_SQUARE = -65536;
    private static ControllerGameView controllerGameView;
    private final GameView gameView;
    private final Match match;
    private Promotion promotion;
    private GameConclusion gameConclusion;
    // flag for test
    private boolean showMovementCalled = false;
    private boolean moveCalled         = false;
    private boolean castlingCalled     = false;

    public static ControllerGameView getInstance(GameView gameView){
        return controllerGameView == null ? controllerGameView = new ControllerGameView(gameView) : controllerGameView;
    }

    private ControllerGameView(final GameView gameView){
        Chessboard chessboard = Chessboard.getIstance();
        this.match = new Match(chessboard,new Player(ColorChessboardEnum.WHITE),new Player(ColorChessboardEnum.BLACK), new ChessTimer(600 * 1000));
        this.startTimer();
        this.gameView = gameView;
    }

    /**
     * Handles actions based on the color of the square clicked.
     * 
     *
     * @param evt   The ActionEvent generated by the click.
     * @param color The color of the square.
     */
    public void actions(java.awt.event.ActionEvent evt, int color) {
        switch (color) {
            case WHITE_SQUARE, BLACK_SQUARE -> {
                this.showMovement(evt);
                showMovementCalled = true;
            }
            case RED_SQUARE -> {
                this.move(evt);
                moveCalled = true;
            }
            case GREEN_SQUARE -> {
                Rook rook = (Rook) this.getEvtPiece(evt);
                if (rook != null) {
                    this.castling(rook);
                }
                castlingCalled = true;
            }
        }
    }

    public GameView getGameView(){
        return this.gameView;
    }

    public Match getMatch(){
        return this.match;
    }

    /**
     * Displays the legal movements of a selected piece and highlights squares for castling if applicable.
     * 
     * @param evt The ActionEvent triggered by a button click.
     */
    private void showMovement(java.awt.event.ActionEvent evt) {
        Position castling;
        ArrayList<Position> legalMovements;

        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                // Check if a piece is present on the square.
                if (this.match.getChessboard().getSquare(row, col).getPiece().isPresent()) {
                    Piece p = this.match.getChessboard().getSquare(row, col).getPiece().get();
                    // Check if the clicked square corresponds to the piece's color and type
                    if (gameView.getButtonGrid(row, col) == evt.getSource() && p.getColor().ordinal() == this.match.getChessboard().getTurn()) {
                        // Reset square colors and search for possible movements.
                        gameView.resetColors();
                        this.searchPiece();
                        legalMovements = this.match.getChessboard().legalMovements(p);
                        if (!legalMovements.isEmpty()) {
                            p.setInAction(true);
                            // Highlight squares where the piece can move.
                            this.gameView.changeBottonColor(legalMovements);
                        }
                        if (p instanceof King) {
                            // Check if castling is possible for the King.
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

    /**
     * Handles the castling move in chess, including the king and rook's movement and updating the game state.
     * 
     * @param rook The Rook piece involved in castling.
     */
    private void castling(Rook rook){
        Icon rookIcon = new ImageIcon(this.match.getChessboard().getSquare(rook.getPosition().getRow(), rook.getPosition().getCol()).getPiece().get().getIcon());
        Icon kingIcon;
        int row;
        int newColKing;
        int newColRook;
        String castling;        

        // Determine which player is performing castling (white or black).
        if(rook.getColor() == ColorChessboardEnum.WHITE){
           kingIcon = new ImageIcon(this.match.getChessboard().getSquare(0, 3).getPiece().get().getIcon()); 
           castling = (rook.getPosition().equals(new Position(0,0))) ? "0 - 0" : "0 - 0 - 0";
           newColKing = (rook.getPosition().equals(new Position(0,0))) ? 1 : 5;
           newColRook = (rook.getPosition().equals(new Position(0,0))) ? 2 : 4;
           row = 0;
           this.match.getPlayer(ColorChessboardEnum.WHITE).addToHistory(castling);
        }else{
            kingIcon = new ImageIcon(this.match.getChessboard().getSquare(7, 3).getPiece().get().getIcon());
            castling = (rook.getPosition().equals(new Position(7,0))) ? "0 - 0" : "0 - 0 - 0";
            newColKing = (rook.getPosition().equals(new Position(7,0))) ? 1 : 5;
            newColRook = (rook.getPosition().equals(new Position(7,0))) ? 2 : 4;
            row = 7;
            this.match.getPlayer(ColorChessboardEnum.BLACK).addToHistory(castling);            
        }

        // Configure castling by moving the king and rook icons.
        configureCastling(row, newColKing, newColRook, kingIcon, rookIcon);

        // Switch the turn, reset square colors.
        this.handlerSwitchTurn();
        gameView.resetColors();
    }

    /**
     * Configures the castling move by updating the positions and icons of the King and Rook pieces.
     * 
     * @param row       The row where the castling is performed.
     * @param newColKing The new column for the King piece after castling.
     * @param newColRook The new column for the Rook piece after castling.
     * @param kingIcon  The icon for the King piece.
     * @param rookIcon  The icon for the Rook piece.
     */
    private void configureCastling(int row, int newColKing, int newColRook, Icon kingIcon, Icon rookIcon){ 
        this.updatePositionAndIcon(row, 3, row, newColKing, kingIcon);
        this.updatePositionAndIcon(row, (newColRook == 2)? 0 : 7, row, newColRook, rookIcon);
    }

    /**
     * Handles a piece movement when a chessboard button is clicked. Moves the selected piece to the target position
     * on the chessboard, updates the game state, checks for special moves like en passant and promotions, and
     * manages turn switching and game outcomes.
     *
     * @param evt The ActionEvent generated by clicking a chessboard button.
     */
    private void move(java.awt.event.ActionEvent evt){
        for (int row = 0; row <= Chessboard.ROW_UPPER_LIMIT; row++) {
            for (int col = 0; col <= Chessboard.COL_UPPER_LIMIT; col++) {
                if(gameView.getButtonGrid(row, col) == evt.getSource()){
                    Piece p = this.searchPiece();

                    if(p != null && p.getColor().ordinal() == this.match.getChessboard().getTurn()){
                        // Reset the chessboard colors and set enPassant to false for the new turn.
                        gameView.resetColors();

                        Position positionEnPassant = this.match.turnHandler(p, row, col);

                        if(positionEnPassant != null){
                            // Handle en passant capture.
                            gameView.getButtonGrid(positionEnPassant.getRow(), positionEnPassant.getCol()).setIcon(null);
                        }

                        // Update the piece's position on the chessboard.
                        this.updatePositionAndIcon(p.getPosition().getRow(), p.getPosition().getCol(), 
                    row, col, new ImageIcon(this.match.getChessboard().getSquare(p.getPosition().getRow(), p.getPosition().getCol()).getPiece().get().getIcon()));

                        // Perform actions that must follow position updates, such as checking for check and promotions.
                        this.match.checkHandler();

                        if(p instanceof Pawn pawn)
                        if(this.match.getChessboard().promotion(pawn)){
                            // Handle pawn promotion.
                            promotion = new Promotion(pawn, this, this.match);
                            promotion.setVisible(true);
                            this.match.getTimer().switchTurn();
                        }

                        // Switch turns and update the cemetery.
                        this.updateCemetery((this.match.getChessboard().getTurn() == 0) ? this.match.getPlayer(ColorChessboardEnum.BLACK) :
                                                                                          this.match.getPlayer(ColorChessboardEnum.WHITE));
                        this.handlerSwitchTurn();
                    }

                    // Check for victory or draw conditions.
                    if(p.getColor() == ColorChessboardEnum.WHITE)
                        handleVictoryOrDraw(ColorChessboardEnum.BLACK);
                    else
                        handleVictoryOrDraw(ColorChessboardEnum.WHITE);     
                }
            }
        }
    }

    /**
     * Handles switching the turn in the chess game. Updates the game history, switches the turn on the chessboard,
     * and updates the timer to indicate the new player's turn.
     */
    private void handlerSwitchTurn(){
        this.updateHistory();
        this.match.getChessboard().switchTurn();
        this.match.getTimer().switchTurn();
    }

    /**
     * Updates the position and icon of a chess piece on both the game view and the chessboard model.
     *
     * @param row    The current row of the piece.
     * @param col    The current column of the piece.
     * @param newRow The new row to move the piece to.
     * @param newCol The new column to move the piece to.
     * @param icon   The icon for the piece.
     */
    private void updatePositionAndIcon(int row, int col, int newRow, int newCol, Icon icon){
        this.gameView.updateIcon(row, col, newRow, newCol, icon);
        this.match.getChessboard().updatePosition(row, col,newRow, newCol);
    }

    /**
     * Handles the end of the game by checking for checkmate or draw conditions. If either occurs,
     * it stops the timer, displays an appropriate message, and triggers the game conclusion dialog.
     *
     * @param color The color of the player to check for victory or draw.
     */
    private void handleVictoryOrDraw(ColorChessboardEnum color) {
        int checkmateOrFlapResult = this.match.getChessboard().isCheckmateOrFlap(color);
        String message;

        switch (checkmateOrFlapResult) {
            case 0 -> message = (color == ColorChessboardEnum.WHITE) ? "Black player wins by checkmate" :
                                                                   "White player wins by checkmate";
            case 1 -> message = "Draw";
            default -> {
                return; // No significant result
            }
        }

        this.match.getTimer().stopTimer();
        gameConclusion = new GameConclusion(message, gameView);
        gameConclusion.setVisible(true);        
    }

    /**
     * Retrieves the chess piece associated with the ActionEvent's source button on the game view.
     *
     * @param evt The ActionEvent triggered by a button click.
     * @return The chess piece associated with the button, or null if none is found.
     */
    private Piece getEvtPiece(java.awt.event.ActionEvent evt){
        return IntStream.rangeClosed(0, Chessboard.ROW_UPPER_LIMIT)
            .boxed()
            .flatMap(row ->
                    IntStream.rangeClosed(0, Chessboard.COL_UPPER_LIMIT)
                            .boxed()
                            .filter(col -> gameView.getButtonGrid(row, col) == evt.getSource())
                            .map(col -> this.match.getChessboard().getSquare(row, col).getPiece())
                            .filter(Optional::isPresent)
                            .map(Optional::get))
            .findFirst()
            .orElse(null);
    }

    /**
     * Searches for a chess piece that is currently in action, resets its action state, and returns it.
     * Used to identify the piece that is being moved.
     *
     * @return The chess piece that is currently in action, or null if none is found.
     */
    private Piece searchPiece(){
        return IntStream.rangeClosed(0, Chessboard.ROW_UPPER_LIMIT)
            .boxed()
            .flatMap(row ->
                    IntStream.rangeClosed(0, Chessboard.COL_UPPER_LIMIT)
                            .boxed()
                            .map(col -> this.match.getChessboard().getSquare(row, col).getPiece()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .filter(piece -> piece.isInAction())
            .peek(piece -> piece.setInAction(false))
            .findFirst()
            .orElse(null);

    }

    /**
     * Updates the display of the player's cemetery (captured pieces) on the game view.
     *
     * @param p The player whose cemetery needs to be updated.
     */
    private void updateCemetery(Player p){
        ArrayList<Piece> pCemetery = p.getCemetery();
        String str = pCemetery.stream()
                .map(Piece::pieceSwap)
                .collect(Collectors.joining());

        if (p.getColor() == ColorChessboardEnum.WHITE) {
            gameView.getjLabelCemeteryWhite().setText(str);
        } else {
            gameView.getjLabelCemeteryBlack().setText(str);
        }
    }

    /**
     * Updates the display of the game history on the game view.
     */
    public void updateHistory() {
        this.gameView.getHistory().setText("");
        this.gameView.getHistory().append("                Bianco \t\tNero");
        int it = 0;
        while (true){
            this.gameView.getHistory().append("\n"+it+". ");
            if(it < this.match.getPlayer(ColorChessboardEnum.WHITE).getHistory().size())
                this.gameView.getHistory().append(this.match.getPlayer(ColorChessboardEnum.WHITE).getHistory().get(it)+"\t\t     ");
            else 
                this.gameView.getHistory().append("        "+"\t");

            if(it < this.match.getPlayer(ColorChessboardEnum.BLACK).getHistory().size())
                this.gameView.getHistory().append(this.match.getPlayer(ColorChessboardEnum.BLACK).getHistory().get(it));
            else 
                this.gameView.getHistory().append("        ");

            if(it >= this.match.getPlayer(ColorChessboardEnum.WHITE).getHistory().size() && it >= this.match.getPlayer(ColorChessboardEnum.WHITE).getHistory().size()){
                break;
            }
            it++;
        }
    }

    /**
     * Starts the timer that tracks each player's remaining time for their moves.
     * If a player's time runs out, it triggers a game conclusion.
     */
    private void startTimer() {
        this.match.getTimer().getTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (match.getTimer().getIsPlayerWTurn()) {
                    match.getTimer().setPlayerWRemainingTime(match.getTimer().getPlayerWRemainingTime() - 1000); // Subtract one second
                    if (match.getTimer().getPlayerWRemainingTime() < 0) {
                        match.getTimer().stopTimer();
                        gameConclusion = new GameConclusion("Black player wins by time forfeit", gameView);
                        gameConclusion.setVisible(true);
                    } else {
                        gameView.getTimerPlayerW().setText(match.getTimer().formatTime(match.getTimer().getPlayerWRemainingTime()));
                    }
                } else {
                    match.getTimer().setPlayerBRemainingTime(match.getTimer().getPlayerBRemainingTime() - 1000); // Subtract one second
                    if (match.getTimer().getPlayerBRemainingTime() < 0) {
                        match.getTimer().stopTimer();
                        gameConclusion = new GameConclusion("White player wins by time forfeit", gameView);
                        gameConclusion.setVisible(true);
                    } else {
                        gameView.getTimerPlayerB().setText(match.getTimer().formatTime(match.getTimer().getPlayerBRemainingTime()));
                    }
                }
            }
        }, 1000, 1000);
    }

    // Test methods.
    public boolean isShowMovementCalled(){
        return this.showMovementCalled;        
    }

    public boolean isMoveCalled(){
        return this.moveCalled;
    }

    public boolean isCastlingCalled(){
        return this.castlingCalled;
    }     

    public void resetFlags(){
        this.showMovementCalled = false;
        this.moveCalled = false;
        this.castlingCalled = false;
    }

}
