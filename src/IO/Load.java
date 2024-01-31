package IO;

import UI.BoardUI;
import Game.Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;

/**
 * This class provides functionality to load a saved Game object from a file.
 */
public class Load {
    private Load() {
    }

    /**
     * Creates a Game object by deserializing it from a file specified by the given Path.
     * @param path The Path object representing the file path to deserialize the Game object from.
     * @return The deserialized Game object or null if an exception occurs during the process.
     */
    public static Game fromFile(Path path) {
        File file = new File(String.valueOf(path));
        if(!file.exists()){
            return null;
        }
        Game game = null;
        try {
            FileInputStream fileIn = new FileInputStream(path.toFile());
            ObjectInputStream in = new ObjectInputStream(fileIn);
            game = (Game) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Game class not found");
            c.printStackTrace();
        }
        return game;
    }
}
