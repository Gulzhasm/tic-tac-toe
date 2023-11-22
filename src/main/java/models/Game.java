package models;

import java.util.Scanner;

public class Game {

    private final char HUMAN = 'X';
    private final char COMPUTER = 'O';
    private boolean isGameOver = false;
    private char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public void play() {
        char currentPlayer = HUMAN;

        while (!isGameOver) {
            printBoard();
            if (currentPlayer == HUMAN) {
                humanMove();
            } else {
                computerMove();
            }
            if (isGameOver) {
                printBoard();
                System.out.println("The player " + currentPlayer + " has won!");
            } else if (isBoardFull()) {
                System.out.println("It is a draw! No one wins!");
                break;
            } else {
                currentPlayer = (currentPlayer == HUMAN) ? COMPUTER : HUMAN;
            }
        }
    }

    void humanMove() {
        System.out.println("Please enter your positions: ");
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        if (board[row][col] == ' ') {
            board[row][col] = HUMAN;
            isGameOver = hasWon(HUMAN);
        } else {
            System.out.println("Please enter valid and empty cell position from [3][3]");
        }
    }

    boolean isBoardFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false; // Board is not full
                }
            }
        }
        return true;
    }

    boolean hasWon(char player) {
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


    void printBoard() {
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

    void computerMove() {
        int[] move = prioritizedMove();
        if (move[0] != -1) {
            board[move[0]][move[1]] = COMPUTER;
            isGameOver = hasWon(COMPUTER);
        } else {
            System.out.println("It is a draw");
        }
    }

    int[] prioritizedMove() {
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

}
