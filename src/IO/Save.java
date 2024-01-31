package IO;

import Game.Game;
import UI.BoardUI;

import java.io.Serializable;
import java.nio.file.Path;

/**
 * Utility class for saving the game state to a file.
 */
public class Save implements Serializable{
    private Save(){}

    /**
     * Saves the current game state to a specified file path.
     * @param path The path where the game state will be saved.
     */
    public static void toFile(Path path) {
        Game game = BoardUI.getGame();
        try {
            java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(new java.io.FileOutputStream(path.toFile()));
            out.writeObject(game);
            out.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}
