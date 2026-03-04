package iscteiul.ista.battleship;

import java.util.List;

/**
 * Represents the main game logic and actions for a Battleship game.
 * It manages the firing mechanics, shot statistics, and fleet status.
 */
public interface IGame {

    /**
     * Fires a shot at the specified position on the board.
     *
     * @param pos The {@link IPosition} coordinates where the shot is fired.
     * @return The {@link IShip} that was hit, or null if the shot missed (hit water).
     */
    IShip fire(IPosition pos);

    /**
     * Retrieves the history of all shots fired during the game.
     *
     * @return A list of {@link IPosition} representing all targeted coordinates.
     */
    List<IPosition> getShots();

    /**
     * Gets the total number of repeated shots (shots fired at previously targeted positions).
     *
     * @return The number of repeated shots.
     */
    int getRepeatedShots();

    /**
     * Gets the total number of invalid shots (e.g., shots fired outside the board's boundaries).
     *
     * @return The number of invalid shots.
     */
    int getInvalidShots();

    /**
     * Gets the total number of successful hits on enemy ships.
     *
     * @return The number of hits.
     */
    int getHits();

    /**
     * Gets the total number of enemy ships that have been completely sunk.
     *
     * @return The number of sunk ships.
     */
    int getSunkShips();

    /**
     * Gets the number of enemy ships that are still afloat.
     *
     * @return The number of remaining ships.
     */
    int getRemainingShips();

    /**
     * Prints the record or grid of all valid shots fired so far.
     */
    void printValidShots();

    /**
     * Prints the current status and positioning of the player's fleet on the board.
     */
    void printFleet();
}