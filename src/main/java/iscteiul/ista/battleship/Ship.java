package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract base class representing a generic ship in the Battleship game.
 * A ship is defined by its category, bearing (orientation), starting position,
 * and the list of board positions it occupies.
 *
 * <p>This class implements the {@link IShip} interface and provides shared
 * behavior for all ship types, including hit detection, adjacency checks,
 * boundary calculations and collision proximity rules. Concrete ship types
 * must define their size and initialize their occupied positions.</p>
 */
public abstract class Ship implements IShip {

    /** Identifier for a Galleon ship. */
    private static final String GALEAO = "galeao";

    /** Identifier for a Frigate ship. */
    private static final String FRAGATA = "fragata";

    /** Identifier for a Carrack ship. */
    private static final String NAU = "nau";

    /** Identifier for a Caravel ship. */
    private static final String CARAVELA = "caravela";

    /** Identifier for a Barge ship. */
    private static final String BARCA = "barca";

    /**
     * Factory method that builds a specific ship instance based on its category.
     * The created ship is oriented according to the given bearing and placed
     * starting at the given position.
     *
     * @param shipKind the category of the ship to create
     * @param bearing the orientation of the ship
     * @param pos the starting position of the ship
     * @return a concrete {@link Ship} instance, or {@code null} if the category is invalid
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }

    /** The category of this ship (e.g., "fragata", "caravela"). */
    private String category;

    /** The orientation of the ship on the board. */
    private Compass bearing;

    /** The starting position of the ship. */
    private IPosition pos;

    /** The list of all board positions occupied by this ship. */
    protected List<IPosition> positions;

    /**
     * Creates a ship with the given category, bearing, and starting position.
     * Subclasses are responsible for populating the {@code positions} list
     * according to the ship's size and orientation.
     *
     * @param category the category of the ship
     * @param bearing the orientation of the ship
     * @param pos the starting position of the ship
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Returns the category of this ship.
     *
     * @return the ship category
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Returns the list of positions occupied by this ship.
     *
     * @return the list of occupied positions
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Returns the starting position of this ship.
     *
     * @return the starting position
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Returns the orientation of this ship.
     *
     * @return the ship's bearing
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Determines whether the ship is still floating.
     * A ship is considered floating if at least one of its positions
     * has not been hit.
     *
     * @return {@code true} if the ship is still afloat,
     *         {@code false} if all positions have been hit
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Returns the smallest row index occupied by this ship.
     *
     * @return the top-most row index
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Returns the largest row index occupied by this ship.
     *
     * @return the bottom-most row index
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Returns the smallest column index occupied by this ship.
     *
     * @return the left-most column index
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Returns the largest column index occupied by this ship.
     *
     * @return the right-most column index
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Determines whether this ship occupies the given position.
     *
     * @param pos the position to check
     * @return {@code true} if the ship occupies the position,
     *         {@code false} otherwise
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Determines whether this ship is too close to another ship.
     * Two ships are considered too close if any of their positions
     * are adjacent.
     *
     * @param other the ship to compare with
     * @return {@code true} if the ships are adjacent or touching,
     *         {@code false} otherwise
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Determines whether this ship is too close to a given position.
     * A ship is too close if any of its positions is adjacent to the given one.
     *
     * @param pos the position to compare with
     * @return {@code true} if the ship is adjacent to the position,
     *         {@code false} otherwise
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }

    /**
     * Registers a shot on this ship at the given position.
     * If the position matches one of the ship's segments, that segment
     * is marked as hit.
     *
     * @param pos the position being targeted
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Returns a string representation of the ship, including its category,
     * orientation and starting position.
     *
     * @return a string describing the ship
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }
}