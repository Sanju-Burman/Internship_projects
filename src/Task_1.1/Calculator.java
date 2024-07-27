package main_file;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private double num1;
    private double num2;
    private String operator;

    public Calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the display field
        displayField = new JTextField();
        displayField.setEditable(false);
        add(displayField, BorderLayout.NORTH);

        // Create the button panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        String[] buttonLabels = {
            "AC", "C", "%", "/",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "00", "0", ".", "="
        };

        // Add buttons to the panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        // Add the button panel to the center of the frame
        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "AC":
                displayField.setText("");
                num1 = num2 = 0;
                operator = "";
                break;
            case "C":
                displayField.setText("");
                break;
            case "%":
                num1 = Double.parseDouble(displayField.getText());
                displayField.setText(String.valueOf(num1 / 100));
                break;
            case "=":
                num2 = Double.parseDouble(displayField.getText());
                double result = performOperation(num1, num2, operator);
                displayField.setText(String.valueOf(result));
                break;
            case "+":
            case "-":
            case "x":
            case "/":
                operator = command;
                num1 = Double.parseDouble(displayField.getText());
                displayField.setText("");
                break;
            default:
                if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
                    displayField.setText(displayField.getText() + command);
                }
                break;
        }
    }

    private double performOperation(double a, double b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "x":
                return a * b;
            case "/":
                return b != 0 ? a / b : Double.NaN;
            default:
                return Double.NaN;
        }
    }

}
