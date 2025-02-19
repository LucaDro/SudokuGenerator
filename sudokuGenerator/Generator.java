package sudokuGenerator;
import java.util.Random;

public class Generator {
    Random random = new Random();

    public void main(String[] args) {
        Integer[][] sudoku = createEmptySudoku();
        fillDiagonalBoxes(sudoku);
        fillGrid(sudoku);
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

    private Integer[][] fillBox(Integer[][] sudoku, int start, int end) {
        for(int hor=start; hor<end; hor++){
            for(int ver=start; ver<end; ver++){
                fillSquare(sudoku, hor, ver);
            }
        } 
        return sudoku;
    }

    private Integer[][] fillDiagonalBoxes(Integer[][] sudoku){
        fillBox(sudoku, 0, 3);
        fillBox(sudoku, 3, 6);
        fillBox(sudoku, 6, 9);
        return sudoku;
    }

    private Integer[][] fillGrid(Integer[][] sudoku){
        for(int hor=0; hor<9; hor++){
            for(int ver=0; ver<9; ver++){
                if(sudoku[hor][ver] == 0){
                    fillSquare(sudoku, hor, ver);
                }
            }
        }
        return sudoku;
    }

    private Integer[][] fillSquare(Integer[][] sudoku, int horizontal, int vertical){
        boolean found = false;
        OUTERLOOP:
        while(!found) {
            int number = random.nextInt(9) + 1;
            for(int hor=0; hor<9; hor++){
                if(number==sudoku[hor][vertical]){
                    continue OUTERLOOP;
                }
            }
            for(int ver=0; ver<9; ver++){
                if(number==sudoku[horizontal][ver]){
                    continue OUTERLOOP;
                }
            }
            if(checkBox(sudoku, horizontal, vertical, number)){
                continue OUTERLOOP;
            }
            found = true;
            sudoku[horizontal][vertical] = number;
            printSudoku(sudoku);
        }
        return sudoku;
    }

    private boolean checkBox(Integer[][] sudoku, int x, int y, int number){
        int xStart = (x/3)*3;
        int yStart = (y/3)*3;
        for(int hor=xStart; hor<xStart+3; hor++){
            for(int ver=yStart; ver<yStart+3; ver++){
                if(sudoku[hor][ver] == number){
                    return true;
                }
            }
        }
        return false;
    }
}