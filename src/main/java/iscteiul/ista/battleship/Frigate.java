package iscteiul.ista.battleship;

/**
 * Represents a Frigate ("Fragata") in the Discoveries Battleship Game.
 * <p>
 * A Frigate is a ship of size 4 and corresponds to the traditional
 * "navio de 4 canhões" in the classic Battleship game.
 * </p>
 *
 * <p>The ship is placed on the board according to its initial position
 * and orientation (bearing). Depending on the {@link Compass} direction,
 * the ship occupies 4 consecutive cells either vertically or horizontally.</p>
 */
public class Frigate extends Ship {

    /** The fixed size of a Frigate (4 cells). */
    private static final Integer SIZE = 4;

    /** The display name of this ship type. */
    private static final String NAME = "Fragata";

    /**
     * Creates a new Frigate with the given orientation and starting position.
     *
     * <p>The constructor computes the occupied positions based on the
     * direction of the ship:</p>
     * <ul>
     *     <li>{@code NORTH}/{@code SOUTH}: occupies 4 vertical cells</li>
     *     <li>{@code EAST}/{@code WEST}: occupies 4 horizontal cells</li>
     * </ul>
     *
     * @param bearing the orientation of the ship (NORTH, SOUTH, EAST, WEST)
     * @param pos the starting position (top-most or left-most cell)
     * @throws IllegalArgumentException if the bearing is invalid
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);

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
                throw new IllegalArgumentException("ERROR! invalid bearing for the frigate");
        }
    }

    /**
     * Returns the size of the Frigate.
     *
     * @return the size of the ship (always 4)
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }
}