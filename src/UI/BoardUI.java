package UI;

import Game.Game;
import Game.Player;
import Game.MillContainer;
import Game.Token;

import IO.Save;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class creates the UI for the game.
 * It implements Serializable interface to support serialization.
 */
public class BoardUI implements Serializable {
    public static JLabel[][] circleMatrix;
    public JButton[] buttonList;
    public JLayeredPane lpane;
    public JLabel label;
    public JComboBox<String> comboBox1;
    public JComboBox<String> comboBox2;
    public boolean ButtonListenerEnable = true;
    public boolean moveEnable = true;
    public boolean flyEnable = true;
    public static boolean botEnable = true;

    private static Game game;

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        BoardUI.game = game;
    }

    /**
     * Constructor for the BoardUI class, representing the user interface for the game board.
     * Initializes arrays for circleMatrix and buttonList, and sets up the game using the specified JFrame and Token.
     * @param frame The JFrame for the game UI.
     * @param token The initial Token for the game.
     */
    public BoardUI(JFrame frame, Token token){
        circleMatrix = new JLabel[24][4];
        buttonList = new JButton[24];
        game = new Game(token);
        frame.getContentPane().removeAll();
        this.Init(frame);
    }

    /**
     * Constructor for the BoardUI class, representing the user interface for the game board.
     * Initializes arrays for circleMatrix and buttonList, and sets up the game using the specified JFrame and Game.
     * @param frame The JFrame for the game UI.
     * @param game The Game object representing the current state of the game.
     */
    public BoardUI(JFrame frame, Game game){
        circleMatrix = new JLabel[24][4];
        buttonList = new JButton[24];
        this.game = game;
        frame.getContentPane().removeAll();
        this.Init(frame);

    }

    /**
     * Initializes the game board UI based on the current state of the game.
     * Updates the visibility of pieces and sets labels accordingly.
     * @param frame The JFrame for the game UI.
     */
    public void Init(JFrame frame) {
        inic(frame);
        // Iterate over the board positions
        for (int i = 0; i < game.getBoard().getBoardPositions().length; i++) {
            // Check if the position is occupied
            if (game.getBoard().getBoardPositions()[i].isOccupied()) {
                // Check which player occupies the position
                if (game.getBoard().getBoardPositions()[i].getOccupiedBy() == Token.Player1) {
                    // Check if the position has a special piece
                    if (game.getBoard().getBoardPositions()[i].isHasSpecial()) {
                        circleMatrix[i][2].setVisible(true); // Show the special piece for Player 1
                    } else {
                        circleMatrix[i][0].setVisible(true); // Show the normal piece for Player 1
                    }
                } else if (game.getBoard().getBoardPositions()[i].getOccupiedBy() == Token.Player2 || game.getBoard().getBoardPositions()[i].getOccupiedBy() == Token.bot) {
                    // Check if the position has a special piece
                    if (game.getBoard().getBoardPositions()[i].isHasSpecial()) {
                        circleMatrix[i][3].setVisible(true); // Show the special piece for Player 2
                    } else {
                        circleMatrix[i][1].setVisible(true); // Show the normal piece for Player 2
                    }
                }
            }
        }

        comboBoxRefresh(game.getPlayer1(), game);
        comboBoxRefreshNormal(game.getPlayer1(), game);
        comboBoxRefresh(game.getPlayer2(), game);
        comboBoxRefreshNormal(game.getPlayer2(), game);

        if (game.getCurrentTurn() == game.getPlayer1()) {
            label.setText("White's turn!");
        } else {
            label.setText("Black's turn!");
        }

    }

    /**
     * Additional initialization for the game board UI. (aka. does everything)
     * Sets up background image, buttons, save button, and error labels.
     * @param frame The JFrame for the game UI.
     */
    public void inic(JFrame frame){
        ImageIcon icon = new ImageIcon(Menu.class.getResource("en_280.png"));
        java.awt.Image image = icon.getImage().getScaledInstance(600, 600, java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(image);
        JLabel labelImage = new JLabel(scaledIcon);
        labelImage.setBounds(0,0,1000,800);
        lpane = new JLayeredPane();
        lpane.add(labelImage, 1, 0);

        specialPieceInic(frame);

        JButton save = new JButton("Save the gamestate");
        save.setVisible(true);
        save.setBounds(600,20,100,50);
        lpane.add(save);

        JTextField fileName = new JTextField();
        fileName.setVisible(true);
        fileName.setBounds(700,20,200,50);
        lpane.add(fileName);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Save.toFile(Paths.get(fileName.getText()));
            }
        });

        for(int i = 0; i < 24; i++){
                ImageIcon iconw = new ImageIcon(Menu.class.getResource("white2.png"));
                java.awt.Image imagew = iconw.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                ImageIcon scaledIconw = new ImageIcon(imagew);
                JLabel labelw = new JLabel(scaledIconw);
                lpane.add(labelw, 2, 0);

                ImageIcon iconb = new ImageIcon(Menu.class.getResource("black2.png"));
                java.awt.Image imageb = iconb.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                ImageIcon scaledIconb = new ImageIcon(imageb);
                JLabel labelb = new JLabel(scaledIconb);
                lpane.add(labelb, 2, 0);

                ImageIcon siconw = new ImageIcon(Menu.class.getResource("spec_white2.png"));
                java.awt.Image simagew = siconw.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                ImageIcon sscaledIconw = new ImageIcon(simagew);
                JLabel slabelw = new JLabel(sscaledIconw);
                lpane.add(slabelw, 2, 0);

                ImageIcon biconw = new ImageIcon(Menu.class.getResource("spec_black2.png"));
                java.awt.Image bimagew = biconw.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                ImageIcon bscaledIconw = new ImageIcon(bimagew);
                JLabel blabelw = new JLabel(bscaledIconw);
                lpane.add(blabelw, 2, 0);

                circleMatrix[i][0] = labelw;
                circleMatrix[i][1] = labelb;
                circleMatrix[i][2] = slabelw;
                circleMatrix[i][3] = blabelw;
                circleMatrix[i][0].setVisible(false);
                circleMatrix[i][1].setVisible(false);
                circleMatrix[i][2].setVisible(false);
                circleMatrix[i][3].setVisible(false);


                JButton button = new JButton();
                buttonList[i] =button;
                lpane.add(button, 0, 0);
        }
        for(int i = 0; i < 4; i++){
            circleMatrix[0][i].setBounds(208, 110, 40 ,40);
            circleMatrix[1][i].setBounds(480, 110, 40 ,40);
            circleMatrix[2][i].setBounds(752, 110, 40 ,40);


            circleMatrix[3][i].setBounds(298, 200, 40 ,40);
            circleMatrix[4][i].setBounds(480, 200, 40 ,40);
            circleMatrix[5][i].setBounds(662, 200, 40 ,40);

            circleMatrix[6][i].setBounds(390, 290, 40 ,40);
            circleMatrix[7][i].setBounds(480, 290, 40 ,40);
            circleMatrix[8][i].setBounds(570, 290, 40 ,40);

            circleMatrix[9][i].setBounds(208, 382, 40 ,40);
            circleMatrix[10][i].setBounds(298, 382, 40 ,40);
            circleMatrix[11][i].setBounds(390, 382, 40 ,40);
            circleMatrix[12][i].setBounds(570, 382, 40 ,40);
            circleMatrix[13][i].setBounds(662, 382, 40 ,40);
            circleMatrix[14][i].setBounds(752, 382, 40 ,40);

            circleMatrix[15][i].setBounds(390, 471, 40 ,40);
            circleMatrix[16][i].setBounds(480, 471, 40 ,40);
            circleMatrix[17][i].setBounds(570, 471, 40 ,40);

            circleMatrix[18][i].setBounds(298, 560, 40 ,40);
            circleMatrix[19][i].setBounds(480, 560, 40 ,40);
            circleMatrix[20][i].setBounds(662, 560, 40 ,40);

            circleMatrix[21][i].setBounds(208, 652, 40 ,40);
            circleMatrix[22][i].setBounds(480, 652, 40 ,40);
            circleMatrix[23][i].setBounds(752, 652, 40 ,40);
        }

        buttonList[0].setBounds(208, 110, 40 ,40);
        buttonList[1].setBounds(480, 110, 40 ,40);
        buttonList[2].setBounds(752, 110, 40 ,40);

        buttonList[3].setBounds(298, 200, 40 ,40);
        buttonList[4].setBounds(480, 200, 40 ,40);
        buttonList[5].setBounds(662, 200, 40 ,40);

        buttonList[6].setBounds(390, 290, 40 ,40);
        buttonList[7].setBounds(480, 290, 40 ,40);
        buttonList[8].setBounds(570, 290, 40 ,40);

        buttonList[9].setBounds(208, 382, 40 ,40);
        buttonList[10].setBounds(298, 382, 40 ,40);
        buttonList[11].setBounds(390, 382, 40 ,40);
        buttonList[12].setBounds(570, 382, 40 ,40);
        buttonList[13].setBounds(662, 382, 40 ,40);
        buttonList[14].setBounds(752, 382, 40 ,40);

        buttonList[15].setBounds(390, 471, 40 ,40);
        buttonList[16].setBounds(480, 471, 40 ,40);
        buttonList[17].setBounds(570, 471, 40 ,40);

        buttonList[18].setBounds(298, 560, 40 ,40);
        buttonList[19].setBounds(480, 560, 40 ,40);
        buttonList[20].setBounds(662, 560, 40 ,40);

        buttonList[21].setBounds(208, 652, 40 ,40);
        buttonList[22].setBounds(480, 652, 40 ,40);
        buttonList[23].setBounds(752, 652, 40 ,40);

        label = new JLabel("White's turn!");
        label.setVisible(true);
        label.setBounds(440,10,300,100);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        frame.add(label);

        JLabel errorText = new JLabel("Not your turn!");
        errorText.setVisible(false);
        errorText.setBounds(700,10,400,100);
        errorText.setFont(new Font("Arial", Font.PLAIN, 30));
        errorText.setForeground(Color.red);
        frame.add(errorText);

        JLabel errorText2 = new JLabel("That place is not empty!");
        errorText2.setVisible(false);
        errorText2.setBounds(100,10,400,100);
        errorText2.setFont(new Font("Arial", Font.PLAIN, 30));
        errorText2.setForeground(Color.red);
        frame.add(errorText2);
        if(game.getPlayer2().getPlayerToken() == Token.bot){
            botHandler(game);
        }

        for (int i = 0; i < 24; i++) {
            final int index = i;

            buttonList[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                      if (ButtonListenerEnable) {
                          if (game.getPlayer1().getPieces() == 0 && game.getPlayer1().getGameState() == 1) {
                              game.getPlayer1().setGameState(2);
                          }
                          if (game.getPlayer1().getPieces() == 0 && game.getPlayer1().getGameState() == 1 && !game.getPlayer1().isSpecialPieceOnBoard()){
                              comboBoxRefreshNormal(game.getPlayer1(),game);
                          }
                          if (game.getPlayer2().getPieces() == 0 && game.getPlayer2().getGameState() == 1 && !game.getPlayer2().isSpecialPieceOnBoard() && game.getPlayer2().getPlayerToken() != Token.bot){
                              comboBoxRefreshNormal(game.getPlayer2(),game);
                          }
                          if (game.getPlayer2().getPieces() == 0 && game.getPlayer2().getGameState() == 1) {
                              game.getPlayer2().setGameState(2);
                          }
                          if (game.getPlayer1().getGameState() == 2 && game.getPlayer1().getPiecesOnBoard() == 3) {
                              game.getPlayer1().setGameState(3);
                          }
                          if (game.getPlayer2().getGameState() == 2 && game.getPlayer2().getPiecesOnBoard() == 3) {
                              game.getPlayer2().setGameState(3);
                          }
                          //white
                          if (game.getCurrentTurn() == game.getPlayer1()) {
                              switch (game.getPlayer1().getGameState()) {
                                  case 1:
                                      if (game.place(index, game.getPlayer1().getPlayerToken(), game, game.getPlayer1())) {
                                          if(game.getPlayer1().getPieces() == 0 && !game.getPlayer1().isSpecialPieceOnBoard()){
                                              game.getPlayer1().setSelectedPieceIsSpecial(true);
                                          }
                                          if(game.getPlayer1().isSelectedPieceIsSpecial()){
                                              circleMatrix[index][2].setVisible(true);
                                              game.getPlayer1().setSpecialPieceOnBoard(true);
                                              comboBoxRefresh(game.getPlayer1(),game);
                                              game.getPlayer1().setSelectedPieceIsSpecial(false);
                                              game.getBoard().getBoardPositions()[index].setHasSpecial(true);
                                          } else if(game.getPlayer1().isSelectedPieceIsNormal()){
                                              circleMatrix[index][0].setVisible(true);
                                          } if (game.hasMill(game.getBoard(), game.getCurrentTurn())) {
                                              delete(game, game.getPlayer2());
                                          }
                                          label.setText("Black's turn!");
                                          game.setCurrentTurn(game.getPlayer2());
                                      } else {
                                          errorText2.setVisible(true);
                                          Timer timer = new Timer(500, new ActionListener() {
                                              @Override
                                              public void actionPerformed(ActionEvent e) {
                                                  errorText2.setVisible(false); // Hide the label
                                                  ((Timer) e.getSource()).stop(); // Stop the timer
                                              }
                                          });
                                          timer.start();
                                      }

                                      break;
                                  case 2:
                                      if (moveEnable) {
                                          if(game.getBoard().getBoardPositions()[index].isHasSpecial()){
                                              fly(game, game.getPlayer1(), index);
                                          } else {
                                              move(game, game.getPlayer1(), index);
                                          }
                                      }
                                      break;
                                  case 3:
                                      if (flyEnable) {
                                          fly(game, game.getPlayer1(), index);
                                      }
                                      break;
                                  default:
                                      break;
                              }

                              // black
                          } else if (game.getCurrentTurn() == game.getPlayer2() && game.getPlayer2().getPlayerToken() == Token.Player2) {
                              switch (game.getPlayer2().getGameState()) {
                                  case 1:
                                      if (game.place(index, game.getPlayer2().getPlayerToken(), game, game.getPlayer2())) {
                                          if(game.getPlayer2().getPieces() == 0 && !game.getPlayer2().isSpecialPieceOnBoard()){
                                              game.getPlayer2().setSelectedPieceIsSpecial(true);
                                          }
                                          if(game.getPlayer2().isSelectedPieceIsSpecial()){
                                              circleMatrix[index][3].setVisible(true);
                                              game.getPlayer2().setSpecialPieceOnBoard(true);
                                              comboBoxRefresh(game.getPlayer2(),game);
                                              game.getPlayer2().setSelectedPieceIsSpecial(false);
                                              game.getBoard().getBoardPositions()[index].setHasSpecial(true);
                                          } else if(game.getPlayer2().isSelectedPieceIsNormal()){
                                              circleMatrix[index][1].setVisible(true);
                                          }
                                          if (game.hasMill(game.getBoard(), game.getCurrentTurn())) {
                                              delete(game, game.getPlayer1());
                                          }
                                          label.setText("White's turn!");
                                          game.setCurrentTurn(game.getPlayer1());
                                      } else {
                                          errorText2.setVisible(true);
                                          Timer timer = new Timer(500, new ActionListener() {
                                              @Override
                                              public void actionPerformed(ActionEvent e) {
                                                  errorText2.setVisible(false); // Hide the label
                                                  ((Timer) e.getSource()).stop(); // Stop the timer
                                              }
                                          });
                                          timer.start();
                                      }
                                      break;
                                  case 2:
                                      if (moveEnable) {
                                          if(game.getBoard().getBoardPositions()[index].isHasSpecial()){
                                              fly(game, game.getPlayer2(), index);
                                          } else {
                                              move(game, game.getPlayer2(), index);
                                          }
                                      }
                                      break;
                                  case 3:
                                      if (flyEnable) {
                                          fly(game, game.getPlayer2(), index);
                                      }
                                      break;
                                  default:
                                      break;
                              }
                          }
                      } else {
                          errorText.setVisible(true);
                          Timer timer = new Timer(500, new ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent e) {
                                  errorText.setVisible(false); // Hide the label
                                  ((Timer) e.getSource()).stop(); // Stop the timer
                              }
                          });
                          timer.start();
                      }

                      if (game.getCurrentTurn() == game.getPlayer2() && game.getPlayer2().getPlayerToken() == Token.bot) {
                          botTurn(game);
                      }
                      for(int i = 0; i < 24; i++){
                          if(game.getBoard().getPosition(i).isInMill()){
                              System.out.println("pos:" + i);

                          }


                      }
                    System.out.println(" ");
                }
            });
        }


        frame.add(lpane, BorderLayout.CENTER);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        lpane.setVisible(true);
        frame.setVisible(true);
    }

    /**
     * Executes the bot's turn in the game.
     * The bot places or moves its pieces based on its game state.
     * @param game The game object representing the current state of the game.
     */
    public void botTurn(Game game){
        if(botEnable){
            switch (game.getPlayer2().getGameState()) {
                case 1:
                    if(game.getPlayer2().isSelectedPieceIsSpecial()){
                        int index = game.botPlace(game);
                        circleMatrix[index][3].setVisible(true);
                        game.getPlayer2().setSpecialPieceOnBoard(true);
                        comboBoxRefresh(game.getPlayer2(),game);
                        game.getPlayer2().setSelectedPieceIsSpecial(false);
                        game.getBoard().getBoardPositions()[index].setHasSpecial(true);
                    } else if(game.getPlayer2().isSelectedPieceIsNormal()){
                        circleMatrix[game.botPlace(game)][1].setVisible(true);
                    }
                    if (game.hasMill(game.getBoard(), game.getCurrentTurn())) {
                        botDelete(game);
                    }
                    label.setText("White's turn!");
                    game.setCurrentTurn(game.getPlayer1());
                    break;
                case 2:
                    boolean valid = false;
                    while(valid == false){
                        Random random = new Random();
                        int randomNumberFrom = random.nextInt(24);
                        if(circleMatrix[randomNumberFrom][3].isVisible()){
                            if(botFly(game, randomNumberFrom)){
                                valid = true;
                            }
                        } else if (circleMatrix[randomNumberFrom][1].isVisible()){
                            if(botMove(game, randomNumberFrom)){
                                valid = true;
                            }
                        }
                    }
                    break;
                case 3:
                    boolean valid2 = false;
                    while(valid2 == false){
                        Random random = new Random();
                        int randomNumberFrom = random.nextInt(24);
                        if(botFly(game, randomNumberFrom)){
                            valid2 = true;
                        }
                    }

                    break;
                default:
                    break;
            }
        }
        ifWon(game);
    }

    /**
     * Attempts to perform a flying move for the bot.
     * @param game The game object representing the current state of the game.
     * @param randomNumberFrom The randomly chosen position to fly from.
     * @return True if the flying move is successful, false otherwise.
     */
    public boolean botFly(Game game, int randomNumberFrom){
        Random random = new Random();
        boolean validMove = false;
        while(!validMove) {
            if (circleMatrix[randomNumberFrom][1].isVisible() || circleMatrix[randomNumberFrom][3].isVisible()) {
                int randomNumberTo = random.nextInt(24);
                if (!circleMatrix[randomNumberTo][0].isVisible() && !circleMatrix[randomNumberTo][1].isVisible() && !circleMatrix[randomNumberTo][2].isVisible() && !circleMatrix[randomNumberTo][3].isVisible()) {
                    game.hasMill(game.getBoard(), game.getCurrentTurn());
                    game.canMove(game, randomNumberFrom, randomNumberTo, game.getPlayer2());
                    if(game.getBoard().getBoardPositions()[randomNumberFrom].isHasSpecial()){
                        circleMatrix[randomNumberFrom][3].setVisible(false);
                        circleMatrix[randomNumberTo][3].setVisible(true);
                        game.getBoard().getBoardPositions()[randomNumberFrom].setHasSpecial(false);
                        game.getBoard().getBoardPositions()[randomNumberTo].setHasSpecial(true);
                    } else {
                        circleMatrix[randomNumberFrom][1].setVisible(false);
                        circleMatrix[randomNumberTo][1].setVisible(true);
                    }
                    if (game.hasMill(game.getBoard(), game.getCurrentTurn())) {
                        botDelete(game);
                    }
                    label.setText("White's turn!");
                    game.setCurrentTurn(game.getPlayer1());
                    validMove = true;
                    ifWon(game);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Attempts to perform a normal move for the bot.
     * @param game The game object representing the current state of the game.
     * @param randomNumberFrom The randomly chosen position to move from.
     * @return True if the normal move is successful, false otherwise.
     */
    public boolean botMove(Game game, int randomNumberFrom) {
        Random random = new Random();
        boolean validMove = false;
        while(!validMove) {
            if (circleMatrix[randomNumberFrom][1].isVisible()) {
                int tmp = random.nextInt(game.getBoard().getBoardPositions()[randomNumberFrom].getMoveAdjacentIndex().length);
                int randomNumberTo = game.getBoard().getBoardPositions()[randomNumberFrom].getMoveAdjacentIndex()[tmp];
                if (!circleMatrix[randomNumberTo][0].isVisible() && !circleMatrix[randomNumberTo][1].isVisible() && !circleMatrix[randomNumberTo][2].isVisible() && !circleMatrix[randomNumberTo][3].isVisible()) {
                    validMove = true;
                    game.hasMill(game.getBoard(), game.getCurrentTurn());
                    game.canMove(game, randomNumberFrom, randomNumberTo, game.getPlayer2());
                    circleMatrix[randomNumberFrom][1].setVisible(false);
                    circleMatrix[randomNumberTo][1].setVisible(true);
                    if (game.hasMill(game.getBoard(), game.getCurrentTurn())) {
                        botDelete(game);
                    }
                    label.setText("White's turn!");
                    game.setCurrentTurn(game.getPlayer1());
                    ifWon(game);
                } else {
                    return false;
                }

            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Deletes a piece on the board for the bot.
     * @param game The game object representing the current state of the game.
     */
    public void botDelete(Game game){
        boolean ableToDelete = false;
        Random random = new Random();
        while(!ableToDelete) {
            int randomPlace = random.nextInt(24);
            if (circleMatrix[randomPlace][0].isVisible() && !game.getBoard().getBoardPositions()[randomPlace].isInMill() || circleMatrix[randomPlace][2].isVisible()) {
                game.hasMill(game.getBoard(), game.getCurrentTurn());
                game.deletePiece(game.getBoard(), game.getPlayer1(), randomPlace, game);
                circleMatrix[randomPlace][0].setVisible(false);
                if(circleMatrix[randomPlace][2].isVisible()){
                    circleMatrix[randomPlace][2].setVisible(false);
                    game.getPlayer1().setSpecialPieceOnBoard(false);
                }
                ableToDelete = true;
                ifWon(game);
            }
        }
    }

    /**
     * Handles the flying action for a player's piece on the board.
     * @param game The game object representing the current state of the game.
     * @param player The player who is performing the flying action.
     * @param index The index of the position where the flying action is initiated.
     */
    public void fly(Game game, Player player, int index){
        ArrayList<JButton> list = new ArrayList<>();
        if(player == game.getPlayer1()) {
            if(circleMatrix[index][0].isVisible()|| circleMatrix[index][2].isVisible()) {
                ButtonListenerEnable = false;
                moveEnable = false;
                for (int i = 0; i < 24; i++) {
                    if (circleMatrix[i][0].isVisible() == false && circleMatrix[i][1].isVisible() == false && !circleMatrix[i][2].isVisible() && !circleMatrix[i][3].isVisible()) {
                        int indexi = i;
                        JButton button = new JButton();
                        button.setBounds(circleMatrix[i][0].getBounds());
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                game.hasMill(game.getBoard(), game.getCurrentTurn());
                                game.canMove(game, index, indexi, player);
                                if(game.getBoard().getBoardPositions()[index].isHasSpecial()){
                                    circleMatrix[index][2].setVisible(false);
                                    circleMatrix[indexi][2].setVisible(true);
                                    game.getBoard().getBoardPositions()[index].setHasSpecial(false);
                                    game.getBoard().getBoardPositions()[indexi].setHasSpecial(true);
                                } else {
                                    circleMatrix[index][0].setVisible(false);
                                    circleMatrix[indexi][0].setVisible(true);
                                }

                                for (JButton a : list) {
                                    a.setVisible(false);
                                }

                                if (game.hasMill(game.getBoard(), game.getCurrentTurn())) {
                                    delete(game, game.getPlayer2());
                                }

                                list.clear();
                                ButtonListenerEnable = true;
                                moveEnable = true;
                                label.setText("Black's turn!");
                                game.setCurrentTurn(game.getPlayer2());
                                if(game.getPlayer2().getPlayerToken() == Token.bot){
                                    botTurn(game);
                                }
                                ifWon(game);
                            }
                        });
                        lpane.add(button, 3, 0);
                        list.add(button);
                    }
                }
            }
        } else if(player == game.getPlayer2()) {
            if(circleMatrix[index][1].isVisible() == true || circleMatrix[index][3].isVisible()) {
                ButtonListenerEnable = false;
                moveEnable = false;
                for (int i = 0; i < 24; i++) {
                    if (circleMatrix[i][0].isVisible() == false && circleMatrix[i][1].isVisible() == false && !circleMatrix[i][2].isVisible() && !circleMatrix[i][3].isVisible()) {
                        int indexi = i;
                        JButton button = new JButton();
                        button.setBounds(circleMatrix[i][1].getBounds());
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                game.hasMill(game.getBoard(), game.getCurrentTurn());
                                game.canMove(game, index, indexi, player);
                                if(game.getBoard().getBoardPositions()[index].isHasSpecial()){
                                    circleMatrix[index][3].setVisible(false);
                                    circleMatrix[indexi][3].setVisible(true);
                                    game.getBoard().getBoardPositions()[index].setHasSpecial(false);
                                    game.getBoard().getBoardPositions()[indexi].setHasSpecial(true);
                                } else {
                                    circleMatrix[index][1].setVisible(false);
                                    circleMatrix[indexi][1].setVisible(true);
                                }
                                for (JButton a : list) {
                                    a.setVisible(false);
                                }

                                if (game.hasMill(game.getBoard(), game.getCurrentTurn())) {
                                    delete(game, game.getPlayer1());
                                }

                                list.clear();
                                ButtonListenerEnable = true;
                                moveEnable = true;
                                label.setText("White's turn!");
                                game.setCurrentTurn(game.getPlayer1());
                                if(game.getPlayer2().getPlayerToken() == Token.bot){
                                    botTurn(game);
                                }
                                ifWon(game);
                            }
                        });
                        lpane.add(button, 3, 0);
                        list.add(button);
                    }
                }
            }
        }
    }

    /**
     * Handles the movement action for a player's piece on the board.
     * @param game The game object representing the current state of the game.
     * @param player The player who is performing the movement action.
     * @param index The index of the position where the movement action is initiated.
     */
    public void move(Game game, Player player, int index){
        int hasNeighbour = 0;
        ArrayList<JButton> list = new ArrayList<JButton>();
        if(player == game.getPlayer1()) {
            if(circleMatrix[index][0].isVisible() == true) {
                ButtonListenerEnable = false;
                flyEnable = false;
                for (int i = 0; i < 24; i++) {
                    for(int j = 0; j < game.getBoard().getBoardPositions()[index].getMoveAdjacentIndex().length; j++){
                        if(game.getBoard().getBoardPositions()[index].getMoveAdjacentIndex()[j] == i){
                            if (circleMatrix[i][0].isVisible() == false && circleMatrix[i][1].isVisible() == false && !circleMatrix[i][2].isVisible() && !circleMatrix[i][3].isVisible()) {
                                int indexi = i;
                                JButton button = new JButton();
                                button.setBounds(circleMatrix[i][0].getBounds());
                                button.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        game.hasMill(game.getBoard(), game.getCurrentTurn());
                                        game.canMove(game, index, indexi, player);
                                        circleMatrix[index][0].setVisible(false);
                                        circleMatrix[indexi][0].setVisible(true);
                                        for (JButton a : list) {
                                            a.setVisible(false);
                                        }

                                        if (game.hasMill(game.getBoard(), game.getCurrentTurn())) {
                                            delete(game, game.getPlayer2());
                                        }

                                        list.clear();
                                        ButtonListenerEnable = true;
                                        flyEnable = true;
                                        label.setText("Black's turn!");
                                        game.setCurrentTurn(game.getPlayer2());
                                        if(game.getPlayer2().getPlayerToken() == Token.bot){
                                            botTurn(game);
                                        }
                                        ifWon(game);
                                    }
                                });
                                lpane.add(button, 3, 0);
                                list.add(button);
                                hasNeighbour++;
                            }
                        }
                    }
                }
                if(hasNeighbour == 0){
                    ButtonListenerEnable = true;
                    flyEnable = true;
                }
            }
        } else if (player == game.getPlayer2()) {
            if(circleMatrix[index][1].isVisible() == true) {
                ButtonListenerEnable = false;
                flyEnable = false;
                for (int i = 0; i < 24; i++) {
                    for (int j = 0; j < game.getBoard().getBoardPositions()[index].getMoveAdjacentIndex().length; j++) {
                        if (game.getBoard().getBoardPositions()[index].getMoveAdjacentIndex()[j] == i) {
                            if (circleMatrix[i][0].isVisible() == false && circleMatrix[i][1].isVisible() == false && !circleMatrix[i][2].isVisible() && !circleMatrix[i][3].isVisible()) {
                                int indexi = i;
                                JButton button = new JButton();
                                button.setBounds(circleMatrix[i][1].getBounds());
                                button.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        game.hasMill(game.getBoard(), game.getCurrentTurn());
                                        game.canMove(game, index, indexi, player);
                                        circleMatrix[index][1].setVisible(false);
                                        circleMatrix[indexi][1].setVisible(true);
                                        for (JButton a : list) {
                                            a.setVisible(false);
                                        }

                                        if (game.hasMill(game.getBoard(), game.getCurrentTurn())) {
                                            delete(game, game.getPlayer1());
                                        }
                                        list.clear();
                                        ButtonListenerEnable = true;
                                        flyEnable = true;
                                        label.setText("White's turn!");
                                        game.setCurrentTurn(game.getPlayer1());
                                        if(game.getPlayer2().getPlayerToken() == Token.bot){
                                            botTurn(game);
                                        }
                                        ifWon(game);
                                    }
                                });
                                lpane.add(button, 3, 0);
                                list.add(button);
                                hasNeighbour++;
                            }
                        }
                    }
                }
                if(hasNeighbour == 0){
                    ButtonListenerEnable = true;
                    flyEnable = true;
                }
            }
        }
    }

    /**
     * Handles the deletion of a player's piece from the board.
     * @param game The game object representing the current state of the game.
     * @param player The player whose piece is being deleted.
     */
    public void delete(Game game, Player player){
        botEnable = false;
        ButtonListenerEnable = false;
        moveEnable = false;
        flyEnable = false;
        int isThereTodelete = 0;
        ArrayList<JButton> list = new ArrayList<JButton>();
        if(player == game.getPlayer2()){
            for(int i = 0;i < circleMatrix.length; i++){
                if((circleMatrix[i][1].isVisible() == true && game.getBoard().getBoardPositions()[i].isInMill() == false) || circleMatrix[i][3].isVisible()){
                    JButton button = new JButton();
                    button.setBounds(circleMatrix[i][1].getBounds());
                    int index = i;
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            game.hasMill(game.getBoard(), game.getCurrentTurn());
                            game.deletePiece(game.getBoard(), game.getPlayer2(), index, game);
                            circleMatrix[index][1].setVisible(false);
                            if(circleMatrix[index][3].isVisible()){
                                circleMatrix[index][3].setVisible(false);
                                player.setSpecialPieceOnBoard(false);
                            }

                            for(JButton a : list){
                                a.setVisible(false);
                            }
                            list.clear();
                            ButtonListenerEnable = true;
                            moveEnable = true;
                            flyEnable = true;
                            botEnable = true;
                            if(game.getPlayer2().getPlayerToken() == Token.bot){
                                botTurn(game);
                            }
                            ifWon(game);
                        }
                    });
                    lpane.add(button, 3, 0);
                    list.add(button);
                    isThereTodelete++;
                }

            }
            if(isThereTodelete == 0){
                ButtonListenerEnable = true;
                moveEnable = true;
                flyEnable = true;
                botEnable = true;
                botTurn(game);
            }
        } else if(player == game.getPlayer1()) {
            //player1
            for(int i = 0;i < circleMatrix.length; i++){
                if((circleMatrix[i][0].isVisible() == true && game.getBoard().getBoardPositions()[i].isInMill() == false) || circleMatrix[i][2].isVisible()){
                    JButton button = new JButton();
                    button.setBounds(circleMatrix[i][0].getBounds());
                    int index = i;
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            game.hasMill(game.getBoard(),game.getCurrentTurn());
                            game.deletePiece(game.getBoard(), game.getPlayer1(), index, game);
                            circleMatrix[index][0].setVisible(false);
                            if(circleMatrix[index][2].isVisible()){
                                circleMatrix[index][2].setVisible(false);
                                player.setSpecialPieceOnBoard(false);
                            }
                            for(JButton a : list){
                                a.setVisible(false);
                            }
                            list.clear();
                            ButtonListenerEnable = true;
                            moveEnable = true;
                            flyEnable = true;
                            botEnable = true;
                            if(game.getPlayer2().getPlayerToken() == Token.bot){
                                botTurn(game);
                            }
                            ifWon(game);
                        }
                    });
                    lpane.add(button, 3, 0);
                    list.add(button);
                    isThereTodelete++;
                }
            }
            if(isThereTodelete == 0){
                ButtonListenerEnable = true;
                moveEnable = true;
                flyEnable = true;
                botEnable = true;
                botTurn(game);
                return;
            }
        }
    }

    /**
     * Checks if a player is able to move a piece to an adjacent position.
     * @param game The game object representing the current state of the game.
     * @param player The player attempting to move a piece.
     * @param k The index of the position from which the piece is being moved.
     * @return True if the player is able to move a piece, false otherwise.
     */
    public static boolean ableToMove(Game game, Player player, int k){
        int hasNeighbour = 0;
        boolean tmp = false;
        if(player == game.getPlayer1()) {
                if(circleMatrix[k][0].isVisible() == true) {
                    for (int i = 0; i < 24; i++) {
                        for(int j = 0; j < game.getBoard().getBoardPositions()[k].getMoveAdjacentIndex().length; j++){
                            if(game.getBoard().getBoardPositions()[k].getMoveAdjacentIndex()[j] == i){
                                if (circleMatrix[i][0].isVisible() == false && circleMatrix[i][1].isVisible() == false) {
                                    hasNeighbour++;
                                }
                            }
                        }
                    }
                    if(hasNeighbour != 0){
                        tmp = true;
                    }
                }
        } else if (player == game.getPlayer2()) {
                if(circleMatrix[k][1].isVisible() == true) {
                    for (int i = 0; i < 24; i++) {
                        for (int j = 0; j < game.getBoard().getBoardPositions()[k].getMoveAdjacentIndex().length; j++) {
                            if (game.getBoard().getBoardPositions()[k].getMoveAdjacentIndex()[j] == i) {
                                if (circleMatrix[i][0].isVisible() == false && circleMatrix[i][1].isVisible() == false) {
                                    hasNeighbour++;
                                }
                            }
                        }
                    }
                    if(hasNeighbour != 0){
                        tmp = true;
                    }
                }
        }
        return tmp;
    }

    /**
     * Checks if a player has won the game or if the game is still ongoing.
     * @param game The game object representing the current state of the game.
     * @return 0 if the game is still ongoing, 1 if Player 1 has won, and 2 if Player 2 has won.
     */
    public static int ifWin(Game game){
        if(game.getPlayer1().getGameState() == 2 || game.getPlayer1().getGameState() == 3) {
            if (game.getPlayer1().getPiecesOnBoard() == 2) {
                return 2;
            }
            int canMoveCounter = 0;
            for (int a = 0; a < 24; a++) {
                if (ableToMove(game, game.getPlayer1(), a)) {
                    canMoveCounter++;
                }
            }
            if(canMoveCounter == 0){
                return 2;
            }
        }
        if(game.getPlayer2().getGameState() == 2 || game.getPlayer2().getGameState() == 3){
            if (game.getPlayer2().getPiecesOnBoard() == 2) {
                return 1;
            }
            int canMoveCounter2 = 0;
            for(int a = 0; a < 24; a++){
                if(ableToMove(game, game.getPlayer2(),a)){
                    canMoveCounter2++;
                }
            }
            if(canMoveCounter2 == 0){
                return 1;
            }
        }
        return 0;
    }

    /**
     * Checks if a player has won the game and displays a corresponding message.
     * @param game The game object representing the current state of the game.
     */
    public void ifWon(Game game){
        JFrame frame2 = new JFrame("nyert");
        frame2.add(label, BorderLayout.CENTER);
        frame2.setLayout(new BorderLayout());
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(1000, 800);
        frame2.setResizable(false);
        label.setFont(new Font("Arial", Font.PLAIN, 100));
        label.setBounds(150,0,1000,800);
        frame2.setLocationRelativeTo(null);
        if(this.ifWin(game) == 1){
            label.setText("Fehér nyeeeert!");
            frame2.setVisible(true);
        }
        if(this.ifWin(game) == 2){
            label.setText("Nigger nyeeeert!");
            frame2.setVisible(true);
        }
    }

    /**
     * Initializes and configures combo boxes for selecting piece types (Normal or Special) for both players.
     * Allows players to choose the type of piece they want to place on the board.
     * @param frame The Frame instance associated with the game.
     */
    public void specialPieceInic(Frame frame){
        String[] items = { "Normal piece", "Special piece"};
        comboBox1 = new JComboBox<>(items);
        JLabel label = new JLabel("Selected Item: ");
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBox1.getSelectedItem();
                label.setText("Selected Item: " + selected);
                if(selected.equals("Special piece")){
                    game.getPlayer1().setSelectedPieceIsSpecial(true);
                } else if(selected.equals("Normal piece")){
                    game.getPlayer1().setSelectedPieceIsNormal(true);
                }
            }
        });
        comboBox1.setBounds(30,100,150,25);
        lpane.add(comboBox1,3,0);
        comboBox1.setVisible(true);

        comboBox2 = new JComboBox<>(items);
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBox2.getSelectedItem();
                label.setText("Selected Item: " + selected);
                if(selected.equals("Special piece")){
                    game.getPlayer2().setSelectedPieceIsSpecial(true);
                } else if(selected.equals("Normal piece")){
                    game.getPlayer2().setSelectedPieceIsNormal(true);
                }
            }
        });
        comboBox2.setBounds(820,100,150,25);
        lpane.add(comboBox2,3,0);
        comboBox2.setVisible(true);
    }

    /**
     * Refreshes the combo box options for selecting piece types, removing "Special piece" if the special piece is already on the board for the given player.
     * This method is used to update the combo box options when a player places a special piece on the board.
     * @param player The player for whom to refresh the combo box.
     * @param game   The Game instance representing the current game state.
     */
    public void comboBoxRefresh(Player player, Game game){
        if (!player.isSpecialPieceOnBoard()) return;
        if(player == game.getPlayer1()){
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox1.getModel();
            int index = model.getIndexOf("Special piece");
            if (index != -1) {
                model.removeElementAt(index);
            }
            game.getPlayer1().setSelectedPieceIsSpecial(false);
        } else if(player == game.getPlayer2()){
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox2.getModel();
            int index = model.getIndexOf("Special piece");
            if (index != -1) {
                model.removeElementAt(index);
            }
            game.getPlayer2().setSelectedPieceIsSpecial(false);
        }
    }

    /**
     * Refreshes the combo box options for selecting piece types, removing "Normal piece" if the player has 8 normal pieces on the board and no special piece.
     * This method is used to update the combo box options when a player has placed all normal pieces on the board.
     * @param player The player for whom to refresh the combo box.
     * @param game   The Game instance representing the current game state.
     */
    public void comboBoxRefreshNormal(Player player, Game game){
        if (player.getPiecesOnBoard() != 8 || player.isSpecialPieceOnBoard()) return;
        if(player == game.getPlayer1()){
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox1.getModel();
            int index = model.getIndexOf("Normal piece");
            if (index != -1) {
                model.removeElementAt(index);
            }
            game.getPlayer1().setSelectedPieceIsNormal(false);
        } else if(player == game.getPlayer2()){
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) comboBox2.getModel();
            int index = model.getIndexOf("Normal piece");
            if (index != -1) {
                model.removeElementAt(index);
            }
            game.getPlayer2().setSelectedPieceIsNormal(false);
        }
    }

    /**
     * Handles the bot's turn by selecting a special piece for Player 2 and hiding the corresponding combo box.
     * This method is called during the bot's turn to simulate the selection of a special piece and update the UI.
     * @param game The Game instance representing the current game state.
     */
    public void botHandler(Game game){
        game.getPlayer2().setSelectedPieceIsSpecial(true);
        comboBox2.setVisible(false);
    }
}