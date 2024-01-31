package Game;

import java.io.Serializable;

/**
 * This class represents the positions inside the game.
 * It implements Serializable interface to support serialization.
 */
public class Position implements Serializable {
    private boolean isOccupied;
    private int index;
    private Token occupiedBy;
    private int[] moveAdjacentIndexes;
    private int[] millAdjacentIndexes;
    private boolean inMill;
    private boolean hasSpecial;

    /**
     * Checks if the current game position is part of a mill.
     * @return True if the position is in a mill, false otherwise.
     */
    public boolean isInMill() {
        return inMill;
    }

    /**
     * Sets the flag indicating whether the current game position is part of a mill.
     * @param a The boolean value to set for the mill flag.
     */
    public void setInMill(boolean a) {
        this.inMill = a;
    }


    /**
     * Constructor for the Position class.
     * Initializes the position with default values.
     * @param position The index of the position.
     */
    public Position (int position){
        isOccupied = false;
        this.index = position;
        occupiedBy = Token.noplayer;
        inMill = false;
        hasSpecial = false;
    }

    /**
     * This method returns a boolean indicating whether the position has a special piece.
     * @return True if the position has a special piece, false otherwise.
     */
    public boolean isHasSpecial() {
        return hasSpecial;
    }

    /**
     * Sets the flag indicating whether the position has a special piece.
     * @param hasSpecial True if the position has a special piece, false otherwise.
     */
    public void setHasSpecial(boolean hasSpecial) {
        this.hasSpecial = hasSpecial;
    }

    /**
     * This method returns a boolean indicating whether the position is currently occupied by a player.
     * @return True if the position is occupied, false otherwise.
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Marks the position as occupied by a specified player.
     * @param player The token representing the player who occupies the position.
     */
    public void setAsOccupied(Token player) {
        isOccupied = true;
        occupiedBy = player;
    }

    /**
     * Marks the position as unoccupied.
     * Resets the isOccupied flag to false and sets the occupiedBy token to no player.
     */
    public void setAsUnoccupied(){
        isOccupied = false;
        occupiedBy = Token.noplayer;
    }

    /**
     * This method returns the index of the position on the game board.
     * @return The index of the position.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Adds two adjacent indexes to the position for movement purposes on the game board.
     * @param pos1 The index of the first adjacent position.
     * @param pos2 The index of the second adjacent position.
     */
    public void addAdjacentIndex(int pos1, int pos2) {
        moveAdjacentIndexes = new int[2];
        moveAdjacentIndexes[0] = pos1;
        moveAdjacentIndexes[1] = pos2;
    }

    /**
     * Adds three adjacent indexes to the position for movement purposes on the game board.
     * @param pos1 The index of the first adjacent position.
     * @param pos2 The index of the second adjacent position.
     * @param pos3 The index of the third adjacent position.
     */
    public void addAdjacentIndex(int pos1, int pos2, int pos3) {
        moveAdjacentIndexes = new int[3];
        moveAdjacentIndexes[0] = pos1;
        moveAdjacentIndexes[1] = pos2;
        moveAdjacentIndexes[2] = pos3;
    }

    /**
     * Adds four adjacent indexes to the position for movement purposes on the game board.
     * @param pos1 The index of the first adjacent position.
     * @param pos2 The index of the second adjacent position.
     * @param pos3 The index of the third adjacent position.
     * @param pos4 The index of the fourth adjacent position.
     */
    public void addAdjacentIndex(int pos1, int pos2, int pos3, int pos4) {
        moveAdjacentIndexes = new int[4];
        moveAdjacentIndexes[0] = pos1;
        moveAdjacentIndexes[1] = pos2;
        moveAdjacentIndexes[2] = pos3;
        moveAdjacentIndexes[3] = pos4;
    }

