package Game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents containers for the game.
 * It implements Serializable interface to support serialization.
 */
public class MillHelper implements Serializable {

    public ArrayList<MillContainer> mills = new ArrayList<>();
    public ArrayList<MillContainer> helper = new ArrayList<>();

}
