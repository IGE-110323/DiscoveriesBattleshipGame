package iscteiul.ista.battleship;

/**
 * Represents a Carrack (Nau) in the Age of Discoveries fleet.
 * According to the game rules, this ship occupies 3 positions on the board.s
 */
public class Carrack extends Ship {

    /** Fixed size of the Carrack (occupies 3 squares). */
    private static final Integer SIZE = 3;

    /** Representative name of the ship in Portuguese. */
    private static final String NAME = "Nau";

    /**
     * Constructor that initializes a Carrack with its bearing and initial position.
     *
     * @param bearing The orientation of the ship (NORTH, SOUTH, EAST, WEST).
     * @param pos     The initial position (coordinate) of the ship's stern.
     * @throws IllegalArgumentException If the bearing is invalid for this type of ship.
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Returns the size of the Carrack.
     *
     * @return The number of positions this ship occupies (3).
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}