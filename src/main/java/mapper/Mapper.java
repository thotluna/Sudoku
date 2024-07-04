package mapper;

import dtos.BoardDto;
import dtos.CellDto;
import dtos.GameDto;
import models.Board;
import models.Cell;
import models.Coordinate;
import models.Game;
import types.TypeCell;

public class Mapper {

    public CellDto fromCellToCellDto(Cell cell){
        return new CellDto(cell.coordinate().toString(), cell.value(), cell.type().toString().charAt(0));
    }

    public Cell fromCellDtoToCell(CellDto dto){
        return new Cell(new Coordinate(dto.coordinateString()), dto.value(), TypeCell.get(dto.type()));
    }

    public BoardDto fromBoardToBoardDto(Board board){
        CellDto[][] cellsDto = new CellDto[board.getDimension()][board.getDimension()];
        for (int row = 0; row < board.getDimension(); row++) {
            for (int col = 0; col < board.getDimension(); col++) {
                cellsDto[row][col] = fromCellToCellDto(board.getCell(row, col));
            }
        }

        return new BoardDto(board.getDimension(), cellsDto);
    }

    public Board fromBoardDtoToBoard(BoardDto dto){
        Board board = new Board();
        for (int row = 0; row < dto.dimension(); row++) {
            for (int col = 0; col < dto.dimension(); col++) {
                board.addCell(fromCellDtoToCell(dto.board()[row][col]));
            }
        }
        return board;
    }

    public GameDto fromGameToGameDto(Game game){
        BoardDto board = fromBoardToBoardDto(game.getBoard());
        BoardDto solution = fromBoardToBoardDto(game.getSolution());
        BoardDto initial = fromBoardToBoardDto(game.getInitial());


        return new GameDto(initial, solution, board);
    }

    public Game fromGameDtoToGame(GameDto dto){
        Board board = fromBoardDtoToBoard(dto.board());
        Board solution = fromBoardDtoToBoard(dto.solutionBoard());
        Board initial = fromBoardDtoToBoard(dto.initialBoard());
        return new Game(initial, solution, board);
    }
}
