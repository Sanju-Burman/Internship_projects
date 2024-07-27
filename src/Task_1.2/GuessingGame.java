package front;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GuessingGame extends JFrame implements ActionListener {

	private JTextField guessField;
    private JLabel messageLabel;
    private JButton guessButton, restartButton;
    private int randomNumber, attempts;

    public GuessingGame() {
        super("Guess the Number");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);

        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        restartButton = new JButton("Restart");
        messageLabel = new JLabel();

        guessButton.addActionListener(this);
        restartButton.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(messageLabel);

        add(panel, BorderLayout.CENTER);
        add(restartButton, BorderLayout.SOUTH);

        newGame();
        setVisible(true);
    }

    private void newGame() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        System.out.println("Guess a number is: "+ randomNumber);
        attempts = 0;
        messageLabel.setText("Guess a number between 1 and 100");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;

                if (guess == randomNumber) {
                    messageLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                    guessButton.setEnabled(false);
                    restartButton.setVisible(true);
                } else if(guess<1||guess>100) {
                	
                	messageLabel.setText("number is not between 1 and 100! Try again.");
                }else if (guess < randomNumber) {
                    messageLabel.setText("Too low! Try again.");
                } else {
                    messageLabel.setText("Too high! Try again.");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid input. Please enter a number.");
            }
        } else if (e.getSource() == restartButton) {
            newGame();
            guessButton.setEnabled(true);
            restartButton.setVisible(false);
            guessField.setText("");
        }
    }

    public static void main(String[] args) {
    	System.out.println("Program is started");
        new GuessingGame();
    }
}

