package Game;

import java.io.Serializable;

/**
 * Represents the tokens that can be assigned to players or indicate the absence of a player.
 */
public enum Token implements Serializable {
    Player1, Player2, noplayer, bot;
}
