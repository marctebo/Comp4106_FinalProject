# Comp4106_FinalProject
Creator: Marc teBoekhorst

This project was developed for the COMP4106 (Artificial Intelligence) final project.  The purpose of the project was to develop an AI that could solve Rush Hour puzzles.  Rules of the game may be found at this Wikipedia page: https://en.wikipedia.org/wiki/Rush_Hour_(board_game). Although the original game used cars and trucks, this program used boats and cargo ships (as specified in the project proposal). 

To run this program, download the project folder and run the "ShipYardGUI.java" file located in the "src" directory. Once you have ran the file, select the difficulty of the puzzle you wish to solve (1-8) and wait for the program to find a solution.  After the solution has been found, you may step through each move in the solution using the "Next" button.  The "Previous" button can be used to step backwards in the solution.  If you wish to run a different puzzle, restart the program and select a new difficulty.

To solve these puzzles, a breadth-first search (BFS) algorithm was used. The AI clones the state of the board, then generates each possible move given the arrangement of the ships.  The AI then traverses each branch of the created tree until a solution is found.  Because these problems are mostly trivial, BFS was chosen over depth-first search due to the sheer speed that it completed the task.
