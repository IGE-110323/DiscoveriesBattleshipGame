package iscteiul.ista.battleship;

/**
 * Representa as direções (pontos cardeais) usadas para orientar os navios no tabuleiro.
 * * @author fba
 */
public enum Compass {
    /** Direção Norte (representada por 'n') */
    NORTH('n'),
    /** Direção Sul (representada por 's') */
    SOUTH('s'),
    /** Direção Este/Leste (representada por 'e') */
    EAST('e'),
    /** Direção Oeste (representada por 'o') */
    WEST('o'),
    /** Direção desconhecida ou inválida (representada por 'u') */
    UNKNOWN('u');

    /** O carácter interno que guarda a letra da direção. */
    private final char c;

    /**
     * Construtor do Enum. Associa um carácter a cada direção.
     * * @param c O carácter que representa a direção (ex: 'n' para Norte).
     */
    Compass(char c) {
        this.c = c;
    }

    /**
     * Devolve o carácter associado a esta direção.
     * * @return O carácter representativo da direção.
     */
    public char getDirection() {
        return c;
    }

    /**
     * Devolve a representação em formato de texto (String) da direção.
     * * @return Uma String contendo a letra da direção.
     */
    @Override
    public String toString() {
        return "" + c;
    }

    /**
     * Converte um carácter solto no seu respetivo ponto cardeal (Compass).
     * * @param ch O carácter a ser convertido ('n', 's', 'e' ou 'o').
     * @return A direção {@link Compass} correspondente, ou UNKNOWN se o carácter não for reconhecido.
     */
    static Compass charToCompass(char ch) {
        Compass bearing;
        switch (ch) {
            case 'n':
                bearing = NORTH;
                break;
            case 's':
                bearing = SOUTH;
                break;
            case 'e':
                bearing = EAST;
                break;
            case 'o':
                bearing = WEST;
                break;
            default:
                bearing = UNKNOWN;
        }

        return bearing;
    }
}