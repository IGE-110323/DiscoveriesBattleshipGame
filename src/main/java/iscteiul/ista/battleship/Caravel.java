package iscteiul.ista.battleship;

/**
 * Representa uma Caravela na frota dos Descobrimentos.
 * De acordo com as regras do jogo, este navio ocupa 2 posições no tabuleiro.
 * * @author Teu Nome de Aluno
 */
public class Caravel extends Ship {
    /** Tamanho fixo da Caravela (ocupa 2 quadrados). */
    private static final Integer SIZE = 2;

    /** Nome representativo do navio. */
    private static final String NAME = "Caravela";

    /**
     * Construtor que inicializa uma Caravela com a sua orientação e posição inicial.
     * O navio será desenhado ocupando 2 posições consecutivas.
     * * @param bearing A orientação para onde a Caravela aponta (Norte, Sul, Este, Oeste).
     * @param pos     O ponto (coordenada) inicial para posicionar a Caravela.
     * @throws NullPointerException     Se a orientação (bearing) fornecida for nula.
     * @throws IllegalArgumentException Se a orientação for inválida (ex: UNKNOWN).
     */
    public Caravel(Compass bearing, IPosition pos) throws NullPointerException, IllegalArgumentException {
        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for the caravel");
        }

    }

    /**
     * Devolve o tamanho da Caravela.
     * * @return O número de posições que este navio ocupa (2).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}