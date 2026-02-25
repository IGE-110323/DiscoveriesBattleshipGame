package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a fleet of ships in the Battleship game.
 * A fleet stores all ships placed by a player and provides
 * operations to query, filter and validate ship placement.
 *
 * <p>This class implements the {@link IFleet} interface and ensures
 * that ships are added only when they respect the board limits and
 * do not collide or touch other ships.</p>
 */
public class Fleet implements IFleet {

    /**
     * Prints all ships contained in the given list.
     *
     * @param ships the list of ships to print
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    /** The list of ships that belong to this fleet. */
    private List<IShip> ships;

    /**
     * Creates an empty fleet with no ships.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Returns all ships currently in the fleet.
     *
     * @return the list of ships
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Attempts to add a ship to the fleet.
     * A ship is only added if:
     * <ul>
     *     <li>The fleet has not reached its maximum size</li>
     *     <li>The ship is fully inside the board</li>
     *     <li>The ship does not collide or touch another ship</li>
     * </ul>
     *
     * @param s the ship to add
     * @return {@code true} if the ship was successfully added,
     *         {@code false} otherwise
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Returns all ships whose category matches the given string.
     *
     * @param category the category of ships to search for
     * @return a list of ships belonging to the given category
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Returns all ships that are still floating (not sunk).
     *
     * @return a list of floating ships
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Returns the ship occupying the given position, if any.
     *
     * @param pos the position to check
     * @return the ship at the given position, or {@code null} if none exists
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Checks whether a ship is fully inside the board boundaries.
     *
     * @param s the ship to validate
     * @return {@code true} if the ship is inside the board, {@code false} otherwise
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 &&
                s.getTopMostPos() >= 0 && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Checks whether adding a ship would cause a collision or adjacency
     * with any existing ship in the fleet.
     *
     * @param s the ship to test
     * @return {@code true} if there is collision risk, {@code false} otherwise
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * Prints the full status of the fleet, including:
     * <ul>
     *     <li>All ships</li>
     *     <li>Floating ships</li>
     *     <li>Ships grouped by category</li>
     * </ul>
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Prints all ships belonging to a specific category.
     *
     * @param category the category of interest
     */
    public void printShipsByCategory(String category) {
        assert category != null;
        printShips(getShipsLike(category));
    }

    /**
     * Prints all ships that are still floating.
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Prints all ships in the fleet.
     */
    void printAllShips() {
        printShips(ships);
    }
}