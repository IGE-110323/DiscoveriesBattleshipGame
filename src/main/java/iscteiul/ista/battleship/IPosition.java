package iscteiul.ista.battleship;

/**
 * Represents a specific coordinate or position on the Battleship game board.
 * It tracks the location (row and column) and its current state (occupied, hit).
 */
public interface IPosition {

    /**
     * Gets the row index of this position.
     *
     * @return The row number.
     */
    int getRow();

    /**
     * Gets the column index of this position.
     *
     * @return The column number.
     */
    int getColumn();

    /**
     * Compares this position to another object to check if they represent the same coordinates.
     *
     * @param other The other object (usually an IPosition) to compare with.
     * @return true if the coordinates are the same, false otherwise.
     */
    boolean equals(Object other);

    /**
     * Checks if this position is adjacent to another specified position.
     * Adjacency usually includes horizontal, vertical, and sometimes diagonal positions depending on game rules.
     *
     * @param other The {@link IPosition} to check for adjacency.
     * @return true if the positions are adjacent, false otherwise.
     */
    boolean isAdjacentTo(IPosition other);

    /**
     * Marks this position as occupied by a ship.
     */
    void occupy();

    /**
     * Registers a shot fired at this specific position.
     */
    void shoot();

    /**
     * Checks if there is a ship occupying this position.
     *
     * @return true if the position is occupied, false otherwise.
     */
    boolean isOccupied();

    /**
     * Checks if this position has already been fired upon during the game.
     *
     * @return true if a shot has hit this position, false otherwise.
     */
    boolean isHit();
}