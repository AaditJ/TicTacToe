package org.sky;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        TicTacToe x = new TicTacToe();
        System.out.println("Welcome to Tic Tac Toe");

        int position = 0;
        boolean end = false;
        while(!end){
            x.printBoard();
            System.out.println("enter your position (1-9)");
            try{
                position = scan.nextInt();
                if (position > 9 || position < 1){
                    throw new ArrayIndexOutOfBoundsException();
                }

                if (!x.validPosition(position-1)){throw new InputMismatchException();};
                x.updateBoard(position-1);

                end = x.checkWin();

            }catch (InputMismatchException inputMismatchException){
                System.out.println(inputMismatchException);
                System.out.println("Please choose a valid option");

            }catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                System.out.println(arrayIndexOutOfBoundsException);
                System.out.println("Please choose a value between 1-9");

            }catch (Exception e){
                System.out.println(e);
                System.out.println("Unknown error");
            }
        }

        String winner = "";
        if (winner.equals("")){
            switch (x.getTurn()){
                case 1:
                    winner = "X";
                    break;
                case -1:
                    winner = "O";
                    break;
            }
        }


        System.out.println("The winner is " + winner);
    }
}



