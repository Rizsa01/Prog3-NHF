package Game;

import java.io.Serializable;

/**
 * This class represents the game board.
 * It implements Serializable interface to support serialization.
 */
public class Board implements Serializable {
    protected Position[] boardPositions;

    /**
     * Constructor for the Board class.
     * Initializes the board positions.
     */
    public Board() {
        boardPositions = new Position[24];
        initBoard();
    }

    /**
     * Method to get a position on the board.
     * @param posIndex The index of the position.
     * @return The position at the given index.
     */
    public Position getPosition(int posIndex) {
        return boardPositions[posIndex];
    }

    /**
     * Method to check if a position on the board is available.
     * @param posIndex The index of the position.
     * @return True if the position is not occupied, false otherwise.
     */
    public boolean positionIsAvailable(int posIndex) {
        return !boardPositions[posIndex].isOccupied();
    }


    /**
     * This method sets a position on the board as occupied by a player.
     * @param posIndex The index of the position to be set as occupied.
     * @param player The player (Token) to occupy the position.
     */
    public void setPositionAsPlayer(int posIndex, Token player) {
        boardPositions[posIndex].setAsOccupied(player);
    }

    /**
     * This private method initializes the game board by creating Position objects and establishing adjacency relationships.
     * It also sets up mill-formation relationships for the positions on the board.
     */
    private void initBoard() {
        for(int i = 0; i < 24; i++) {
            boardPositions[i] = new Position(i);
        }
        // outer square
        boardPositions[0].addAdjacentIndex(1,9);
        boardPositions[1].addAdjacentIndex(0,2,4);
        boardPositions[2].addAdjacentIndex(1,14);
        boardPositions[9].addAdjacentIndex(0,10,21);
        boardPositions[14].addAdjacentIndex(2,13,23);
        boardPositions[21].addAdjacentIndex(9,22);
        boardPositions[22].addAdjacentIndex(19,21,23);
        boardPositions[23].addAdjacentIndex(14,22);
        // middle square
        boardPositions[3].addAdjacentIndex(4,10);
        boardPositions[4].addAdjacentIndex(1,3,5,7);
        boardPositions[5].addAdjacentIndex(4,13);
        boardPositions[10].addAdjacentIndex(3,9,11,18);
        boardPositions[13].addAdjacentIndex(5,12,14,20);
        boardPositions[18].addAdjacentIndex(10,19);
        boardPositions[19].addAdjacentIndex(16,18,20,22);
        boardPositions[20].addAdjacentIndex(13,19);
        // inner square
        boardPositions[6].addAdjacentIndex(7,11);
        boardPositions[7].addAdjacentIndex(4,6,8);
        boardPositions[8].addAdjacentIndex(7,12);
        boardPositions[11].addAdjacentIndex(6,10,15);
        boardPositions[12].addAdjacentIndex(8,13,17);
        boardPositions[15].addAdjacentIndex(11,16);
        boardPositions[16].addAdjacentIndex(15,17,19);
        boardPositions[17].addAdjacentIndex(12,16);

        // outer square
        boardPositions[0].addMillAdjacentIndex(1,9,3);
        boardPositions[1].addMillAdjacentIndex(0,2,4);
        boardPositions[2].addMillAdjacentIndex(1,14,5);
        boardPositions[9].addMillAdjacentIndex(0,10,21);
        boardPositions[14].addMillAdjacentIndex(2,13,23);
        boardPositions[21].addMillAdjacentIndex(9,22,18);
        boardPositions[22].addMillAdjacentIndex(19,21,23);
        boardPositions[23].addMillAdjacentIndex(14,22,20);
        // middle square
        boardPositions[3].addMillAdjacentIndex(4,10,0,6);
        boardPositions[4].addMillAdjacentIndex(1,3,5,7,6,8);
        boardPositions[5].addMillAdjacentIndex(4,13,2,8);
        boardPositions[10].addMillAdjacentIndex(3,9,11,18,6,15);
        boardPositions[13].addMillAdjacentIndex(5,12,14,20,8,17);
        boardPositions[18].addMillAdjacentIndex(10,19,21,15);
        boardPositions[19].addMillAdjacentIndex(16,18,20,22,15,17);
        boardPositions[20].addMillAdjacentIndex(13,19,17,23);
        // inner square
        boardPositions[6].addMillAdjacentIndex(7,11,3,4,10);
        boardPositions[7].addMillAdjacentIndex(4,6,8);
        boardPositions[8].addMillAdjacentIndex(7,12,4,5,13);
        boardPositions[11].addMillAdjacentIndex(6,10,15);
        boardPositions[12].addMillAdjacentIndex(8,13,17);
        boardPositions[15].addMillAdjacentIndex(11,16,10,18,19);
        boardPositions[16].addMillAdjacentIndex(15,17,19);
        boardPositions[17].addMillAdjacentIndex(12,16,19,20,13);
    }

    /**
     * This method returns the array of Position objects representing the game board positions.
     * @return The array of Position objects.
     */
    public Position[] getBoardPositions() {
        return boardPositions;
    }
}
