package controllers;

import models.Cell;
import org.junit.jupiter.api.Test;

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
    void GivenRequestNewGame_WhenGenerateNewGameGenerate_ThenReturnListCellLess250ms(){
        GenerateNewGame generateNewGame = new GenerateNewGame();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            List<Cell> cells = generateNewGame.generate();
            assertThat(cells.size(), is(greaterThan(0)));
        }
        long endTime = System.currentTimeMillis() - startTime;

        assertThat(endTime/10, is(lessThanOrEqualTo(250L)));
    }

}