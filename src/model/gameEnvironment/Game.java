package model.gameEnvironment;

public class Game {
    private Chessboard chessboard;
    private Player playerW;
    private Player playerB;
    private boolean turn;
    
    public Game(Chessboard chessboard, Player playerW, Player playerB, boolean turn){
       this.chessboard = chessboard;
       this.playerW = playerW;
       this.playerB = playerB;
       this.setTurn(turn);
    }
    
    private void setTurn(boolean turn){
        if(this.turn == false){
            this.turn = true;
        }
    }
    
    private void initializePlayers(){
        this.playerB.createPieces(chessboard);
        this.playerW.createPieces(chessboard);
    }
}
