package Test;

import Game.Game;
import Game.Token;
import Game.MillContainer;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This class contains JUnit tests for the Game class.
 */
public class GameTest {
    ArrayList<MillContainer> mills = new ArrayList<MillContainer>();
    ArrayList< MillContainer > millsHelper = new ArrayList<MillContainer>();

    /**
     * Test case for the hasMill method in the Game class.
     */
    @Test
    public void TestHasMill() {
        Game game = new Game(Token.Player2);
        game.getBoard().setPositionAsPlayer(0,game.getPlayer1().getPlayerToken());
        game.getBoard().setPositionAsPlayer(1,game.getPlayer1().getPlayerToken());
        game.getBoard().setPositionAsPlayer(2,game.getPlayer1().getPlayerToken());

        assertEquals("Created a mill in the upper row", true, game.hasMill(game.getBoard(), game.getCurrentTurn()));
    }

    /**
     * Test case for the deletePiece method in the Game class.
     */
    @Test
    public void TestDeletePiece() {
        Game game = new Game(Token.Player2);
        game.getBoard().setPositionAsPlayer(0,game.getPlayer1().getPlayerToken());

        assertEquals("checks if the piece is really created", game.getPlayer1().getPlayerToken(), game.getBoard().getPosition(0).getOccupiedBy());
        game.deletePiece(game.getBoard(),game.getPlayer2(),0,game);
        assertEquals("created then deleted a white piece at the upper left corner",Token.noplayer, game.getBoard().getPosition(0).getOccupiedBy());
    }

    /**
     * Test case for the place method in the Game class.
     */
    @Test
    public void Testplace() {
        Game game = new Game(Token.Player2);
        game.place(0,Token.Player2,game, game.getPlayer2());
        assertEquals("pieces decreased", 8, game.getPlayer2().getPieces());
        assertEquals("piecesonboard increased",1,game.getPlayer2().getPiecesOnBoard());
        assertEquals("position is occupied by the player",game.getPlayer2().getPlayerToken(),game.getBoard().getPosition(0).getOccupiedBy());
    }

    /**
     * Test case for the canMove method in the Game class.
     */
    @Test
    public void TestCanMove() {
        Game game = new Game(Token.Player2);
        game.place(0,Token.Player2,game, game.getPlayer2());
        game.canMove(game,0,1,game.getPlayer2());
        assertEquals("where it moved from become empty",Token.noplayer,game.getBoard().getPosition(0).getOccupiedBy());
        assertEquals("it moved where it supposed to",game.getPlayer2().getPlayerToken(),game.getBoard().getPosition(1).getOccupiedBy());
    }
}