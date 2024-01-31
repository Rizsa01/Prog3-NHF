package Game;

import UI.Menu;

/**
 * The main class to launch the Nine Men's Morris game.
 */
public class Main {
    /**
     * The main method to start the game.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.initializeUI();
    }
}