import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Calculator {

    private JPanel mainPanel;
    private JButton button0;
    private JButton button4;
    private JButton button7;
    private JTextField calcField;
    private JButton button2;
    private JButton button3;
    private JButton button5;
    private JButton button8;
    private JButton button_dot;
    private JButton button6;
    private JButton button9;
    private JButton button_cl;
    private JButton buttonDiv;
    private JButton buttonMinus;
    private JButton buttonTotal;
    private JButton button1;
    private JButton buttonExp;
    private JButton buttonMult;
    private JButton buttonPlus;
    private JButton buttonRemove;

    public Calculator() {
        List<JButton> calcButtons = Arrays.asList(
                button0,
                button4,
                button7,
                button2,
                button3,
                button5,
                button8,
                button_dot,
                button6,
                button9,
                button1,
                buttonPlus,
                buttonExp,
                buttonDiv,
                buttonMinus,
                buttonMult
        );

        for (JButton button : calcButtons) {
            button.addActionListener(__ -> calcField.setText(calcField.getText() + button.getText()));
        }
        buttonRemove.addActionListener(__ -> calcField.setText(calcField.getText()
                .substring(
                        0,
                        calcField.getText()
                                .length() - 1
                )));

        buttonTotal.addActionListener(__ -> startCalculationLogic(calcField.getText()));
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Calculator");
        Calculator calculator = new Calculator();
        jFrame.setContentPane(calculator.mainPanel);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
