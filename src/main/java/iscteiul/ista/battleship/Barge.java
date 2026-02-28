package iscteiul.ista.battleship;

/**
 * Represents a Barge in the Age of Discoveries fleet.
 * According to the game rules, this ship is the smallest and occupies only 1 position (square) on the board.
 */
public class Barge extends Ship {

    /** Fixed size of the Barge (occupies 1 square). */
    private static final Integer SIZE = 1;

    /** Representative name of the ship. */
    private static final String NAME = "Barge";

    /**
     * Constructor that initializes a Barge with its bearing and position.
     * Since the Barge occupies only 1 position, the bearing does not change the
     * area occupied on the board, but it is registered in the system.
     * * @param bearing The orientation of the ship (North, South, East, West).
     * @param pos     The initial (and only) coordinate where the Barge will be positioned.
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Returns the size of the Barge.
     * * @return The number of positions this ship occupies (always 1).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}