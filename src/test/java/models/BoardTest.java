package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import types.TypeCell;

import static models.BoardSupport.getBoardComplete;
import static models.IsBoardMatcher.isEmptyBoard;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void GiveDoesNotExistBoard_WhenNewBoardCreated_ThenReturnBoardFilledNullCell(){
        assertThat(board, isEmptyBoard());
        assertThat(board.get().length, is(9));
    }

    @Test
    void GiveDoesNotExistBoard_WhenNewBoardCWithFourDimensionCreated_ThenReturnBoardFilledNullCell(){
        Board board = new Board(4);
        assertThat(board, isEmptyBoard());
        assertThat(board.get().length, is(4));
    }

    @Test
    void GiveABoardNotComplete_WhenAddCellCandidateCalled_ThenBoardSaved(){
        Cell cell = Cell.newCellCandidate(0,0,1);
        board.addCell(cell);

        assertThat(board, not(isEmptyBoard()));
        assertThat(board.getCell(0, 0), is(equalTo(cell)));
    }

    @Test
    void GiveBoardNotComplete_WhenAddCellWithCellFixedCalled_ThenThrowAssertion(){
        Cell cell = new Cell(new Coordinate("A1"), 1, TypeCell.FIXED);

        assertThrows( AssertionError.class, () -> board.addCell(cell) );
    }

    @Test
    void GiveBoardNotComplete_WhenAddCellWithCellFixedAndOverrideCalled_ThenBoardSaved(){
        Cell cell = new Cell(new Coordinate("A1"), 1, TypeCell.FIXED);
        board.addCell(cell, true);

        assertThat(board, not(isEmptyBoard()));
        assertThat(board.getCell(0, 0), is(equalTo(cell)));
    }

    @Test
    void GiveBoardNotComplete_WhenGetCellWithRowOutRageCalled_ThenThrowAssertion(){
        assertThrows(AssertionError.class, () -> board.getCell(-1, 0));
        assertThrows(AssertionError.class, () -> board.getCell(9, 0));
    }

    @Test
    void GiveBoardNotComplete_WhenGetCellWithColumnOutRageCalled_ThenThrowAssertion(){
        assertThrows(AssertionError.class, () -> board.getCell(0, -1));
        assertThrows(AssertionError.class, () -> board.getCell(0, 9));
    }

    @Test
    void GiveBoardNotComplete_WhenGetCellCalled_ThenReturnCell(){
        Board board = getBoardComplete();
        Cell cell = board.getCell(0, 0);


        assertThat(board, not(isEmptyBoard()));
        assertThat(cell.isEmpty(), is(false));
    }

    @Test
    void GiveBoardNotComplete_WhenGetValueCalled_ThenReturnCellValue(){
        Cell cell = Cell.newCellCandidate(0,0,1);
        board.addCell(cell);

        assertThat(board, not(isEmptyBoard()));
        assertThat(board.getValue(0, 0), is(1));
        assertThat(board.getValue(0,1), is(0));
    }

    @Test
    void GiveBoardNotComplete_WhenIsNullCellCalled_ThenReturnIsNull(){
        Cell cell = Cell.newCellCandidate(0,0,1);
        board.addCell(cell);

        assertThat(board, not(isEmptyBoard()));
        assertThat(board.isNullCell(0,0), is(false));
        assertThat(board.isNullCell(0,1), is(true));
    }

    @Test
    void GiveBoard_WhenIsBusyACellWithCoordinateCalled_ThenReturnIsBusy(){
        Coordinate coordinateBusy = new Coordinate("A1");
        Cell cellBusy = new Cell(coordinateBusy, 1, TypeCell.FIXED);
        board.addCell(cellBusy, true);

        Cell cell = Cell.newCellCandidate(0, 1, 2);
        board.addCell(cell, true);

        assertThat(board, not(isEmptyBoard()));
        assertThat(board.isBusyCell(coordinateBusy), is(true));
        assertThat(board.isBusyCell(0,1), is(false));
        assertThat(board.isBusyCell(0,2), is(false));
    }

    @Test
    void GiveABoard_WhenCopyCalled_thenReturnANewBoardEqual(){
        Board board = getBoardComplete();
        Board boardCopy = board.newCopy();

        assertThat(boardCopy, is(equalTo(board)));
        assertThat(boardCopy, is(not(sameInstance(board))));
        assertThat(boardCopy.isComplete(), is(true));
    }

    @Test
    void GiveABoardComplete_WhenIsCompleteCalled_ThenReturnTrue(){
        Board board = getBoardComplete();
        assertThat(board.isComplete(), is(true));
    }

    @Test
    void GiveBoard_WhenHasValueInRow_ReturnTrueIfExist(){
        Board board = getBoardComplete();
        int value = board.getValue(1, 1);

        assertThat(board.hasValueInRow(value, 1), is(true));

        board.addCell(Cell.nullCell(1,1));
        assertThat(board.hasValueInRow(value, 1), is(false));
    }

    @Test
    void GiveABoardNoEmpty_WhenHasValueInColumn_ReturnTrueIfExist(){
        Board board = getBoardComplete();
        int value = board.getValue(1, 1);

        assertThat(board.hasValueInColumn(value, 1), is(true));

        board.addCell(Cell.nullCell(1,1));
        assertThat(board.hasValueInColumn(value, 1), is(false));
    }

    @Test
    void GiveABoardNoEmpty_WhenHasValueInSubgrid_ReturnTrueIfExist(){
        Board board = getBoardComplete();
        Coordinate coordinate = new Coordinate(1,1);
        int value = board.getValue(coordinate.getRow(), coordinate.getColumn());

        assertThat(board.hasValueInSubstring(value, coordinate), is(true));
        assertThat(board.hasValueInSubstring(value, new Coordinate("A1")), is(true));

        board.addCell(Cell.nullCell(1,1));
        assertThat(board.hasValueInSubstring(value,coordinate), is(false));
    }

    @Test
    void GiveBoardComplete_WhenIsEmptyCompleteCalled_ThenReturnFalse(){
        Board board = getBoardComplete();

        assertThat(board.isEmptyComplete(), is(false));
    }

    @Test
    void GiveBoardNotComplete_WhenIsEmptyCompleteCalled_ThenReturnFalse(){
        Board board = getBoardComplete();
        board.addCell(Cell.nullCell(0,0));

        assertThat(board.isEmptyComplete(), is(false));
    }

    @Test
    void GiveBoardEmpty_WhenIsEmptyCompleteCalled_ThenReturnTrue(){

        assertThat(board.isEmptyComplete(), is(true));
    }







}