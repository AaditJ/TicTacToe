package org.sky;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TicTacToe {
    private int turn;
    private int score;
    private ArrayList<String> board= new ArrayList<>();

    private HashMap<String, ArrayList<Integer>> values = new HashMap();

    TicTacToe(){
        turn = 1;
        for(int i =0; i < 9; i ++){
            board.add(String.valueOf(i+1));
        }

        values.put("O", new ArrayList<>());
        values.put("X", new ArrayList<>());

        System.out.println(board);
    }

    private void changeTurn(){
        turn *= -1;
        switch (turn){
            case -1:
                System.out.println("It's X's turn");
                break;
            case 1:
                System.out.println("It's O's turn");
                break;
            default:
                System.out.println("Error");
                break;
        }
    }

    public boolean validPosition(int position){
        if (!board.get(position).equals("-")){
            return false;
        }else{return true;}
    }

    public void updateBoard(int position){
        switch (turn){
            case -1:
                board.set(position, "X");
                break;
            case 1:
                board.set(position, "O");
                break;
            default:
                board.set(position, "-");
        }
        changeTurn();
    }

    public void printBoard(){
        System.out.println("|---|---|---|");
        System.out.println("| " + board.get(0) + " | "
                + board.get(1) + " | " + board.get(2)
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board.get(3) + " | "
                + board.get(4) + " | " + board.get(5)
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board.get(6) + " | "
                + board.get(7) + " | " + board.get(8)
                + " |");
        System.out.println("|---|---|---|");
    }

    public boolean checkBoard(){
        for (String val: board) {
            if (!val.equals("-")){
                return false;
            }
        }
        return true;
    }

    public int checkWin(){
        if (checkBoard()){return 0;}

        for(int i =0; i < board.size(); i ++){
            if (board.get(i).equals("X")){
                values.get("X").add(i);
            }else if(board.get(i).equals("O")){
                values.get("O").add(i);
            }
        }

        if (turn == -1){
            if(checkValues(values.get("X").stream().map(x -> x%3).collect(Collectors.groupingBy(x -> x, Collectors.counting())))){return 1;};
            if(checkValues(values.get("X").stream().map(x -> x/3).collect(Collectors.groupingBy(x -> x, Collectors.counting())))){return 1;};
            
        } else if (turn == 1) {
            if(checkValues(values.get("O").stream().map(x -> x%3).collect(Collectors.groupingBy(x -> x, Collectors.counting())))){return 1;};
            if(checkValues(values.get("O").stream().map(x -> x/3).collect(Collectors.groupingBy(x -> x, Collectors.counting())))){return 1;};
            
        }

        if ((board.get(0) == board.get(4) && board.get(0) == board.get(8))|| (board.get(6) == board.get(4) && board.get(6) == board.get(2))){
            return 1;
        }

        return -1;
    }

    private boolean checkValues(Map<Integer, Long> values){
        for (var x:values.entrySet()
        ) {
            if(x.getValue() == 3){
                return true;
            }
        }
        return false;
    }

    public int getTurn(){
        return turn;
    }
}
