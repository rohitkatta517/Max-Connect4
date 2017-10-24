import java.util.*;

/**
 * This is the AiPlayer class.  It simulates a minimax player for the max
 * connect four game.
 * The constructor essentially does nothing. 
 *
 *  @author Rohit Katta
 *  @UID: 1001512896
 *  @Original-author James Spargo
 *
 */

public class AiPlayer
{
    private int depth;

    /**
     * Creates a player with a depth level
     *
     */
    public AiPlayer()
    {
        this.depth = 5;
    }

    public AiPlayer(int depth) {
        this.depth = depth;
    }

    /**
     * This method plays a piece randomly on the board
     * @param currentGame The GameBoard object that is currently being used to
     * play the game.
     * @return an integer indicating which column the AiPlayer would like
     * to play in.
     */
    public int findBestPlay( GameBoard currentGame )
    {
        int playChoice = 0;
        if (currentGame.getCurrentTurn() == 1) {
            int v = 1000000000;
            for (int i = 0; i < 6; i ++) {
                if (currentGame.isValidPlay(i)) {
                    GameBoard nextGame = new GameBoard(currentGame.getGameBoard());
                    nextGame.playPiece(i);
                    int value = Max(nextGame, depth, -1000000000, 1000000000);
                    if (v > value) {
                        playChoice = i;
                        v = value;
                    }
                }
            }
        } else {
            int v = -1000000000;
            for (int i = 0; i < 6; i++) {
                if (currentGame.isValidPlay(i)) {
                    GameBoard nextGame = new GameBoard(currentGame.getGameBoard());
                    nextGame.playPiece(i);
                    int value = Min(nextGame, depth, -1000000000, 1000000000);
                    if (v < value) {
                        playChoice = i;
                        v = value;
                    }
                }
            }
        }
        return playChoice;
    }

    public int Max(GameBoard currentGame, int depthLevel, int alpha, int beta) {
        // assume this is player 2.
        // terminal utility is player2_score - player1_score
        if (currentGame.getPieceCount() < 42 && depthLevel > 0) {
            int v = -1000000000;
            for (int i = 0; i < 6; i ++) {
                if (currentGame.isValidPlay(i)) {
                    GameBoard nextGame = new GameBoard(currentGame.getGameBoard());
                    nextGame.playPiece(i);
                    int value = Min(nextGame, depthLevel - 1, alpha, beta);
                    if (v < value) {
                        v = value;
                    }
                    if (v >= beta) {
                        //System.out.println("Pruned: v = " + v + ", beta = " + beta);
                        return v;
                    }
                    if (alpha < v) {
                        alpha = v;
                    }
                }
            }
            return v;
        } else {
           return StaticEvalFn(currentGame);
        }
    }

    public int Min(GameBoard currentGame, int depthLevel, int alpha, int beta) {
        // assume this is player 1
        // terminal utility is player2_score - player1_score
        if (currentGame.getPieceCount() < 42 && depthLevel > 0) {
            int v = 1000000000;
            for (int i = 0; i < 6; i ++) {
                if (currentGame.isValidPlay(i)) {
                    GameBoard nextGame = new GameBoard(currentGame.getGameBoard());
                    nextGame.playPiece(i);
                    int value = Max(nextGame, depthLevel - 1, alpha, beta);
                    if (v > value) {
                        v = value;
                    }
                    if (v <= alpha) {
                        //System.out.println("Pruned: v = " + v + ", alpha = " + alpha);
                        return v;
                    }
                    if (beta > v) {
                        beta = v;
                    }
                }
            }
            return v;
        } else {
            return StaticEvalFn(currentGame);
        }
    }

    /**
     * This is Evaluation Method. If node is full and player2 Wins its ends Highest value
     * If Player1 wins it sens least value.
     * When depth is reached it calculates value giving weighted average to 4,3,2
     * 4 has weight of 1000
     * 3 has weight of 50
     * 2 has weight of 10
     * @param currentGame The GameBoard object that is currently being used to
     * play the game.
     * @return an integer showing the value of that node
     */
    private int StaticEvalFn(GameBoard currentGame)
    {
        int result = 0;
        if (currentGame.isFull()) {
            if((currentGame.getScore(2) - currentGame.getScore(1)) > 0){
                return 1000000000;
            } else if((currentGame.getScore(2) - currentGame.getScore(1)) == 0){
                return 0;
            } else {
                return -1000000000;
            }
        } else {

            result = (currentGame.getScore(1) * 1000) + (currentGame.getThreeCount(1) * 50) + (currentGame.getTwoCount(1) * 10)
                    - (currentGame.getScore(2) * 1000) - (currentGame.getThreeCount(2) * 50) - (currentGame.getTwoCount(2) * 10);
            //System.out.println("Eval: " + -result);
        }
        return (-result);
    }
}