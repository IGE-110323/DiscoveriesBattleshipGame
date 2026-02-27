package iscteiul.ista.battleship;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides a collection of interactive test tasks for validating different
 * components of the Battleship game. Each task reads user input from the
 * console and performs operations such as building ships, assembling fleets,
 * firing shots and inspecting game state.
 *
 * <p>This class is intended for incremental development and manual testing.
 * It demonstrates how ships, fleets and game mechanics behave under various
 * scenarios, and may be partially converted into automated tests with
 * appropriate refactoring.</p>
 */
public class Tasks {

    /** Logger used to output task results and diagnostic messages. */
    private static final Logger LOGGER = LogManager.getLogger();

    /** Number of shots fired in a single firing round. */
    private static final int NUMBER_SHOTS = 3;

    /** Message displayed when the user exits a task. */
    private static final String GOODBYE_MESSAGE = "Bons ventos!";

    /** Command to create a new fleet. */
    private static final String NOVAFROTA = "nova";

    /** Command to exit the task. */
    private static final String DESISTIR = "desisto";

    /** Command to fire a round of shots. */
    private static final String RAJADA = "rajada";

    /** Command to display valid shots. */
    private static final String VERTIROS = "ver";

    /** Command to reveal the fleet (cheat mode). */
    private static final String BATOTA = "mapa";

    /** Command to display fleet status. */
    private static final String STATUS = "estado";

    // -------------------------------------------------------------------------
    // Interactive tasks for manual testing
    // -------------------------------------------------------------------------

    /**
     * Tests ship construction and occupancy detection. For each ship read from
     * the input, the task reads three positions and reports whether the ship
     * occupies each of them.
     */
    public static void taskA() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            Ship s = readShip(in);
            if (s != null)
                for (int i = 0; i < NUMBER_SHOTS; i++) {
                    Position p = readPosition(in);
                    LOGGER.info("{} {}", p, s.occupies(p));
                }
        }
    }

    /**
     * Tests the construction of fleets. The user may create a new fleet and
     * request its status. Unknown commands are ignored in this task.
     */
    public static void taskB() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();

        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Tests fleet construction and includes a cheat mode that prints the entire
     * fleet layout. Useful for verifying ship placement and collision rules.
     */
    public static void taskC() {
        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        String command = in.next();

        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    LOGGER.info(fleet);
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete lá ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    /**
     * Tests a full game round, including ship placement, firing a sequence of
     * shots and reporting game statistics, such as hits, invalid shots, repeated
     * shots and remaining ships.
     */
    public static void taskD() {

        Scanner in = new Scanner(System.in);
        IFleet fleet = null;
        IGame game = null;
        String command = in.next();

        while (!command.equals(DESISTIR)) {
            switch (command) {
                case NOVAFROTA:
                    fleet = buildFleet(in);
                    game = new Game(fleet);
                    break;
                case STATUS:
                    if (fleet != null)
                        fleet.printStatus();
                    break;
                case BATOTA:
                    if (fleet != null)
                        game.printFleet();
                    break;
                case RAJADA:
                    if (game != null) {
                        firingRound(in, game);

                        LOGGER.info("Hits: {} Inv: {} Rep: {} Restam {} navios.",
                                game.getHits(), game.getInvalidShots(),
                                game.getRepeatedShots(), game.getRemainingShips());

                        if (game.getRemainingShips() == 0)
                            LOGGER.info("Maldito sejas, Java Sparrow, eu voltarei, glub glub glub...");
                    }
                    break;
                case VERTIROS:
                    if (game != null)
                        game.printValidShots();
                    break;
                default:
                    LOGGER.info("Que comando é esse??? Repete ...");
            }
            command = in.next();
        }
        LOGGER.info(GOODBYE_MESSAGE);
    }

    // -------------------------------------------------------------------------
    // Utility operations used by the interactive tasks
    // -------------------------------------------------------------------------

    /**
     * Builds a fleet by repeatedly reading ship data from the input until the fleet
     * reaches its maximum size. Invalid ships or invalid placements are
     * reported through the logger.
     *
     * @param in the scanner used to read user input
     * @return the constructed fleet
     */
    static Fleet buildFleet(Scanner in) {
        assert in != null;

        Fleet fleet = new Fleet();
        int i = 0;

        while (i <= Fleet.FLEET_SIZE) {
            IShip s = readShip(in);
            if (s != null) {
                boolean success = fleet.addShip(s);
                if (success)
                    i++;
                else
                    LOGGER.info("Falha na criacao de {} {} {}", s.getCategory(), s.getBearing(), s.getPosition());
            } else {
                LOGGER.info("Navio desconhecido!");
            }
        }
        LOGGER.info("{} navios adicionados com sucesso!", i);
        return fleet;
    }

    /**
     * Reads ship data from the input, constructs the corresponding ship, and
     * returns it.
     *
     * @param in the scanner used to read user input
     * @return the constructed ship, or {@code null} if the category is invalid
     */
    static Ship readShip(Scanner in) {
        String shipKind = in.next();
        Position pos = readPosition(in);
        char c = in.next().charAt(0);
        Compass bearing = Compass.charToCompass(c);
        return Ship.buildShip(shipKind, bearing, pos);
    }

    /**
     * Reads a board position from the input.
     *
     * @param in the scanner used to read user input
     * @return the position that was read
     */
    static Position readPosition(Scanner in) {
        int row = in.nextInt();
        int column = in.nextInt();
        return new Position(row, column);
    }

    /**
     * Executes a firing round consisting of three shots in the context of a
     * game. Each shot is read from the input and applied to the game state.
     *
     * @param in the scanner used to read user input
     * @param game the game instance on which shots are fired
     */
    static void firingRound(Scanner in, IGame game) {
        for (int i = 0; i < NUMBER_SHOTS; i++) {
            IPosition pos = readPosition(in);
            IShip sh = game.fire(pos);
            if (sh != null)
                LOGGER.info("Mas... mas... {}s nao sao a prova de bala? :-(", sh.getCategory());
        }
    }
}
