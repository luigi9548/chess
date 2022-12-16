package model.chess;

import model.gameEnvironment.Chessboard;
import model.gameEnvironment.Game;
import model.gameEnvironment.Player;
import model.pieces.Piece;

public class Chess {
    public static void main(String[] args) {
        Player g = new Player("Bianco", true);
        Player g2 = new Player("Nero", false);
        g.createPieces();
        Game game = new Game(new Chessboard(), g, g2, true);
    }
}
