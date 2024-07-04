package models;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class ContainsValidCellsMatcher extends BaseMatcher<Board> {

    @Override
    public boolean matches(Object o) {
        Board board = (Board) o;
        for (Cell[] row : board.get()) {
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
