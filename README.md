# Conway
The game of life is a simple cellular automaton devised by John Horton Conway in 1970.

The game takes place on a two - dimensional grid (theoretically infinite) whose cells can take two distinct states : 
*alive* or *dead*.

## Game rules
The cells are born, survive or die (disappear) from one generation to another by the following rules :
- Any dead cell with exactly three live neighbours becomes a living cell.
- Any living cell with zero or one neighbour dies (isolation).
- Any living cell with more than three live neighbours dies (overcrowding).
- Any living cell with two or three live neighbours survives.

