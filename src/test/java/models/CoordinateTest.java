package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void GiveAColumnAndRowNumberInputCreate_WhenCoordinateToStringCalled_ThenReturnStringCoordinate(){
        int[][] values = new int[][]{{0,0}, {8,8}, {5, 5}};
        String[] expected = new String[]{"A1", "I9", "F6"};
        for (int i = 0; i < values.length; i++) {
            Coordinate coordinate = new Coordinate(values[i][0],values[i][1]);

            assertEquals(expected[i], coordinate.toString());
        }
    }

    @Test
    void GiveAColumnAndRowNumberInputOutRageCreate_WhenCoordinateCalled_ThenThrowAssertion(){
        int[] values = new int[]{-1, 9};
        int okValue = 5;
        for (int value: values) {
            assertThrows(AssertionError.class, () ->  new Coordinate(value,okValue));
            assertThrows(AssertionError.class, () ->  new Coordinate(okValue,value));
            assertThrows(AssertionError.class, () ->  new Coordinate(value,value));
        }

    }

    @Test
    void GiveAColumnAndRowStringInputCreate_WhenCoordinateToStringCalled_ThenReturnStringCoordinate(){

        String[] values = new String[]{"A1", "I9"};
        for (String value : values) {
            Coordinate coordinate = new Coordinate(value);

            assertEquals(value, coordinate.toString());
        }
    }

    @Test
    void GiveAColumnAndRowStringInputOutRageCreate_WhenCoordinateCalled_ThenThrowAssertion(){
        String[] values = new String[]{"a", "@1", "J1", "A12"};
        for (String value: values) {
            assertThrows(AssertionError.class, () ->  new Coordinate(value));
        }

    }

}