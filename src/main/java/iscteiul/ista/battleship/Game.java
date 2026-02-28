package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Battleship game session.
 * <p>
 * A {@code Game} manages a fleet, tracks all shots fired by the player,
 * and keeps statistics such as invalid shots, repeated shots, hits,
 * and sunk ships.
 * </p>
 *
 * <p>This class implements the {@link IGame} interface and provides
 * the core game logic for firing at positions and updating ship states.</p>
 */
public class Game implements IGame {

    /** The fleet of ships used in this game. */
    private IFleet fleet;

    /** List of all valid shots fired by the player. */
    private List<IPosition> shots;

    /** Number of shots fired outside the board. */
    private Integer countInvalidShots;

    /** Number of shots fired at a position already targeted before. */
    private Integer countRepeatedShots;

    /** Number of successful hits on ships. */
    private Integer countHits;

    /** Number of ships completely sunk. */
    private Integer countSinks;

    /**
     * Creates a new game with the given fleet.
     *
     * @param fleet the fleet to be used in the game
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Fires a shot at the given position.
     * <p>
     * The method performs the following checks:
     * </p>
     * <ul>
     *     <li>If the shot is outside the board → increments invalid shots</li>
     *     <li>If the shot was already made → increments repeated shots</li>
     *     <li>If the shot hits a ship → registers hit and checks if it sinks</li>
     * </ul>
     *
     * @param pos the position to fire at
     * @return the ship that was sunk by this shot, or {@code null} if no ship was sunk
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else {
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Returns the list of all valid shots fired.
     *
     * @return list of positions representing shots
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Returns the number of repeated shots.
     *
     * @return number of repeated shots
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Returns the number of invalid shots.
     *
     * @return number of invalid shots
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Returns the number of successful hits.
     *
     * @return number of hits
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Returns the number of ships that have been sunk.
     *
     * @return number of sunk ships
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Returns the number of ships still floating.
     *
     * @return number of remaining ships
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Checks whether a shot is inside the board boundaries.
     *
     * @param pos the position to validate
     * @return {@code true} if the shot is valid, {@code false} otherwise
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE &&
                pos.getColumn() >= 0 && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Checks whether a shot has already been fired at the given position.
     *
     * @param pos the position to check
     * @return {@code true} if the shot is repeated, {@code false} otherwise
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Prints a board representation marking the given positions with a character.
     *
     * @param positions the positions to mark
     * @param marker the character used to mark the positions
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }
    }

    /**
     * Prints the board showing all valid shots fired by the player.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }

    /**
     * Prints the board showing the entire fleet layout.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }
}