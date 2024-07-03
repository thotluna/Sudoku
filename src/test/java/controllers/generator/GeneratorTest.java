package controllers.generator;

import controllers.GeneratorBoard;
import models.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GeneratorTest {

    GeneratorBoard generator;
    Board initial;
    Board solution;

    @BeforeEach
    void setUp() {
        generator = new Generator();
        generator.generateSudoku();
        initial = generator.getBoardInitial();
        solution = generator.getBoardSolution();
    }

    @Test
    void GiveGenerateGame_WhenIsEmptyCompleteCalled_TheBoardFalseAndSolutionFalse(){
        assertThat(initial.isEmptyComplete(), is(false));
        assertThat(solution.isEmptyComplete(), is(false));
    }

    @Test
    void GiveGenerateGame_WhenIsCompleteCalled_TheBoardFalseAndSolutionTrue(){
        assertThat(initial.isComplete(), is(false));
        assertThat(solution.isComplete(), is(true));
    }
}