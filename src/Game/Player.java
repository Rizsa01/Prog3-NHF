package Game;

import java.io.Serializable;

/**
 * Represents a player in the Nine Men's Morris game.
 * Implements Serializable to allow for object serialization.
 */
public class Player  implements Serializable {
    private int pieces;
    private int piecesOnBoard;
    private boolean specialPieceOnBoard;
    private Token playerToken;
    private int gameState;
    private boolean selectedPieceIsNormal = true;
    private boolean selectedPieceIsSpecial = false;

    /**
     * Default constructor for the Player class.
     * Initializes the player with default values for pieces, pieces on the board, special piece presence,
     * and sets the initial game state.
     */
    public Player () {
        pieces = 9;
        piecesOnBoard = 0;
        specialPieceOnBoard = false;
        gameState = 1;
    }

    /**
     * This method returns a boolean indicating whether the selected piece is a normal piece.
     * @return True if the selected piece is a normal piece, false otherwise.
     */
    public boolean isSelectedPieceIsNormal() {
        return selectedPieceIsNormal;
    }

    /**
     * Sets the flag indicating whether the selected piece is a normal piece.
     * @param selectedPieceIsNormal True if the selected piece is a normal piece, false otherwise.
     */
    public void setSelectedPieceIsNormal(boolean selectedPieceIsNormal) {
        this.selectedPieceIsNormal = selectedPieceIsNormal;
    }

    /**
     * This method returns a boolean indicating whether the selected piece is a special piece.
     * @return True if the selected piece is a special piece, false otherwise.
     */
    public boolean isSelectedPieceIsSpecial() {
        return selectedPieceIsSpecial;
    }

    /**
     * Sets the flag indicating whether the selected piece is a special piece.
     * @param selectedPieceIsSpecial True if the selected piece is a special piece, false otherwise.
     */
    public void setSelectedPieceIsSpecial(boolean selectedPieceIsSpecial) {
        this.selectedPieceIsSpecial = selectedPieceIsSpecial;
    }

    /**
     * This method returns the current game state.
     * @return The current game state represented as an integer.
     */
    public int getGameState() {
        return gameState;
    }

    /**
     * Sets the current game state to the specified value.
     * @param gameState The new game state to be set.
     */
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    /**
     * This method returns the current number of pieces held by the player.
     * @return The current number of pieces.
     */
    public int getPieces() {
        return pieces;
    }

    /**
     * This method returns the current number of pieces placed on the game board.
     * @return The current number of pieces on the game board.
     */
    public int getPiecesOnBoard() {
        return piecesOnBoard;
    }

    /**
     * This method returns the player's token.
     * @return The player's token.
     */
    public Token getPlayerToken() {
        return playerToken;
    }

    /**
     * Sets the player's token to the specified token.
     * @param playerToken The token to be set for the player.
     */
    public void setPlayerToken(Token playerToken) {
        this.playerToken = playerToken;
    }

    /**
     * This method returns a boolean indicating whether a special piece is currently on the game board.
     * @return True if a special piece is on the board, false otherwise.
     */
    public boolean isSpecialPieceOnBoard() {
        return specialPieceOnBoard;
    }

    /**
     * Sets the number of pieces held by the player to the specified value.
     * @param pieces The new number of pieces to be set.
     */
    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    /**
     * Sets the number of pieces placed on the game board to the specified value.
     * @param piecesOnBoard The new number of pieces on the game board to be set.
     */
    public void setPiecesOnBoard(int piecesOnBoard) {
        this.piecesOnBoard = piecesOnBoard;
    }

    /**
     * Sets the presence of a special piece on the game board to the specified value.
     * @param specialPieceOnBoard True if a special piece is on the board, false otherwise.
     */
    public void setSpecialPieceOnBoard(boolean specialPieceOnBoard) {
        this.specialPieceOnBoard = specialPieceOnBoard;
    }

}
