package iscteiul.ista.battleship;

import java.util.List;

/**
 * Represents a fleet of ships in the Battleship game.
 * The fleet is positioned on a grid and manages a collection of ships.
 */
public interface IFleet {

    /** * The default size of the board's width and height (10x10 grid). 
     */
    Integer BOARD_SIZE = 10;

    /** * The total number of ships a player has in their fleet. 
     */
    Integer FLEET_SIZE = 10;

    /**
     * Retrieves all the ships currently in the fleet.
     *
     * @return A list containing all {@link IShip} objects in the fleet.
     */
    List<IShip> getShips();

    /**
     * Adds a new ship to the fleet.
     *
     * @param s The {@link IShip} to be added to the fleet.
     * @return true if the ship was successfully added, false otherwise (e.g., if the fleet is full or position is invalid).
     */
    boolean addShip(IShip s);

    /**
     * Retrieves a list of ships that match a specific category or name (e.g., "Caravel", "Carrack").
     *
     * @param category The name of the ship category to filter by.
     * @return A list of {@link IShip} objects that match the specified category.
     */
    List<IShip> getShipsLike(String category);

    /**
     * Retrieves all ships in the fleet that are still floating (not fully destroyed).
     *
     * @return A list of {@link IShip} objects that have not been sunk.
     */
    List<IShip> getFloatingShips();

    /**
     * Finds and returns the ship located at a specific position on the board.
     *
     * @param pos The {@link IPosition} coordinates to check.
     * @return The {@link IShip} at the given position, or null if there is no ship at that coordinate.
     */
    IShip shipAt(IPosition pos);

    /**
     * Prints the current status of the fleet to the console.
     * This may include details about floating and sunken ships.
     */
    void printStatus();
}