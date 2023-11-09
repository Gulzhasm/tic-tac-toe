import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final char PLAYER_HUMAN = 'X';
    private static final char PLAYER_COMPUTER = 'O';
    static char[][] board;

    static {
        board = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }

    public static void main(String[] args) {
        System.out.println("Welcome to TicTacToe Game!");
        displayBoard(board);
        char currentPlayer = PLAYER_HUMAN;
        while (true) {
            if (currentPlayer == PLAYER_HUMAN) {
                humanMove();
            } else {
                computerMove();
            }
            displayBoard(board);

            // switch players
            if (currentPlayer == PLAYER_HUMAN) {
                currentPlayer = PLAYER_COMPUTER;
            } else {
                currentPlayer = PLAYER_HUMAN;
            }

            //check wins
            if (checkWin(currentPlayer)) {
                if (currentPlayer == 'X') {
                    System.out.println("You won! Congrats!");
                } else {
                    System.out.println("Computer wins!");
                }
                break;
            }

            //if board is full
            if (isBoardFull()) {
                System.out.println("it is a draw!");
                break;
            }

        }
    }

    public static void displayBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < board.length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    public static int checkAvailableCell() {
        int availableCell = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    availableCell++;
                }
            }
        }
        return availableCell;
    }

    static boolean isBoardFull() {
        if (checkAvailableCell() > 0) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == ' ') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void humanMove() {
        System.out.println("Please Enter Row and Column index starts [0][0] and ends with [2][2]");
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        if (checkAvailableCell() > 0) {
            if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
                if (board[row][col] == ' ') {
                    board[row][col] = PLAYER_HUMAN;
                } else {
                    System.out.println(" The cell is not empty!");
                }
            }
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
                break;
            }
        }
        //   if (checkAvailableCell() > 0) {
//            int randomX = random.nextInt(checkAvailableCell());
//            int index = 0;
//
//            for (int i = 0; i < board.length; i++) {
//                for (int j = 0; j < board[i].length; j++) {
//                    if (board[i][j] == ' ') {
//                        if (index == randomX) {
//                            board[i][j] = PLAYER_COMPUTER;
//                            System.out.println("Computer placed the move '0' to indexes: " + i + " and " + j);
//                        }
//                    }
//                    index++;
//                }
//            }
        // }
    }


    // Function to check if the current player has won
    static boolean checkWin(char CURRENT_PLAYER) {
        // Check rows
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == CURRENT_PLAYER && board[i][1] == CURRENT_PLAYER && board[i][2] == CURRENT_PLAYER) {
                return true;
            }
        }
        // columns
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == CURRENT_PLAYER && board[1][i] == CURRENT_PLAYER && board[2][i] == CURRENT_PLAYER) {

                return true;
            }
        }
        // and diagonals
        if (board[0][0] == CURRENT_PLAYER && board[1][1] == CURRENT_PLAYER && board[2][2] == CURRENT_PLAYER) {
            return true;
        }
        if (board[0][2] == CURRENT_PLAYER && board[1][1] == CURRENT_PLAYER && board[2][0] == CURRENT_PLAYER) {
            return true;
        }

        if (board[0][1] == CURRENT_PLAYER && board[1][1] == CURRENT_PLAYER && board[2][1] == CURRENT_PLAYER) {
            return true;
        }
        //no winners
        return false;
    }
}
