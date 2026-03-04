package iscteiul.ista.battleship;

import java.util.List;

/**
 * Represents a generic ship in the Battleship game.
 * Defines the common properties and behaviors that all types of ships must implement,
 * such as their position, size, and status.
 */
public interface IShip {

    /**
     * Retrieves the category or name of the ship (e.g., "Carrack", "Barge").
     *
     * @return A String representing the ship's category.
     */
    String getCategory();

    /**
     * Retrieves the size of the ship in terms of how many board squares it occupies.
     *
     * @return The number of positions the ship occupies.
     */
    Integer getSize();

    /**
     * Retrieves the list of all positions (coordinates) currently occupied by this ship.
     *
     * @return A list of {@link IPosition} objects representing the ship's location.
     */
    List<IPosition> getPositions();

    /**
     * Retrieves the starting or anchor position of the ship (usually the stern or bow).
     *
     * @return The initial {@link IPosition} of the ship.
     */
    IPosition getPosition();

    /**
     * Retrieves the orientation of the ship on the board.
     *
     * @return The {@link Compass} direction the ship is facing.
     */
    Compass getBearing();

    /**
     * Checks if the ship is still floating (i.e., not all of its positions have been hit).
     *
     * @return true if the ship is still floating, false if it has been completely sunk.
     */
    boolean stillFloating();

    /**
     * Retrieves the highest row index (top-most) occupied by the ship.
     *
     * @return The top-most row index.
     */
    int getTopMostPos();

    /**
     * Retrieves the lowest row index (bottom-most) occupied by the ship.
     *
     * @return The bottom-most row index.
     */
    int getBottomMostPos();

    /**
     * Retrieves the left-most column index occupied by the ship.
     *
     * @return The left-most column index.
     */
    int getLeftMostPos();

    /**
     * Retrieves the right-most column index occupied by the ship.
     *
     * @return The right-most column index.
     */
    int getRightMostPos();

    /**
     * Checks if the ship occupies a specific position on the board.
     *
     * @param pos The {@link IPosition} to check.
     * @return true if the ship occupies the given position, false otherwise.
     */
    boolean occupies(IPosition pos);

    /**
     * Checks if this ship is placed too close to another ship.
     * According to the game rules, ships cannot touch each other.
     *
     * @param other The other {@link IShip} to check distance against.
     * @return true if the ships are too close (touching), false otherwise.
     */
    boolean tooCloseTo(IShip other);

    /**
     * Checks if this ship is placed too close to a specific position.
     *
     * @param pos The {@link IPosition} to check distance against.
     * @return true if the ship is too close to the given position, false otherwise.
     */
    boolean tooCloseTo(IPosition pos);

    /**
     * Registers a hit on the ship at the specified position.
     *
     * @param pos The {@link IPosition} where the ship was hit.
     */
    void shoot(IPosition pos);
}