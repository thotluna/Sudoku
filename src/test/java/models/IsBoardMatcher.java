package models;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Arrays;

public class IsBoardMatcher extends TypeSafeMatcher<Board> {

    public static IsBoardMatcher isEmptyBoard(){
        return new IsBoardMatcher();
    }


    @Override
    protected boolean matchesSafely(Board board) {

        for (Cell[] row : board.get()) {
            if(!Arrays.stream(row).allMatch(Cell::isEmpty)){
                return false;
            }
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Expected empty board \n");

    }
}
