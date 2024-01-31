package Game;

import java.io.Serializable;

/**
 * This class represents a container for storing information about a mill in the Nine Men's Morris game.
 * Implements Serializable to allow for object serialization.
 */
public class MillContainer implements Serializable {
    private int a;
    private int b;
    private int c;

    /**
     * Constructs a MillContainer with the specified values for its fields.
     * @param au The value for field 'a'.
     * @param bu The value for field 'b'.
     * @param cu The value for field 'c'.
     */
    public MillContainer(int au, int bu, int cu){
        a = au;
        b = bu;
        c = cu;
    }

    /**
     * Gets the value of field 'a'.
     * @return The value of field 'a'.
     */
    public int getA() {
        return a;
    }

    /**
     * Sets the value of field 'a'.
     *
     * @param a The new value for field 'a'.
     */
    public void setA(int a) {
        this.a = a;
    }

    /**
     * Gets the value of field 'b'.
     * @return The value of field 'b'.
     */
    public int getB() {
        return b;
    }

    /**
     * Sets the value of field 'b'.
     *
     * @param b The new value for field 'b'.
     */
    public void setB(int b) {
        this.b = b;
    }

    /**
     * Gets the value of field 'c'.
     * @return The value of field 'c'.
     */
    public int getC() {
        return c;
    }

    /**
     * Sets the value of field 'c'.
     * @param c The new value for field 'c'.
     */
    public void setC(int c) {
        this.c = c;
    }
}
