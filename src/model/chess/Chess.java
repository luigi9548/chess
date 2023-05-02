package model.chess;

import model.gameEnvironment.Chessboard;
import model.gameEnvironment.Game;
import model.gameEnvironment.Player;
import model.pieces.Piece;
import view.*;

public class Chess {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setVisible(true);
        /*Chessboard chessboard = new Chessboard();
        GameView gameView = new GameView();
        gameView.initChessboard(chessboard);*/
        
        
       /* Player g = new Player("Bianco", true);
        Player g2 = new Player("Nero", false);
        g.createPieces();
        Game game = new Game(new Chessboard(), g, g2, true);*/
    }
}
