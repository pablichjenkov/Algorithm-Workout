import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {


    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("sudoku-matcher/input.txt"));
            int testCasesCount = lineToInt(br.readLine());

            while (testCasesCount > 0) {
                testCasesCount --;

                ArrayList<ArrayList<Character>> array = new ArrayList<>();
                int matrixSize = lineToInt(br.readLine());

                for (int i = 0; i < matrixSize; i++) {
                    ArrayList<Character> row = new ArrayList<>();
                    String line = br.readLine();
                    char[] charArray = line.toCharArray();

                    for (int j = 0; j < charArray.length; j+=2) {
                        row.add(charArray[j]);
                    }

                    array.add(row);
                }

                solveSudoku(array);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int lineToInt(String text) {
        return Integer.parseInt(text.trim());
    }

    public static void solveSudoku(ArrayList<ArrayList<Character>> array) {
        boolean found = solveSudokuRec(array, 0, -1);
        if (found) {
            System.out.println("Found match");
        }
    }

    static boolean solveSudokuRec(ArrayList<ArrayList<Character>> array, int row, int col) {

        //printMatrix(array);

        // evaluate current array, if matches a sudoku return true here.
        boolean sudokuSolved = false;
        boolean rowsOk = validateRows(array);
        boolean colsOk = validateCols(array);

        sudokuSolved = rowsOk && colsOk;
        if (sudokuSolved) {
            printMatrix(array);
            return true;
        }

        int len = array.size();


        int nextCol = nextCol(row, col, len);
        int nextRow = nextRow(row, col, len);

        if (nextCol >= len || nextRow >= len) {
            return false;
        }

        // Lets assume slot values go from 1 ... len.
        for (int i=1; i<=len; i++) {
            ArrayList<Character> sudoRow = array.get(nextRow);
            sudoRow.set(nextCol, Character.forDigit(i, 10));
            sudokuSolved = solveSudokuRec(array, nextRow, nextCol);
            if (sudokuSolved) {
                return true;
            }
        }

        return false;
    }

    static int nextRow(int row, int col, int size) {
        col++;
        if (col >= size) {
            return row + 1;
        }

        return row;
    }

    static int nextCol(int row, int col, int size) {
        col++;
        if (col >= size) {
            return 0;
        }

        return col;
    }

    static boolean validateRows(ArrayList<ArrayList<Character>> array) {
        int rowCount = array.size();

        for (int j = 0; j < rowCount; j++) {
            ArrayList<Character> rowIter = array.get(j);

            boolean isValidRow = validateNoDuplicate(rowIter);
            if (!isValidRow) {
                return false;
            }
        }
        return true;
    }

    static boolean validateCols(ArrayList<ArrayList<Character>> array) {
        int rowCount = array.size();
        ArrayList<Character> firstRow = array.get(0);
        int colCount = firstRow.size();

        for (int i = 0; i < colCount; i++) {
            ArrayList<Character> columnIter = new ArrayList<Character>();

            for (int j = 0; j < rowCount; j++) {
                ArrayList<Character> rowIter = array.get(j);
                columnIter.add(rowIter.get(i));
            }

            boolean isValidColumn = validateNoDuplicate(columnIter);
            if (!isValidColumn) {
                return false;
            }
        }

        return true;
    }

    static boolean validateNoDuplicate(ArrayList<Character> line) {
        int count = 0;
        for (Character chIter : line) {
            count = getCharCount(line, chIter);
            if (count > 1) {
                return false;
            }
        }

        count = getCharCount(line, new Character('.'));
        if (count > 0) {
            return false;
        }

        return true;
    }

    static int getCharCount(ArrayList<Character> line, Character ch) {
        int count = 0;
        for (Character chIter : line) {
            if (chIter.equals(ch)) {
                count ++;
            }
        }

        return count;
    }

    static ArrayList<ArrayList<Character>> copyMatrix(ArrayList<ArrayList<Character>> array) {

        ArrayList<ArrayList<Character>> newMatrix = new ArrayList<>();

        for (ArrayList<Character> row : array) {
            ArrayList<Character> newRow = new ArrayList<>();

            for (Character ch : row) {
                newRow.add(new Character(ch));
            }

            newMatrix.add(newRow);
        }

        return newMatrix;
    }

    static void printMatrix(ArrayList<ArrayList<Character>> array) {
        for (ArrayList<Character> row : array) {
            for (Character ch : row) {
                System.out.print(ch);
                System.out.print(", ");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

}
