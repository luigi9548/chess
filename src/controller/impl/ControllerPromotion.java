package controller.impl;

import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.enumerations.ColorChessboardEnum;
import model.enumerations.IconEnum;
import model.functionality.impl.Position;
import model.gameEnvironment.impl.Match;
import model.pieces.impl.Bishop;
import model.pieces.impl.Knight;
import model.pieces.impl.Queen;
import model.pieces.impl.Rook;
import view.impl.Promotion;

public class ControllerPromotion {
    private final Promotion promotion;
    private Position position;
    private ControllerGameView gameView;
    private Match match;
    private Icon icon;
    
    public ControllerPromotion(final Promotion promotion){
        this.promotion = promotion;
    }
    
    private void init(String s){
        position = promotion.getPawn().getPosition();
        gameView = promotion.getControllerGameView();
        match = promotion.getMatch();

        switch(s){
            case "QUEEN" -> {
                if (promotion.getPawn().getColor() == ColorChessboardEnum.WHITE) 
                    icon = new ImageIcon(IconEnum.WQUEEN_ICON.getIcon());
                else
                    icon = new ImageIcon(IconEnum.BQUEEN_ICON.getIcon());
            }
            case "ROOK" -> {
                if (promotion.getPawn().getColor() == ColorChessboardEnum.WHITE) 
                    icon = new ImageIcon(IconEnum.WROOK_ICON.getIcon());
                else
                    icon = new ImageIcon(IconEnum.BROOK_ICON.getIcon());
            }
            case "BISHOP" -> {
                if (promotion.getPawn().getColor() == ColorChessboardEnum.WHITE) 
                    icon = new ImageIcon(IconEnum.WBISHOP_ICON.getIcon());
                else
                    icon = new ImageIcon(IconEnum.BBISHOP_ICON.getIcon());
            }
            case "KNIGHT" -> {
                if (promotion.getPawn().getColor() == ColorChessboardEnum.WHITE) 
                    icon = new ImageIcon(IconEnum.WKNIGHT_ICON.getIcon());
                else
                    icon = new ImageIcon(IconEnum.BKNIGHT_ICON.getIcon());
            }    
    
        }        
    }

    /**
     * Action handler for promoting to a Queen.
     * 
     * @param evt
     */
    public void queenActionPerformed(ActionEvent evt) {
        init("QUEEN");
    
        // Set the icon in the view
        gameView.getGameView().getButtonGrid(position.getRow(), position.getCol()).setIcon(icon);

        // Set the piece at the model level
    if(promotion.getPawn().getColor() == ColorChessboardEnum.WHITE){
            Queen queenW = new Queen(position, ColorChessboardEnum.WHITE, match.getChessboard(), 'q');
            match.getChessboard().getSquare(position.getRow(), position.getCol()).setPiece(queenW);
            queenW.setIcon(IconEnum.WQUEEN_ICON.getIcon());
            this.match.getPlayer(ColorChessboardEnum.WHITE).removeLastString();
            this.match.getPlayer(ColorChessboardEnum.WHITE).addToHistory(position.getStringPosition() + Character.toUpperCase(queenW.getPieceSign()));
        }else{
            Queen queenB = new Queen(position, ColorChessboardEnum.BLACK, match.getChessboard(), 'Q');
            match.getChessboard().getSquare(position.getRow(), position.getCol()).setPiece(queenB);
            queenB.setIcon(IconEnum.BQUEEN_ICON.getIcon());
            this.match.getPlayer(ColorChessboardEnum.BLACK).removeLastString();
            this.match.getPlayer(ColorChessboardEnum.BLACK).addToHistory(position.getStringPosition() + Character.toUpperCase(queenB.getPieceSign()));
        }
        
        this.gameView.updateHistory();
        this.match.getTimer().switchTurn();
        promotion.setVisible(false);
    }

