package controller;

import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import model.functionality.ColorChessboard;
import model.functionality.Position;
import model.gameEnvironment.Chessboard;
import model.pieces.Bishop;
import model.pieces.Knight;
import model.pieces.Queen;
import model.pieces.Rook;
import view.GameView;
import view.Promotion;

public class ControllerPromotion {
    private final Promotion promotion;
    private Position position;
    private GameView gameView;
    private Chessboard chessboard;
    private Icon icon;
    
    public ControllerPromotion(final Promotion promotion){
        this.promotion = promotion;
    }
    
    private void init(String s){
        position = promotion.getPawn().getPosition();
        gameView = promotion.getGameView();
        chessboard = promotion.getChessboard();

        switch(s){
            case "QUEEN" -> {
                if (promotion.getPawn().getColor() == ColorChessboard.BIANCO) 
                    icon = new ImageIcon(".\\src\\images\\whiteQueen.png");
                else
                    icon = new ImageIcon(".\\src\\images\\blackQueen.png");
            }
            case "ROOK" -> {
                if (promotion.getPawn().getColor() == ColorChessboard.BIANCO) 
                    icon = new ImageIcon(".\\src\\images\\whiteRook.png");
                else
                    icon = new ImageIcon(".\\src\\images\\blackRook.png");
            }
            case "BISHOP" -> {
                if (promotion.getPawn().getColor() == ColorChessboard.BIANCO) 
                    icon = new ImageIcon(".\\src\\images\\whiteBishop.png");
                else
                    icon = new ImageIcon(".\\src\\images\\blackBishop.png");
            }
            case "KNIGHT" -> {
                if (promotion.getPawn().getColor() == ColorChessboard.BIANCO) 
                    icon = new ImageIcon(".\\src\\images\\whiteKnight.png");
                else
                    icon = new ImageIcon(".\\src\\images\\blackKnight.png");
            }    
        }        
    }

    public void queenActionPerformed(ActionEvent evt) {
        init("QUEEN");
        // imposto icona nella view
        gameView.getButtonGrid(position.getRow(), position.getCol()).setIcon(icon);
        
        // posiziono il pezzo a livello model
        if(promotion.getPawn().getColor() == ColorChessboard.BIANCO){
            Queen queenW = new Queen(position,ColorChessboard.BIANCO,chessboard, 'q');
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(queenW);
            queenW.setIcon(".\\src\\images\\whiteQueen.png");
            this.gameView.getController().getPlayer(ColorChessboard.BIANCO).removeLastString();
            this.gameView.getController().getPlayer(ColorChessboard.BIANCO).addToHistory(position.getStringPosition() + Character.toUpperCase(queenW.getPieceSign()));
        }else{
            Queen queenB = new Queen(position,ColorChessboard.NERO,chessboard, 'Q');
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(queenB);
            queenB.setIcon(".\\src\\images\\blackQueen.png");
            this.gameView.getController().getPlayer(ColorChessboard.NERO).removeLastString();
            this.gameView.getController().getPlayer(ColorChessboard.NERO).addToHistory(position.getStringPosition() + Character.toUpperCase(queenB.getPieceSign()));
        }
        this.gameView.getController().updateHistory();
        promotion.setVisible(false);
    }

    public void rookActionPerformed(ActionEvent evt) {
        init("ROOK");
        gameView.getButtonGrid(position.getRow(), position.getCol()).setIcon(icon);
        if(promotion.getPawn().getColor() == ColorChessboard.BIANCO){
            Rook rookW = new Rook(position,ColorChessboard.BIANCO,chessboard, 'r');
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(rookW);
            rookW.setIcon(".\\src\\images\\whiteRook.png");
            this.gameView.getController().getPlayer(ColorChessboard.BIANCO).removeLastString();
            this.gameView.getController().getPlayer(ColorChessboard.BIANCO).addToHistory(position.getStringPosition() + Character.toUpperCase(rookW.getPieceSign()));
        }else{
            Rook rookB = new Rook(position,ColorChessboard.NERO,chessboard, 'R');
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(rookB);
            rookB.setIcon(".\\src\\images\\blackRook.png");
            this.gameView.getController().getPlayer(ColorChessboard.NERO).removeLastString();
            this.gameView.getController().getPlayer(ColorChessboard.NERO).addToHistory(position.getStringPosition() + Character.toUpperCase(rookB.getPieceSign()));
        }
        this.gameView.getController().updateHistory();
        promotion.setVisible(false);
    }

    public void bishopActionPerformed(ActionEvent evt) {
        init("BISHOP");
        gameView.getButtonGrid(position.getRow(), position.getCol()).setIcon(icon);
        if(promotion.getPawn().getColor() == ColorChessboard.BIANCO){
            Bishop bishopW = new Bishop(position,ColorChessboard.BIANCO,chessboard, 'b');
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(bishopW);
            bishopW.setIcon(".\\src\\images\\whiteBishop.png");
            this.gameView.getController().getPlayer(ColorChessboard.BIANCO).removeLastString();
            this.gameView.getController().getPlayer(ColorChessboard.BIANCO).addToHistory(position.getStringPosition() + Character.toUpperCase(bishopW.getPieceSign()));
        }else{
            Bishop bishopB = new Bishop(position,ColorChessboard.NERO,chessboard, 'B');
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(bishopB);
            bishopB.setIcon(".\\src\\images\\blackBishop.png");
            this.gameView.getController().getPlayer(ColorChessboard.NERO).removeLastString();
            this.gameView.getController().getPlayer(ColorChessboard.NERO).addToHistory(position.getStringPosition() + Character.toUpperCase(bishopB.getPieceSign()));
        }
        this.gameView.getController().updateHistory();
        promotion.setVisible(false);
    }

    public void knightActionPerformed(ActionEvent evt) {
        init("KNIGHT");
        gameView.getButtonGrid(position.getRow(), position.getCol()).setIcon(icon);
        if(promotion.getPawn().getColor() == ColorChessboard.BIANCO){
            Knight knightW = new Knight(position,ColorChessboard.BIANCO,chessboard, 'h');
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(knightW);
            knightW.setIcon(".\\src\\images\\whiteKnight.png");
            this.gameView.getController().getPlayer(ColorChessboard.BIANCO).removeLastString();
            this.gameView.getController().getPlayer(ColorChessboard.BIANCO).addToHistory(position.getStringPosition() + Character.toUpperCase(knightW.getPieceSign()));
        }else{
            Knight knightB = new Knight(position,ColorChessboard.NERO,chessboard, 'H');
            chessboard.getSquare(position.getRow(), position.getCol()).setPiece(knightB);
            knightB.setIcon(".\\src\\images\\blackKnight.png");
            this.gameView.getController().getPlayer(ColorChessboard.NERO).removeLastString();
            this.gameView.getController().getPlayer(ColorChessboard.NERO).addToHistory(position.getStringPosition() + Character.toUpperCase(knightB.getPieceSign()));
        }
        this.gameView.getController().updateHistory();
        promotion.setVisible(false);
    }   
}
