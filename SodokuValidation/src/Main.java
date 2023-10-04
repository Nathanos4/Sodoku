import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class Main {

    public static boolean LoadFile(String filename, int[][] data){
        boolean file = true;
        File solution = new File(filename);
        int c;
        int row = 0;
        int col = 0;

        try {
            Scanner in = new Scanner(solution);
            while(in.hasNext()){
                c = in.nextInt();
                data[row][col] = c;
                if(col == 8){
                    col = 0;
                    row++;
                }else{
                    col++;
                }
            }

            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    if(data[i][j] == 0){
                        file = false;
                    }
                }
            }
        }catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            file = false;
        }
        return file;
    }

    public static void Display(int[][] data){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(data[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean TestRow(int[][] data, int row){
        for(int i = 0; i < 9; i++){
            for(int j = i + 1; j < 9; j++) {
                if (data[row][j] == data[row][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean TestCol(int[][] data, int col){
        for(int i = 0; i < 9; i++){
            for(int j = i + 1; j < 9; j++) {
                if (data[j][col] == data[i][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean TestBox(int[][] data, int box){
        int row, col;
        int[][] temp = new int[1][9];
        if(box == 0){
            row = 1;
            col = 1;
        }else if(box == 1){
            row = 1;
            col = 4;
        }else if(box == 2){
            row = 1;
            col = 7;
        }else if(box == 3){
            row = 4;
            col = 1;
        }else if(box == 4){
            row = 4;
            col = 4;
        }else if(box == 5){
            row = 4;
            col = 7;
        }else if(box == 6){
            row = 7;
            col = 1;
        }else if(box == 7){
            row = 7;
            col = 4;
        }else{
            row = 7;
            col = 7;
        }
        temp[0][0] = data[row - 1][col - 1];
        temp[0][1] = data[row - 1][col];
        temp[0][2] = data[row - 1][col + 1];
        temp[0][3] = data[row][col - 1];
        temp[0][4] = data[row][col];
        temp[0][5] = data[row][col + 1];
        temp[0][6] = data[row + 1][col - 1];
        temp[0][7] = data[row + 1][col];
        temp[0][8] = data[row + 1][col + 1];
        return TestRow(temp, 0);
    }

    public static void main(String[] args) {
        int[][] data = new int[9][9];
        boolean invalidRow = true, invalidCol = true, invalidBox = true;


        System.out.print("Enter the filename:");
        Scanner in = new Scanner(System.in);
        String filename = in.nextLine();

        if(LoadFile(filename, data)){
            Display(data);
            for(int row = 0; row < 9; row++){
                if(!TestRow(data, row)){
                    System.out.println("Row " + (row+1) + " is invalid.");
                    invalidRow = false;
                }
            }
            for(int col = 0; col < 9; col++){
                if(!TestCol(data, col)){
                    System.out.println("Column " + (col+1) + " is invalid.");
                    invalidCol = false;
                }
            }
            for(int box = 0; box < 9; box++){
                if(!TestBox(data, box)){
                    System.out.println("Box " + (box+1) + " is invalid.");
                    invalidBox = false;
                }
            }
            if(invalidRow && invalidCol && invalidBox){
                System.out.println("The solution is valid.");
            }

        }else{
            System.out.println("Error reading file.");
        }


    }
}