    /**
     * Adds two adjacent indexes to the position for forming a mill on the game board.
     * @param pos1 The index of the first mill-adjacent position.
     * @param pos2 The index of the second mill-adjacent position.
     */
    public void addMillAdjacentIndex(int pos1, int pos2) {
        millAdjacentIndexes = new int[2];
        millAdjacentIndexes[0] = pos1;
        millAdjacentIndexes[1] = pos2;
    }

    /**
     * Adds three adjacent indexes to the position for forming a mill on the game board.
     * @param pos1 The index of the first mill-adjacent position.
     * @param pos2 The index of the second mill-adjacent position.
     * @param pos3 The index of the third mill-adjacent position.
     */
    public void addMillAdjacentIndex(int pos1, int pos2, int pos3) {
        millAdjacentIndexes = new int[3];
        millAdjacentIndexes[0] = pos1;
        millAdjacentIndexes[1] = pos2;
        millAdjacentIndexes[2] = pos3;
    }

    /**
     * Adds four adjacent indexes to the position for forming a mill on the game board.
     * @param pos1 The index of the first mill-adjacent position.
     * @param pos2 The index of the second mill-adjacent position.
     * @param pos3 The index of the third mill-adjacent position.
     * @param pos4 The index of the fourth mill-adjacent position.
     */
    public void addMillAdjacentIndex(int pos1, int pos2, int pos3, int pos4) {
        millAdjacentIndexes = new int[4];
        millAdjacentIndexes[0] = pos1;
        millAdjacentIndexes[1] = pos2;
        millAdjacentIndexes[2] = pos3;
        millAdjacentIndexes[3] = pos4;
    }

    /**
     * Adds five adjacent indexes to the position for forming a mill on the game board.
     * @param pos1 The index of the first mill-adjacent position.
     * @param pos2 The index of the second mill-adjacent position.
     * @param pos3 The index of the third mill-adjacent position.
     * @param pos4 The index of the fourth mill-adjacent position.
     * @param pos5 The index of the fifth mill-adjacent position.
     */
    public void addMillAdjacentIndex(int pos1, int pos2, int pos3, int pos4, int pos5) {
        millAdjacentIndexes = new int[5];
        millAdjacentIndexes[0] = pos1;
        millAdjacentIndexes[1] = pos2;
        millAdjacentIndexes[2] = pos3;
        millAdjacentIndexes[3] = pos4;
        millAdjacentIndexes[4] = pos5;
    }

    /**
     * Adds six adjacent indexes to the position for forming a mill on the game board.
     * @param pos1 The index of the first mill-adjacent position.
     * @param pos2 The index of the second mill-adjacent position.
     * @param pos3 The index of the third mill-adjacent position.
     * @param pos4 The index of the fourth mill-adjacent position.
     * @param pos5 The index of the fifth mill-adjacent position.
     * @param pos6 The index of the sixth mill-adjacent position.
     */
    public void addMillAdjacentIndex(int pos1, int pos2, int pos3, int pos4, int pos5, int pos6) {
        millAdjacentIndexes = new int[6];
        millAdjacentIndexes[0] = pos1;
        millAdjacentIndexes[1] = pos2;
        millAdjacentIndexes[2] = pos3;
        millAdjacentIndexes[3] = pos4;
        millAdjacentIndexes[4] = pos5;
        millAdjacentIndexes[5] = pos6;
    }

    /**
     * This method returns the token representing the player who occupies the position.
     * @return The token representing the player who occupies the position.
     */
    public Token getOccupiedBy() {
        return occupiedBy;
    }

    /**
     * Sets the token representing the player who occupies the position.
     * @param occupiedBy The token to be set for the player who occupies the position.
     */
    public void setOccupiedBy(Token occupiedBy) {
        this.occupiedBy = occupiedBy;
    }

    /**
     * This method returns an array of adjacent indexes for movement purposes on the game board.
     * @return An array of adjacent indexes for movement purposes.
     */
    public int[] getMoveAdjacentIndex() {
        return moveAdjacentIndexes;
    }

}
