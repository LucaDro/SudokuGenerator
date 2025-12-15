package sudokuGenerator;
import java.util.Random;
import java.util.ArrayList;

public class Generator {
    Random random = new Random();

    public void main(String[] args) {
        // main function to create a sudoku
        Integer[][] sudoku = createEmptySudoku();
        fillSquare(sudoku, 0, 0);
        printSudoku(sudoku);
    }

    private Integer[][] createEmptySudoku() {
        // Creates an empty 9x9 grid that will be the grid that you fill to make the sudoku
        Integer[][] sudoku = new Integer[9][9];
        for(int i=0; i<sudoku.length; i++){
            for(int j=0; j<sudoku[0].length; j++){
                sudoku[i][j] = 0;
            }         
        }
        return sudoku;
    }

    private void printSudoku(Integer[][] array){
        // function to print the sudoku
        for(int i=0; i<array.length; i++){
            for(int j=0; j<array[0].length; j++){
                    System.out.print(array[i][j]);
            }
            System.out.println("");         
        }
    }

    private Integer[][] fillSquare(Integer[][] sudoku, int x, int y){
        // Fills square and recursively fills the next square
        // Returns all the way when everything is filled
        ArrayList<Integer> tried = new ArrayList<>();
        do{
            // Takes random number so that the sudoku is unique every time
            int number = random.nextInt(9) + 1;
            // Checks if random number has already been tried or not
            if(tried.contains(number)){
                continue;
            }
            tried.add(number);
            if(!checkColumn(sudoku, y, number)){
                continue;
            }
            if(!checkRow(sudoku, x, number)){
                continue;
            }
            if(!checkBox(sudoku, x, y, number)){
                continue;
            }
            sudoku[x][y] = number;
            int newX = x + 1;
            int newY = y;
            if(x==8){
                newX = 0;
                newY++;
                if(newY == 9){
                    return sudoku;
                }
            }
            sudoku = fillSquare(sudoku, newX, newY);
            // Checks if sudoku is finished or not, if the last box is -1, then the sudoku is unfinished, otherwise it is finished.
            switch (sudoku[8][8]) {
                case -1:
                    sudoku[8][8] = 0;
                    sudoku[x][y] = 0;
                    continue;
                default:
                    return sudoku;
            }
        }
        while(tried.size() < 8);
        sudoku[8][8] = -1;
        return sudoku;
    }

    private boolean checkColumn(Integer[][] sudoku, int vertical, int number){
        // Checks if a number is already in a column
        for(int hor=0; hor<9; hor++){
            if(number==sudoku[hor][vertical]){
                return false;
            }
        }
        return true;
    }

    private boolean checkRow(Integer[][] sudoku, int horizontal, int number){
        // Checks if a number is already in a row
        for(int ver=0; ver<9; ver++){
            if(number==sudoku[horizontal][ver]){
                return false;
            }
        }
        return true;
    }

    private boolean checkBox(Integer[][] sudoku, int x, int y, int number){
        // Checks if a number is already in a 3x3 box
        int xStart = (x/3)*3;
        int yStart = (y/3)*3;
        for(int hor=xStart; hor<xStart+3; hor++){
            for(int ver=yStart; ver<yStart+3; ver++){
                if(sudoku[hor][ver] == number){
                    return false;
                }
            }
        }
        return true;
    }
}