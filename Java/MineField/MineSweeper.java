package Java.MineField;

import java.util.*;

public class MineSweeper {
    int row, col;
    int bomb = 0 ;
    int totalBomb;
    int selectedRow, selectedCol;
    int counter = 0;
    boolean next ;
    String map[][];
    String board[][];
    Scanner scan = new Scanner(System.in);

    MineSweeper(int row, int col){
        Random random  = new Random();
        this.row = row;
        this.col = col;
        //creating board
        this.board = new String[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                this.board[i][j] = "- ";
            }
        }

        //creating board with bombs for check
        totalBomb = (row*col) / 4;
        if(row*col % 2 == 1){
            totalBomb +=1;
        }
        this.map = new String[this.row][this.col];
        boolean bool = true;
        int count = 0;
        while(bool){
            int i = random.nextInt(this.row);
            int j = random.nextInt(this.row);
            if(map[i][j] == null){
                map[i][j] = "*   ";
                count++;
                if(count==totalBomb) bool = false;
            }
        }
    }

    //printing board with bombs
    void printMap(){
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.print(this.map[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("******************************");
    }

    //printing board
    void printBoard() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.print(this.board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("******************************");
    }

    //taking chosen row and column
    void takeInput() {
        System.out.print("Select a Row: ");
        this.selectedRow = scan.nextInt();
        System.out.print("Select a Colum: ");
        this.selectedCol = scan.nextInt();
        if(this.selectedRow<0 || this.selectedCol<0){
            takeInput();
        }
    }

    //is between our indexes
    boolean isValid() {
        if((0<=this.selectedRow && this.selectedRow < this.row) &&
            (0<=this.selectedCol && this.selectedCol < this.col))
            return true;    
        else{ 
            return false;}
    }

    //edit board with bombs numbers
    void editBoard(boolean isValid){
        String str = "";
        if((this.map[this.selectedRow][this.selectedCol] == null)){
            bombCount();
            str = Integer.toString(bomb)+" ";
            this.board[this.selectedRow][this.selectedCol] = str;
            counter++;
            if(counter == (row*col) - totalBomb){
                System.out.println("******************************");
                System.out.println("You have won!!!\nCONGRATS");
                System.out.println("******************************");
                printMap();
                printBoard();
                next = false;
            }
            
        }else{
            System.out.println("******************************");
            System.out.println("You EXPLODED !!!\nNext time think hard...");
            System.out.println("************BOMBS*************");
            printMap();
            next = false;
        }
    }

    //counting near bombs
    void bombCount(){
        bomb = 0;
        int r = this.selectedRow;
        int c = this.selectedCol;
        for(int i = r-1; i <= r+1; i++){
            for(int j = c-1; j <=c+1; j++){
                if((i>= 0 && i<this.row) && (j>= 0 && j<this.col)){
                    if(this.map[i][j] == "*   "){
                        bomb +=1;
                    }
                }
            }
        }
    }

    void run() {
        next = true;
        while(next){
            printBoard();
            takeInput();
            editBoard(isValid());
        }
    }
}