# Battleship

Basic academic version of the Battleship game to build upon.

## Group Nickname
**TP06-01**

## Team Members

| Número  | Nome                         | Curso |
|---------|-------------------------------|--------|
| 122994  | Diogo Abegão da Silva Ramos de Almeida                 | IGE    |
| 122466  | José Maria Belo de Morais Sequeira Remédios           | IGE    |
| 110323  | Francisco Meirinho da Silva   | IGE    |

## Regras do Jogo

### 1. Preparação do Tabuleiro
- Cada jogador possui duas grelhas de **10×10**:
  - **O seu mar** (onde coloca os seus navios)
  - **O mar do adversário** (onde regista os tiros que dispara)
- Os navios são colocados horizontal ou verticalmente.
- Os navios **não podem tocar-se**, mas podem encostar às bordas.

### 2. Tipos de Navios
- Cada jogador tem a mesma frota.
- A frota inclui navios de diferentes tamanhos (ver secção “Tipos de Navios”).

### 3. Turnos de Jogo
- Em cada jogada, o jogador **atira três tiros**, indicando coordenadas (linha, coluna).
- O adversário deve responder ao resultado de cada tiro:
  - **Água** (não acertou)
  - **Atingido** (acertou num navio)
  - **Navio afundado** (quando todas as partes desse navio foram atingidas)

### 4. Registo das Jogadas
- Cada jogador deve marcar na sua grelha do adversário:
  - Tiros falhados
  - Tiros certeiros
  - Navios afundados
- As jogadas podem ser guardadas numa base de dados (ex.: JSON).

### 5. Condição de Vitória
- Ganha o jogo o primeiro jogador a **afundar todos os navios** da frota adversária.

### 6. Variante Opcional
- O jogo pode incluir um modo contra **IA**, usando um modelo open‑source.
