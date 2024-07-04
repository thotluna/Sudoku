package dtos;


import java.util.Arrays;
import java.util.Objects;

public record BoardDto(int dimension, CellDto[][] board) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return dimension == boardDto.dimension && Arrays.deepEquals(board, boardDto.board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dimension);
        result = 31 * result + Arrays.deepHashCode(board);
        return result;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "dimension=" + dimension +
                ", board=" + Arrays.toString(board) +
                '}';
    }
}
