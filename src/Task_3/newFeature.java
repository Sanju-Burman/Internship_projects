package tttGame;

import java.awt.*;

import javax.swing.*;

public class newFeature {

    // Constants for board size and player symbols
    private static final int BOARD_SIZE = 3;
    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";
    
    private JButton restartButton;
    private JFrame frame;
    private JLabel textLabel;
    private JPanel textPanel, boardPanel;
    private JButton[][] board;

    private String currentPlayer;
    private boolean gameOver;
    private int turns;

    public newFeature() {
        currentPlayer = PLAYER_X;
        gameOver = false;
        turns = 0;

        initializeGUI();
        
        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> restartGame());
        frame.add(restartButton, BorderLayout.SOUTH);
    }

    private void initializeGUI() {
        frame = new JFrame("Tic Tac Toe Game");
        frame.setSize(500, 550);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textLabel = new JLabel("Tic Tac Toe");
        textLabel.setBackground(Color.BLACK);
        textLabel.setForeground(Color.YELLOW);
        textLabel.setFont(new Font("Arial", Font.BOLD, 35));
        textLabel.setOpaque(true);

        textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel, BorderLayout.CENTER); // Center alignment

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE, 2, 2)); // Add spacing

        board = new JButton[BOARD_SIZE][BOARD_SIZE];

        // Create and configure buttons
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = new JButton();
                board[row][col].setFont(new Font("Arial", Font.BOLD, 105));
                board[row][col].setFocusable(false);

                board[row][col].addActionListener(e -> {
                    JButton clickedButton = (JButton) e.getSource();
                    if (!gameOver && clickedButton.getText().isEmpty()) {
                        clickedButton.setText(currentPlayer);
                        turns++;
                        checkWinner();
                        if (!gameOver) {
                            currentPlayer = currentPlayer.equals(PLAYER_X) ? PLAYER_O : PLAYER_X;
                            textLabel.setText(currentPlayer + "'s Turn");
                        }
                    }
                });

                boardPanel.add(board[row][col]);
            }
        }

        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    private void restartGame() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col].setText("");
                board[row][col].setBackground(null);
                board[row][col].setForeground(Color.BLACK);
                board[row][col].setEnabled(true); // Re-enable buttons
            }
        }
        currentPlayer = PLAYER_X;
        gameOver = false;
        turns = 0;
        textLabel.setText(currentPlayer + "'s Turn");
    }

    
    private void checkWinner() {
        final String EMPTY_CELL = "";

        // Check rows and columns
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (!board[i][0].getText().equals(EMPTY_CELL) &&
                    board[i][0].getText().equals(board[i][1].getText()) &&
                    board[i][1].getText().equals(board[i][2].getText())) {
                handleWinner(board[i][0]);
                return;
            }

            if (!board[0][i].getText().equals(EMPTY_CELL) &&
                    board[0][i].getText().equals(board[1][i].getText()) &&
                    board[1][i].getText().equals(board[2][i].getText())) {
                handleWinner(board[0][i]);
                return;
            }
        }

        // Check diagonals
        if (!board[0][0].getText().equals(EMPTY_CELL) &&
                board[0][0].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][2].getText())) {
            handleWinner(board[0][0]);
            return;
        }

        if (!board[0][2].getText().equals(EMPTY_CELL) &&
                board[0][2].getText().equals(board[1][1].getText()) &&
                board[1][1].getText().equals(board[2][0].getText())) {
            handleWinner(board[0][2]);
            return;
        }

        // Check for tie
        if (turns == BOARD_SIZE * BOARD_SIZE) {
            handleTie();
        }
    }

    private void handleWinner(JButton winningButton) {
        gameOver = true;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col].setEnabled(false); // Disable all buttons
            }
        }
        winningButton.setBackground(Color.GREEN);
        textLabel.setText(currentPlayer + " wins!");
    }

    private void handleTie() {
        gameOver = true;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col].setEnabled(false); // Disable all buttons
            }
        }
        textLabel.setText("It's a tie!");
    }
}
