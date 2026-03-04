package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Represents a position on the Battleship game board.
 * A position stores its row and column coordinates and tracks
 * whether it is occupied by a ship segment and whether it has been hit.
 *
 * <p>This class implements the {@link IPosition} interface and provides
 * basic operations for querying adjacency, marking occupation, and
 * registering shots on the board.</p>
 */
public class Position implements IPosition {

    /** The row index of this position. */
    private int row;

    /** The column index of this position. */
    private int column;

    /** Indicates whether this position is currently occupied by a ship. */
    private boolean isOccupied;

    /** Indicates whether this position has already been hit. */
    private boolean isHit;

    /**
     * Creates a new position with the given row and column.
     * Newly created positions are neither occupied nor hit.
     *
     * @param row the row index of the position
     * @param column the column index of the position
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * Returns the row index of this position.
     *
     * @return the row index
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Returns the column index of this position.
     *
     * @return the column index
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Computes a hash code based on the position's coordinates and state.
     *
     * @return the hash code for this position
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Compares this position with another object for equality.
     * Two positions are considered equal if they share the same
     * row and column coordinates.
     *
     * @param otherPosition the object to compare with
     * @return {@code true} if both objects represent the same position,
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() &&
                    this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Determines whether this position is adjacent to another.
     * Two positions are adjacent if the difference in both row and
     * column coordinates is at most 1.
     *
     * @param other the position to compare with
     * @return {@code true} if the positions are adjacent,
     *         {@code false} otherwise
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 &&
                Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marks this position as occupied by a ship segment.
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * Registers a shot on this position, marking it as hit.
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * Returns whether this position is currently occupied.
     *
     * @return {@code true} if the position is occupied,
     *         {@code false} otherwise
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Returns whether this position has already been hit.
     *
     * @return {@code true} if the position has been hit,
     *         {@code false} otherwise
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Returns a string representation of this position.
     *
     * @return a string containing the row and column values
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }
}