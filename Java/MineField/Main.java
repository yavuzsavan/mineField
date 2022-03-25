package Java.MineField;

import java.util.*;

public class Main {
    public static void main(String[] args){
        int row, col;
        Scanner scan = new Scanner(System.in);

        System.out.println("Decide your board size");
        System.out.println("******************************");
        System.out.print("Row: ");
        row = scan.nextInt();
        System.out.print("Colum: ");
        col = scan.nextInt();

        System.out.println("When giving row and column number start from 0");
        MineSweeper mine = new MineSweeper(row,col);
        mine.run();
    }
}