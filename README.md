![Sudoku Game image](/docs/images/head.jpeg)
# SUDOKU GAME.


## Description:
Sudoku is a logic-based, combinatorial number-placement puzzle. 
In classic Sudoku, the objective is to fill a 9 × 9 grid with digits so that each column, each row, 
and each of the nine 3 × 3 subgrids that compose the grid (also called "boxes", "blocks", or "regions") 
contains all of the digits from 1 to 9. The puzzle setter provides a partially completed grid, which 
for a well-posed puzzle has a single solution.

## Requirement

![Sudoku Game Requirement](/docs/images/required.png)

## Domain Model

![Sudoku Game image](/docs/images/domain_model.png)

## Glossary

- **Sudoku**	A logic-based, combinatorial number-placement puzzle in which a 9x9 grid must be filled with digits 
  so that each row, column, and 3x3 subgrid contains all of the digits from 1 to 9 exactly once. It is a popular puzzle that can be enjoyed by people of all ages and skill levels. There are many different variants of Sudoku, such as 4x4 Sudoku, irregular Sudoku, and symbol Sudoku.
- **Player**	The person who attempts to solve the Sudoku puzzle. They may also be called a "solver" or a 
  "solutionist".
- **Level**	The difficulty of the Sudoku puzzle, which can range from beginner to expert. Difficulty levels are 
  based on 
  the number of pre-filled digits on the board and the complexity of the strategies required to solve it.
- **Generator**	An algorithm or computer program that is responsible for creating and designing Sudoku puzzles with 
  different difficulty levels. Sudoku generators use mathematical techniques and search algorithms to create valid and challenging puzzles.
- **Board**	The 9x9 grid that is divided into 9 3x3 subgrids and serves as the foundation for the Sudoku game. The 
  Sudoku board may also be called a "grid" or a "square".
- **Row**	A horizontal series of 9 cells on the Sudoku board that may be empty or contain numbers from 1 to 9. Each 
  row is divided into 3 3x3 subgrids. Sudoku rows may also be called "horizontal lines".
- **Column**	A vertical series of 9 cells on the Sudoku board that may be empty or contain numbers from 1 to 9. Each 
  column is divided into 3 3x3 subgrids. Sudoku columns may also be called "vertical lines".
- **Subgrid**	A 3x3 grid that is located within the Sudoku board. Each subgrid must contain all of the digits from 1 
  to 9 exactly once. Sudoku subgrids may also be called "boxes", "blocks", or "regions".
- **Cell**	Each of the 9x9 cells on the Sudoku board.
- **Digit** The numerals used in the game (from 1 to 9)
- **Initial Digit** Given digit by the generator, which cannot be changed
- **Candidate Number**	A number that could potentially be placed in a specific cell but has not yet been confirmed.
- **Color** suggested color schemes for differentiating between initial digits, player digits, hints, and player 
  highlights in Sudoku
- **Solving Strategy**	A technique used to identify the correct placement of numbers on the Sudoku board. Some common 
  strategies include candidate elimination, unique pair hunting, and hidden triple hunting.
- **Single Solution**	A well-posed Sudoku puzzle only has one valid solution.
- **Incomplete Sudoku**	A Sudoku puzzle that has some pre-filled digits but has not yet been completed.
- **Blank Sudoku**	A Sudoku puzzle that has no pre-filled digits and must be completed from scratch.
- **Undo**	Reverses the last action taken by the player, such as placing a digit or removing a highlight.
- **Redo**	Repeats the last action that was undone.
- **Save**	Preserves the current state of the Sudoku puzzle, allowing the player to resume later. 
- **Load**  Retrieves a previously saved Sudoku puzzle and loads it into the game interface.
- **Restart**	Begins Sudoku puzzle, discarding any progress made on the current puzzle.
- **Put**	Places a digit into a selected cell on the Sudoku board.
- **Highlight**	Selects a specific cell  on the Sudoku board, often for the purpose of adding a 
  mark or applying an action.
- **Help**	Provides assistance to the player, such as suggesting possible moves or highlighting potential errors.

## Use Cases

![Use case diagram](/docs/images/use_cases.png)


## System Context Diagram

![Context Domain diagram](/docs/images/context_domain.png)

## Use Cases Prioritize


| Use Case   | Tech | Interaction |
|------------|------|-------------|
| Start      | 5    | 1           |
| Load       | 3    | 3           |
| ShowBoard  | 3    | 1           |
| Save       | 3    | 2           |
| Put        | 0    | 2           |
| Highlight  | 1    | 4           |
| Help       | 1    | 4           |
| Undo       | 1    | 3           |
| Redo       | 1    | 3           |
| Restart    | 0    | 4           |
| Resume     | 0    | 2           |
| Exit       | 0    | 1           |

![Use case prioritization](/docs/images/prioritization.png)

## User Interface

![Welcome view image](/docs/images/welcome_view.png)
![Start menu view image](/docs/images/initital_menu.png)
![load menu view image](/docs/images/load_menu.png)
![Board view image](/docs/images/board.png)
![Game menu view image](/docs/images/game_menu.png)
![Put view image](/docs/images/put_view.png)
![Error value view image](/docs/images/error_value_view.png)
![Highlight view image](/docs/images/highlight_view.png)
![Help view image](/docs/images/help_view.png)
![Error coordinate view image](/docs/images/error_coordinate_view.png)
![Error view](/docs/images/Error_view.png)
![Save menu view image](/docs/images/exit_menu.png)

## UI - Use Cases
![UI Use Case relation](/docs/images/ui_use_case.png)



