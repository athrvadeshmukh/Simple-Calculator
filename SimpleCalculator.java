import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener, KeyListener {

    private JTextField textField;
    private JTextArea inputHistory;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton, eqButton, clrButton, dotButton;
    private JPanel panel;

    private double num1, num2, result;
    private char operator;

    public SimpleCalculator() {
        this.setTitle("Simple Calculator");
        this.setSize(400, 400);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        num1 = num2 = result = 0;
        operator = ' ';

        textField = new JTextField();
        textField.setBounds(50, 10, 300, 50);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setEditable(false);
        textField.addKeyListener(this);

        inputHistory = new JTextArea();
        inputHistory.setBounds(50, 70, 300, 50);
        inputHistory.setFont(new Font("Arial", Font.PLAIN, 14));
        inputHistory.setEditable(false);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        functionButtons = new JButton[7];
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        eqButton = new JButton("=");
        clrButton = new JButton("C");
        dotButton = new JButton(".");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = eqButton;
        functionButtons[5] = clrButton;
        functionButtons[6] = dotButton;

        for (int i = 0; i < 7; i++) {
            functionButtons[i].addActionListener(this);
        }

        panel = new JPanel();
        panel.setBounds(50, 130, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(dotButton);
        panel.add(numberButtons[0]);
        panel.add(clrButton);
        panel.add(divButton);
        panel.add(eqButton);

        this.add(textField);
        this.add(inputHistory);
        this.add(panel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        } else if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        } else if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        } else if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        } else if (e.getSource() == eqButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                    break;
            }

            textField.setText(String.valueOf(result));
        } else if (e.getSource() == clrButton) {
            textField.setText("");
        } else if (e.getSource() == dotButton) {
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText() + ".");
            }
        } else {
            // Handle number buttons
            JButton clickedButton = (JButton) e.getSource();
            textField.setText(textField.getText() + clickedButton.getText());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        char keyChar = e.getKeyChar();

        if (Character.isDigit(keyChar) || keyChar == '.') {
            textField.setText(textField.getText() + keyChar);
        } else if (keyCode == KeyEvent.VK_ENTER) {
            // Handle Enter key as the equals button
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textField.setText("Error");
                        return;
                    }
                    break;
            }

            textField.setText(String.valueOf(result));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in code
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in code
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
