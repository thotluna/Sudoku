package controllers;

import models.Cell;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

class GenerateNewGameTest {

    @Test
    void GivenRequestNewGame_WhenGenerateNewGameGenerate_ThenReturnListCell(){
        GenerateNewGame generateNewGame = new GenerateNewGame();
        List<Cell> cells = generateNewGame.generate();
        assertThat(cells.size(), is(greaterThan(0)));
    }

    @Test
    void GivenRequestNewGame_WhenGenerateNewGameGenerate_ThenReturnListCellNotNull(){
        GenerateNewGame generateNewGame = new GenerateNewGame();
        for (int j = 0; j < 10; j++) {
            List<Cell> list = generateNewGame.generate();
            for (Cell cell : list) {
                assertThat(cell, notNullValue());
                assertThat(cell, notNullValue());
            }
        }
    }

}