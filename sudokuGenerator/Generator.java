package sudokuGenerator;
import java.util.Random;

public class Generator {
    Random random = new Random();

    public void main(String[] args) {
        Integer[][] sudoku = createEmptySudoku();
        fillDiagonalSquares(sudoku);
        printSudoku(sudoku);
    }

    private Integer[][] createEmptySudoku() {
        Integer[][] sudoku = new Integer[9][9];
        for(int i=0; i<sudoku.length; i++){
            for(int j=0; j<sudoku[0].length; j++){
                sudoku[i][j] = 0;
            }         
        }
        return sudoku;
    }

    private void printSudoku(Integer[][] array){
        for(int i=0; i<array.length; i++){
                for(int j=0; j<array[0].length; j++){
                        System.out.print(array[i][j]);
                }
                System.out.println("");         
        }
    }

    private Integer[][] fillSquare(Integer[][] sudoku, int start, int end) {
        for(int hor=start; hor<end; hor++){
            for(int ver=start; ver<end; ver++){
                sudoku[hor][ver] = random.nextInt(8)+1;
            }
        } 
        return sudoku;
    }

    private Integer[][] fillDiagonalSquares(Integer[][] sudoku){
        fillSquare(sudoku, 0, 3);
        fillSquare(sudoku, 3, 6);
        fillSquare(sudoku, 6, 9);
        return sudoku;
    }
}