package models;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class ContainsValidCellsMatcher extends BaseMatcher<Cell[][]> {

    @Override
    public boolean matches(Object o) {
        Cell[][] board = (Cell[][]) o;
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (!cell.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("board is empty");
    }
}
