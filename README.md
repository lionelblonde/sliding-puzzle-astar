# Sliding Puzzle A*

This repository contains a Java implementation of the Astar algorithm, employed for the
resolution of a [sliding puzzle](https://en.wikipedia.org/wiki/Sliding_puzzle) game.
The puzzle grid consists in integers from `0` to `grid_dimension^2 - 1`, where `0`
corresponds to the position of the empty cell on the sliding puzzle board.

The goal of the game is to reach a state in which the digits are sorted in increasing order
(left to right, top to bottom), with `0` in the last cell. Here is an example of
grid where the algorithm managed to solve the puzzle, for dimension 3:

~~~~
1 | 2 | 3
---------
4 | 5 | 6
---------
7 | 8 | 0  <-- empty cell
~~~~

*Work done in 2013.*
