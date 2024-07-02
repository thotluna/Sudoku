package controllers.generator;

import controllers.GeneratorBoard;
import models.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class GeneratorTest {

    GeneratorBoard generator;

    @BeforeEach
    void setUp() {
        generator = new Generator();

    }

    @Test
    void Give_When_then(){
        int times = 2;
        do{
            generator.generateSudoku();
            Board initial = generator.getBoardInitial();
            Board solution = generator.getBoardSolution();
            assertThat(initial.isComplete(), is(false));
            assertThat(solution.isComplete(), is(true));
            times--;
        }while (times > 0);
    }
}