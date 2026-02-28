package iscteiul.ista.battleship;

/**
 * Represents the directions (cardinal points) used to orient the ships on the board.
 */
public enum Compass {
    /** North direction (represented by 'n') */
    NORTH('n'),
    /** South direction (represented by 's') */
    SOUTH('s'),
    /** East direction (represented by 'e') */
    EAST('e'),
    /** West direction (represented by 'o') */
    WEST('o'),
    /** Unknown or invalid direction (represented by 'u') */
    UNKNOWN('u');

    /** The internal character that stores the direction letter. */
    private final char c;

    /**
     * Enum constructor. Associates a character with each direction.
     *
     * @param c The character representing the direction (e.g., 'n' for North).
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Returns the character associated with this direction.
     *
     * @return The representative character of the direction.
     */
    public char getDirection() {
        return c;
    }

    /**
     * Returns the text (String) representation of the direction.
     *
     * @return A String containing the direction letter.
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converts a single character into its respective cardinal point (Compass).
     *
     * @param ch The character to be converted ('n', 's', 'e', or 'o').
     * @return The corresponding {@link Compass} direction, or UNKNOWN if the character is not recognized.
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}