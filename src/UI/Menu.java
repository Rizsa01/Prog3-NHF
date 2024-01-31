package UI;

import Game.Token;
import IO.Load;
import IO.Save;
import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.nio.file.Paths;

/**
 * This class initializes the user interface for the Nine Men's Morris game.
 * Provides options for starting a new game, loading a saved game, or exiting the application.
 */
public class Menu extends JFrame implements Serializable {
    /**
     * Initializes the main user interface for the game.
     * Creates a JFrame with options for starting a new game, loading a saved game, or exiting the application.
     */
    public void initializeUI() {
        JFrame frame = new JFrame("Nine Men's Morris");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setResizable(false);
        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));
        frame.add(panel);
        JButton game = new JButton("New Game");
        game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove all the other options from the frame
                frame.getContentPane().removeAll();
                frame.getContentPane().validate();
                frame.getContentPane().repaint();

                JPanel panel2 = new JPanel(new GridLayout(2, 1));
                panel2.setBorder(BorderFactory.createEmptyBorder(200, 200, 200, 200));



                JButton optionA = new JButton("Másik játékos ellen");
                optionA.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BoardUI A = new BoardUI(frame, Token.Player2);
                    }});


                JButton optionB = new JButton("Bot ellen");
                optionB.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {BoardUI A = new BoardUI(frame, Token.bot);}});
                panel2.add(optionA);
                panel2.add(optionB);
                panel2.setVisible(true);
                frame.getContentPane().add(panel2);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            }
        });
        panel.add(game);


        JTextField fileName = new JTextField();
        fileName.setVisible(true);
        fileName.setBounds(500,20,200,50);
        panel.add(fileName);


        JButton load = new JButton("Load Game");
        panel.add(load);
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Game game = Load.fromFile(Paths.get(fileName.getText()));
                if(game == null){
                    return;
                }
                frame.getContentPane().removeAll();
                frame.getContentPane().validate();
                frame.getContentPane().repaint();
                BoardUI A = new BoardUI(frame, game);

            }
        });
        JButton exit = new JButton("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exit);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



}