    /**
     * Action handler for promoting to a Rook.
     * 
     * @param evt
     */
    public void rookActionPerformed(ActionEvent evt) {
        init("ROOK");
        
        // Set the icon in the view
        gameView.getGameView().getButtonGrid(position.getRow(), position.getCol()).setIcon(icon);
        
        // Set the piece at the model level
        if(promotion.getPawn().getColor() == ColorChessboardEnum.WHITE){
            Rook rookW = new Rook(position, ColorChessboardEnum.WHITE, match.getChessboard(), 'r');
            match.getChessboard().getSquare(position.getRow(), position.getCol()).setPiece(rookW);
            rookW.setIcon(IconEnum.WROOK_ICON.getIcon());
            this.match.getPlayer(ColorChessboardEnum.WHITE).removeLastString();
            this.match.getPlayer(ColorChessboardEnum.WHITE).addToHistory(position.getStringPosition() + Character.toUpperCase(rookW.getPieceSign()));
        }else{
            Rook rookB = new Rook(position, ColorChessboardEnum.BLACK, match.getChessboard(), 'R');
            match.getChessboard().getSquare(position.getRow(), position.getCol()).setPiece(rookB);
            rookB.setIcon(IconEnum.BROOK_ICON.getIcon());
            this.match.getPlayer(ColorChessboardEnum.BLACK).removeLastString();
            this.match.getPlayer(ColorChessboardEnum.BLACK).addToHistory(position.getStringPosition() + Character.toUpperCase(rookB.getPieceSign()));
        }
        
        this.gameView.updateHistory();
        this.match.getTimer().switchTurn();
        promotion.setVisible(false);
    }

       
    /**
     * Action handler for promoting to a Bishop. 
     * 
     * @param evt
     */
       public void bishopActionPerformed(ActionEvent evt) {
           init("BISHOP");
        
        // Set the icon in the view
        gameView.getGameView().getButtonGrid(position.getRow(), position.getCol()).setIcon(icon);
           
        // Set the piece at the model level
        if(promotion.getPawn().getColor() == ColorChessboardEnum.WHITE){
            Bishop bishopW = new Bishop(position, ColorChessboardEnum.WHITE, match.getChessboard(), 'b');
            match.getChessboard().getSquare(position.getRow(), position.getCol()).setPiece(bishopW);
            bishopW.setIcon(IconEnum.WBISHOP_ICON.getIcon());
            this.match.getPlayer(ColorChessboardEnum.WHITE).removeLastString();
            this.match.getPlayer(ColorChessboardEnum.WHITE).addToHistory(position.getStringPosition() + Character.toUpperCase(bishopW.getPieceSign()));
        }else{
            Bishop bishopB = new Bishop(position, ColorChessboardEnum.BLACK, match.getChessboard(), 'B');
            match.getChessboard().getSquare(position.getRow(), position.getCol()).setPiece(bishopB);
            bishopB.setIcon(IconEnum.BBISHOP_ICON.getIcon());
            this.match.getPlayer(ColorChessboardEnum.BLACK).removeLastString();
            this.match.getPlayer(ColorChessboardEnum.BLACK).addToHistory(position.getStringPosition() + Character.toUpperCase(bishopB.getPieceSign()));
        }
        
        this.gameView.updateHistory();
        this.match.getTimer().switchTurn();
        promotion.setVisible(false);
    }
    
    /**
     * Action handler for promoting to a Knight.
     * 
     * @param evt
     */
    public void knightActionPerformed(ActionEvent evt) {
        init("KNIGHT");
           
        // Set the icon in the view
        gameView.getGameView().getButtonGrid(position.getRow(), position.getCol()).setIcon(icon);
           
        // Set the piece at the model level
        if(promotion.getPawn().getColor() == ColorChessboardEnum.WHITE){
            Knight knightW = new Knight(position, ColorChessboardEnum.WHITE, match.getChessboard(), 'h');
            match.getChessboard().getSquare(position.getRow(), position.getCol()).setPiece(knightW);
            knightW.setIcon(IconEnum.WKNIGHT_ICON.getIcon());
            this.match.getPlayer(ColorChessboardEnum.WHITE).removeLastString();
            this.match.getPlayer(ColorChessboardEnum.WHITE).addToHistory(position.getStringPosition() + Character.toUpperCase(knightW.getPieceSign()));
        }else{
            Knight knightB = new Knight(position, ColorChessboardEnum.BLACK, match.getChessboard(), 'H');
            match.getChessboard().getSquare(position.getRow(), position.getCol()).setPiece(knightB);
            knightB.setIcon(IconEnum.BKNIGHT_ICON.getIcon());
            this.match.getPlayer(ColorChessboardEnum.BLACK).removeLastString();
            this.match.getPlayer(ColorChessboardEnum.BLACK).addToHistory(position.getStringPosition() + Character.toUpperCase(knightB.getPieceSign()));
        }
        
        this.gameView.updateHistory();
        this.match.getTimer().switchTurn();
        promotion.setVisible(false);
    }
}
