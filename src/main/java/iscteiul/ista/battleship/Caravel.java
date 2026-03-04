package iscteiul.ista.battleship;

/**
 * Represents a Caravel in the Age of Discoveries fleet.
 * According to the game rules, this ship occupies 2 positions on the board.
 */
public class Caravel extends Ship {
    /** Fixed size of the Caravel (occupies 2 squares). */
    private static final Integer SIZE = 2;

    /** Representative name of the ship. */
    private static final String NAME = "Caravel";

    /**
     * Constructor that initializes a Caravel with its initial bearing and position.
     * The ship will be drawn occupying 2 consecutive positions.
     *
     * @param bearing The bearing the Caravel points to (North, South, East, West).
     * @param pos     The initial point (coordinate) to position the Caravel.
     * @throws NullPointerException     If the provided bearing is null.
     * @throws IllegalArgumentException If the bearing is invalid (e.g., UNKNOWN).
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /**
     * Returns the size of the Caravel.
     *
     * @return The number of positions this ship occupies (2).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}