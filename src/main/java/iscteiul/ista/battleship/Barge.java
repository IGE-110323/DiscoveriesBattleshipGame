package iscteiul.ista.battleship;

/**
 * Representa uma Barca na frota dos Descobrimentos.
 * De acordo com as regras do jogo, este navio é o mais pequeno e ocupa apenas 1 posição (quadrado) no tabuleiro.
 * * @author Teu Nome de Aluno
 */
public class Barge extends Ship {
    /** Tamanho fixo da Barca (ocupa 1 quadrado). */
    private static final Integer SIZE = 1;

    /** Nome representativo do navio. */
    private static final String NAME = "Barca";

    /**
     * Construtor que inicializa uma Barca com a sua orientação e posição.
     * Como a Barca ocupa apenas 1 posição, a orientação (bearing) não altera a 
     * área ocupada no tabuleiro, mas é registada no sistema.
     * * @param bearing A orientação do navio (Norte, Sul, Este, Oeste).
     * @param pos     A coordenada inicial (e única) onde a Barca será posicionada.
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Devolve o tamanho da Barca.
     * * @return O número de posições que este navio ocupa (sempre 1).
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }

}