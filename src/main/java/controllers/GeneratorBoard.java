package controllers;

import models.Board;

public interface GeneratorBoard {
    void generateSudoku();
    Board getBoardInitial();
    Board getBoardSolution();
}
