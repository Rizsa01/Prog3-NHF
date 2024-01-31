package Game;

import java.io.Serializable;
import java.util.*;

/**
 * This class represents the game.
 * It implements Serializable interface to support serialization.
 */
public class Game implements Serializable {

    public ArrayList<MillContainer> mill = new ArrayList<MillContainer>();

    public MillHelper millHelper = new MillHelper();

    private Player currentTurn;
    private Player player1;
    private Player player2;
    private Board board;

    /**
     * Constructor for the Game class.
     * Initializes two players, assigns tokens to them, sets the initial turn to player 1, and creates the game board.
     * @param token The token to be assigned to player 2.
     */
    public Game(Token token){
        player1 = new Player();
        player1.setPlayerToken(Token.Player1);
        player2 = new Player();
        player2.setPlayerToken(token);
        currentTurn = player1;
        board = new Board();
    }

    /**
     * Initializes the mill with predefined mill containers.
     * Each mill container represents a combination of positions forming a mill on the game board.
     */
    public void initMill(){
        mill.add(new MillContainer(0,1,2));
        mill.add(new MillContainer(0,3,6));
        mill.add(new MillContainer(0,9,21));
        mill.add(new MillContainer(1,4,7));
        mill.add(new MillContainer(2,5,8));
        mill.add(new MillContainer(2,14,23));
        mill.add(new MillContainer(3,4,5));
        mill.add(new MillContainer(3,10,18));
        mill.add(new MillContainer(5,13,20));
        mill.add(new MillContainer(6,7,8));
        mill.add(new MillContainer(6,11,15));
        mill.add(new MillContainer(8,12,17));
        mill.add(new MillContainer(9,10,11));
        mill.add(new MillContainer(12,13,14));
        mill.add(new MillContainer(15,18,21));
        mill.add(new MillContainer(15,16,17));
        mill.add(new MillContainer(16,19,22));
        mill.add(new MillContainer(17,20,23));
        mill.add(new MillContainer(18,19,20));
        mill.add(new MillContainer(21,22,23));
        mill.add(new MillContainer(4,6,10));
        mill.add(new MillContainer(4,8,13));
        mill.add(new MillContainer(10,15,19));
        mill.add(new MillContainer(13,17,19));
    }

