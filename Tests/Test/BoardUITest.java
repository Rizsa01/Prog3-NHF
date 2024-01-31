package Test;

import UI.BoardUI;
import org.junit.Test;
import Game.Game;
import Game.Token;

import javax.swing.*;

import static org.junit.Assert.*;

/**
 * This class represents the tests for the class BoardUI.
 */
public class BoardUITest {

    /**
     * JUnit test method for the ability to move a piece on the game board.
     * It tests whether a piece in the corner with two adjacent pieces can move.
     */
    @Test
    public void TestAbleToMove() {
        JFrame frame = new JFrame();
        BoardUI ui = new BoardUI(frame, Token.Player2);
        Game game = new Game(Token.Player2);
        game.getBoard().setPositionAsPlayer(0,game.getPlayer1().getPlayerToken());
        game.getBoard().setPositionAsPlayer(1,game.getPlayer2().getPlayerToken());
        game.getBoard().setPositionAsPlayer(9,game.getPlayer2().getPlayerToken());
        assertEquals("The piece in the corner with two adjacent pieces can't move",false,BoardUI.ableToMove(game,game.getPlayer1(),1));
    }

    /**
     * JUnit test method for checking if a player has won the game.
     * It tests whether Player1 is considered to have won based on the game state and pieces on the board.
     */
    @Test
    public void TestIfWin() {
        Game game = new Game(Token.Player2);
        game.getPlayer1().setPiecesOnBoard(2);
        game.getPlayer1().setGameState(2);
        assertEquals("Player1 lost",2,BoardUI.ifWin(game));
    }


}