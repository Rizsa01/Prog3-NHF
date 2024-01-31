package Test;

import Game.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class represents the tests for the Player class.
 */
public class PlayerTest {

    /**
     * JUnit test method for getting the default game state of a player.
     * It checks whether the default game state is 1.
     */
    @Test
    public void TestGetGameState() {
        Player player = new Player();
        assertEquals("gamestate is 1 by default", 1, player.getGameState());
    }

    /**
     * JUnit test method for setting the game state of a player.
     * It checks whether the game state can be successfully changed to 2.
     */
    @Test
    public void TestSetGameState() {
        Player player = new Player();
        player.setGameState(2);
        assertEquals("changed gamestate to 2", 2,player.getGameState());
    }

    /**
     * JUnit test method for getting the default number of pieces a player has.
     * It checks whether the default number of pieces is 9.
     */
    @Test
    public void TestgetPieces() {
        Player player = new Player();
        assertEquals("9 by default",9,player.getPieces());
    }

    /**
     * JUnit test method for getting the number of pieces placed on the game board by a player.
     * It checks whether the number of pieces on the board is correctly set to 69.
     */
    @Test
    public void TestgetPiecesOnBoard() {
        Player player = new Player();
        player.setPieces(69);
        assertEquals("set pieces to funny number",69,player.getPieces());
    }
}