    /**
     * Checks if the current player has formed a mill on the game board.
     * If a mill is formed, it updates the millHelper data structures to track mills and returns true.
     * @param board The game board.
     * @param currentTurn The current player's turn.
     * @return True if the current player has formed a mill, false otherwise.
     */
    public boolean hasMill(Board board, Player currentTurn) {

        initMill();
        boolean tmp = false;

        for(int k = 0; k < 24; k++) {
            for (int j = 0; j < k; j++) {
                for (int i = 0; i < j; i++) {
                    for(MillContainer m : mill){
                        MillContainer wasMill = new MillContainer(i,j,k);
                        if (i!=j && i!=k && j!=k && board.getBoardPositions()[i].isOccupied() && board.getBoardPositions()[j].isOccupied() && board.getBoardPositions()[k].isOccupied()) {
                            if (board.getBoardPositions()[i].getOccupiedBy() == board.getBoardPositions()[j].getOccupiedBy() && board.getBoardPositions()[i].getOccupiedBy() == board.getBoardPositions()[k].getOccupiedBy() && board.getBoardPositions()[j].getOccupiedBy() == board.getBoardPositions()[k].getOccupiedBy() ){
                                if (m.getA() == i && m.getB() == j && m.getC() == k ) {
                                    if ((!board.getBoardPositions()[i].isInMill() || !board.getBoardPositions()[j].isInMill() || !board.getBoardPositions()[k].isInMill()) && !millHelper.mills.contains(wasMill) && !millHelper.helper.contains(wasMill) && board.getBoardPositions()[i].getOccupiedBy() == currentTurn.getPlayerToken()) {
                                        tmp = true;
                                    }

                                    if(millHelper.mills.isEmpty()){
                                        millHelper.helper.add(wasMill);
                                    }else {
                                        for(MillContainer a : millHelper.mills){
                                            if(a.getA() == i && a.getB() == j && a.getC() == k){
                                            } else if(!millHelper.helper.contains(wasMill)) {
                                                millHelper.helper.add(wasMill);
                                            }
                                        }
                                    }
                                    board.getBoardPositions()[i].setInMill(true);
                                    board.getBoardPositions()[j].setInMill(true);
                                    board.getBoardPositions()[k].setInMill(true);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        Iterator<MillContainer> iterator = millHelper.helper.iterator();
        int counter = 0;
        if(!millHelper.helper.isEmpty()){

            while (iterator.hasNext()) {
                MillContainer m = iterator.next();
                for (MillContainer mi : millHelper.mills) {
                    if (m.getA() == mi.getA() && m.getB() == mi.getB() && m.getC() == mi.getC() && currentTurn == getCurrentTurn()){
                        counter++;
                    }
                }
            }

            if(counter == millHelper.helper.size()){
                tmp = false;
            }

            millHelper.mills.clear();
            millHelper.mills.addAll(millHelper.helper);
            millHelper.helper.clear();


        } else {
            millHelper.mills.clear();
            tmp = false;
        }


        return tmp;
    }

    /**
     * Deletes a piece from the board and updates the player's piece count.
     * If the deleted piece was in a mill, it updates mill information on the board.
     * @param board The game board.
     * @param player The player who is deleting the piece.
     * @param index The index of the position on the board where the piece is deleted.
     * @param game The current game.
     */
    public void deletePiece(Board board, Player player, int index, Game game){
        board.getPosition(index).setAsOccupied(Token.noplayer);
        board.getPosition(index).setAsUnoccupied();
        board.getPosition(index).setInMill(false);
        player.setPiecesOnBoard(player.getPiecesOnBoard()-1);
        if(board.getPosition(index).isHasSpecial()){
            for(int i = 0; i < millHelper.mills.size(); i++){

                if(millHelper.mills.get(i).getA() == game.getBoard().getBoardPositions()[index].getIndex()){
                    game.getBoard().getBoardPositions()[millHelper.mills.get(i).getB()].setInMill(false);
                    game.getBoard().getBoardPositions()[millHelper.mills.get(i).getC()].setInMill(false);
                }
                if(millHelper.mills.get(i).getB() == game.getBoard().getBoardPositions()[index].getIndex()){
                    game.getBoard().getBoardPositions()[millHelper.mills.get(i).getA()].setInMill(false);
                    game.getBoard().getBoardPositions()[millHelper.mills.get(i).getC()].setInMill(false);
                }
                if(millHelper.mills.get(i).getC() == game.getBoard().getBoardPositions()[index].getIndex()){
                    game.getBoard().getBoardPositions()[millHelper.mills.get(i).getB()].setInMill(false);
                    game.getBoard().getBoardPositions()[millHelper.mills.get(i).getA()].setInMill(false);
                }
            }
        }
        board.getPosition(index).setHasSpecial(false);
    }

    /**
     * Places a token for the bot on the game board.
     * The bot randomly selects an available position until it successfully places a token.
     * @param game The current game.
     * @return The index of the position where the bot placed its token, or -1 if unsuccessful.
     */
    public int botPlace(Game game){
        boolean ableToPlace = false;
        while (ableToPlace == false){
            Random random = new Random();
            int randomPlace = random.nextInt(24);
            if(game.board.positionIsAvailable(randomPlace)){
                place(randomPlace,Token.bot,game,game.getPlayer2());
                return randomPlace;
            }
        }
        return -1;
    }

    /**
     * Places a token on the game board for the specified player.
     * Checks if the position is available and the player has remaining pieces.
     * If conditions are met, places the token on the board and updates player's piece counts.
     * @param to The index of the position where the token is to be placed.
     * @param token The token to be placed (either Player1 or bot).
     * @param game The current game.
     * @param player The player for whom the token is being placed.
     * @return True if the token is successfully placed, false otherwise.
     */
    public boolean place (int to, Token token, Game game, Player player){
        if (board.positionIsAvailable(to)){
            if(player.getPieces() > 0) {

                player.setPieces(player.getPieces()-1);
                player.setPiecesOnBoard(player.getPiecesOnBoard()+1);
            } else {
                return false;
            }
            board.setPositionAsPlayer(to, token);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Moves a player's token from one position to another on the game board.
     * Handles mill information if the starting position is in a mill.
     * @param game The current game.
     * @param from The index of the position where the token is currently located.
     * @param to The index of the position where the token is to be moved.
     * @param player The player making the move.
     */
    public void canMove(Game game, int from, int to, Player player){
        if(game.getBoard().getBoardPositions()[from].isInMill()){
            for(int i = 0; i < millHelper.mills.size(); i++){

                if(millHelper.mills.get(i).getA() == game.getBoard().getBoardPositions()[from].getIndex()){
                    game.getBoard().getBoardPositions()[millHelper.mills.get(i).getB()].setInMill(false);
                    game.getBoard().getBoardPositions()[millHelper.mills.get(i).getC()].setInMill(false);
                }
                if(millHelper.mills.get(i).getB() == game.getBoard().getBoardPositions()[from].getIndex()){
                    game.getBoard().getBoardPositions()[millHelper.mills.get(i).getA()].setInMill(false);
                    game.getBoard().getBoardPositions()[millHelper.mills.get(i).getC()].setInMill(false);
                }
                if(millHelper.mills.get(i).getC() == game.getBoard().getBoardPositions()[from].getIndex()){
                    game.getBoard().getBoardPositions()[millHelper.mills.get(i).getB()].setInMill(false);
                    game.getBoard().getBoardPositions()[millHelper.mills.get(i).getA()].setInMill(false);
                }
            }
        }
        game.getBoard().getBoardPositions()[from].setAsUnoccupied();
        game.getBoard().getBoardPositions()[from].setAsOccupied(Token.noplayer);
        game.getBoard().getBoardPositions()[to].setAsOccupied(player.getPlayerToken());
        game.getBoard().getBoardPositions()[from].setInMill(false);
    }

    /**
     * Gets the player whose turn it currently is.
     * @return The player whose turn it currently is.
     */
    public Player getCurrentTurn() {
        return currentTurn;
    }

    /**
     * Sets the player whose turn it currently is in the game.
     * @param currentTurn The player whose turn it should be set to.
     */
    public void setCurrentTurn(Player currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Gets the player representing Player 1 in the game.
     * @return The player representing Player 1.
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Gets the player representing Player 2 in the game.
     * @return The player representing Player 2.
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Sets the player representing Player 2 in the game.
     * @param player2 The player object representing Player 2.
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * Gets the game board object.
     * @return The game board object.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets the game board object.
     * @param board The game board object to be set.
     */
    public void setBoard(Board board) {
        this.board = board;
    }
}
