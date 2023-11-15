package models;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final char PLAYER_HUMAN = 'X';
    private static final char PLAYER_COMPUTER = 'O';
    private static boolean gameOver;

    static char[][] board;

    static {
        board = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }


    public static void main(String[] args) {
        char currentPlayer = PLAYER_HUMAN;
        gameOver = false;

        while (!gameOver) {
            displayBoard(board);
            if(currentPlayer == PLAYER_HUMAN) {
                humanMove();
            } else {
                computerMove();
            }

            if(gameOver){
                displayBoard(board);
                System.out.println("Player " + currentPlayer + " has won! ");
            } else {
                currentPlayer = (currentPlayer == PLAYER_HUMAN) ? PLAYER_COMPUTER : PLAYER_HUMAN;
            }
        }
    }

    public static void displayBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col]);
                if (col < board.length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }


    public static void humanMove() {
        System.out.println("Please enter row and colum: ");
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        if (board[row][col] == ' ') {
            board[row][col] = PLAYER_HUMAN;
            gameOver = hasWon(PLAYER_HUMAN);
        } else {
            System.out.println("Invalid Indexes Entered!!!");
        }
    }

    public static void computerMove() {
        Random random = new Random();
        while (true) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);
            if (board[row][col] == ' ') {
                board[row][col] = PLAYER_COMPUTER;
                gameOver = hasWon(PLAYER_COMPUTER);
                break;
            }
        }
    }


    // Function to check if the current player has won
    static boolean hasWon(char player) {
        // Check rows
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // check columns
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // and diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        if (board[0][1] == player && board[1][1] == player && board[2][1] == player) {
            return true;
        }
        //no winners
        return false;
    }

}
