package iscteiul.ista.battleship;

/**
 * Represents a Galleon ("Galeão") in the Discoveries Battleship Game.
 * <p>
 * A Galleon is the largest ship in the fleet, with a size of 5 cells.
 * It corresponds to the "porta-aviões" in the modern Battleship game.
 * </p>
 *
 * <p>The ship is placed on the board according to its initial position
 * and orientation ({@link Compass}). Each orientation uses a specific
 * geometric pattern, implemented through helper methods such as
 * {@code fillNorth}, {@code fillSouth}, etc.</p>
 */
public class Galleon extends Ship {

    /** The fixed size of a Galleon (5 cells). */
    private static final Integer SIZE = 5;

    /** The display name of this ship type. */
    private static final String NAME = "Galeao";

    /**
     * Creates a new Galleon with the given orientation and starting position.
     *
     * <p>The constructor delegates the computation of occupied positions
     * to orientation-specific helper methods:</p>
     * <ul>
     *     <li>{@code NORTH}: uses {@link #fillNorth(IPosition)}</li>
     *     <li>{@code EAST}: uses {@link #fillEast(IPosition)}</li>
     *     <li>{@code SOUTH}: uses {@link #fillSouth(IPosition)}</li>
     *     <li>{@code WEST}: uses {@link #fillWest(IPosition)}</li>
     * </ul>
     *
     * @param bearing the orientation of the ship
     * @param pos the starting position of the ship
     * @throws IllegalArgumentException if the bearing is invalid
     * @throws NullPointerException if the bearing is {@code null}
     */
    public Galleon(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Galleon.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the galleon");

        switch (bearing) {
            case NORTH:
                fillNorth(pos);
                break;
            case EAST:
                fillEast(pos);
                break;
            case SOUTH:
                fillSouth(pos);
                break;
            case WEST:
                fillWest(pos);
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the galleon");
        }
    }

    /**
     * Returns the size of the Galleon.
     *
     * @return the size of the ship (always 5)
     */
    @Override
    public Integer getSize() {
        return Galleon.SIZE;
    }

    /**
     * Fills the ship's occupied positions when oriented to the NORTH.
     *
     * @param pos the starting position
     */
    private void fillNorth(IPosition pos) {
        for (int i = 0; i < 3; i++) {
            getPositions().add(new Position(pos.getRow(), pos.getColumn() + i));
        }
        getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + 1));
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + 1));
    }

    /**
     * Fills the ship's occupied positions when oriented to the SOUTH.
     *
     * @param pos the starting position
     */
    private void fillSouth(IPosition pos) {
        for (int i = 0; i < 2; i++) {
            getPositions().add(new Position(pos.getRow() + i, pos.getColumn()));
        }
        for (int j = 2; j < 5; j++) {
            getPositions().add(new Position(pos.getRow() + 2, pos.getColumn() + j - 3));
        }
    }

    /**
     * Fills the ship's occupied positions when oriented to the EAST.
     *
     * @param pos the starting position
     */
    private void fillEast(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 3));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }

    /**
     * Fills the ship's occupied positions when oriented to the WEST.
     *
     * @param pos the starting position
     */
    private void fillWest(IPosition pos) {
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
        for (int i = 1; i < 4; i++) {
            getPositions().add(new Position(pos.getRow() + 1, pos.getColumn() + i - 1));
        }
        getPositions().add(new Position(pos.getRow() + 2, pos.getColumn()));
    }
}