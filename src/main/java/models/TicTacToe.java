package models;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private final char PLAYER_HUMAN = 'X';
    private final char PLAYER_COMPUTER = 'O';
    private boolean gameOver = false;
    char[][] board = new char[][]{
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };


    public void play() {
        char currentPlayer = PLAYER_HUMAN;

        while (!gameOver) {
            displayBoard();
            if (currentPlayer == PLAYER_HUMAN) {
                humanMove();
            } else {
                computerMove();
            }

            if (gameOver) {
                displayBoard();
                System.out.println("Player " + currentPlayer + " has won! ");
            } else {
                currentPlayer = (currentPlayer == PLAYER_HUMAN) ? PLAYER_COMPUTER : PLAYER_HUMAN;
            }
        }
    }

    public void displayBoard() {
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


    public void humanMove() {
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

    public int[] prioritizedMove() {
        int[][] corners = {
                {0, 0}, // 0
                {0, 2}, // 1
                {2, 0}, // 2
                {2, 2}   //3
        };

        int[][] edges = {
                {0, 1}, // 0
                {1, 0}, // 1
                {1, 2}, // 2
                {2, 1}   //3
        };

        if (board[1][1] == ' ') {
            return new int[]{1, 1};
        }

        for (int[] corner : corners) {
            if (board[corner[0]][corner[1]] == ' ') {
                return corner;
            }
        }

        for (int[] edge : edges) {
            if (board[edge[0]][edge[1]] == ' ') {
                return edge;
            }
        }
        return new int[]{-1, -1};
    }

    public void computerMove() {
        int[] move = prioritizedMove();
        if (move[0] != -1) {
            board[move[0]][move[1]] = PLAYER_COMPUTER;
            gameOver = hasWon(PLAYER_COMPUTER);
        } else {
            System.out.println("it is a draw");
        }
    }


    // Function to check if the current player has won
    public boolean hasWon(char player) {
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
