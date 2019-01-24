package main.view.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class represent graphic sudoku grid 9x9,
 * You can set sudoku from integer array contains numbers[1-9] and blank space is 
 * signified number [0]. Further is choose change state field(editable, not editable,
 * successful, failure).
 * @author adminuser
 */
public class GameBoard extends JPanel {

    //Sudoku array
    private final JTextField textFields[][] = new JTextField[9][9];

    //Constatnts
    private final int HORIZONAL_GAP = 5;
    private final int VERTICAL_GAP = 5;

    //Color
    private final Color backgroundColor = Color.BLACK;
    private final Color squareBackgroundColor = Color.DARK_GRAY;
    private final Color editableFieldColor = Color.WHITE;
    private final Color notEditableFieldColor = Color.LIGHT_GRAY;
    private final Color successfulFieldColor = Color.GREEN;
    private final Color badFieldColor = Color.RED;

    //Fonts
    private final Font editableFieldFont = new Font("Arial", Font.PLAIN, 18);
    private final Font notEditableFieldFont = new Font("Arial", Font.BOLD, 18);

    //CONSTRUCTORS
    public GameBoard(int[][] array) {
        this.setBackground(backgroundColor);
        prepareFields(array);
        createSquares();
        addFieldsToSquares();
    }

    public int[][] getSudoku() {
        int array[][] = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                String text = textFields[row][column].getText();
                int value = 0;
                    try{
                      value = Integer.parseInt(text);  
                    }
                    catch(Exception e){
                        value =0;
                    }

                array[row][column] = value;
            }
        }
        return array;
    }

    public void setSudoku(int[][] array) {
         NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumIntegerDigits(1);
        
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (array[row][column] > 0 && array[row][column] < 10) {
                    textFields[row][column].setText(array[row][column] + "");
                } else {
                    textFields[row][column].setText("");
                }
            }
        }
    }

    public void setEditable(int row, int column, boolean editable) {
        if (editable) {
            JTextField textField = textFields[row][column];
            textField.setBackground(editableFieldColor);
            textField.setFont(editableFieldFont);
            textField.setEditable(true);
        } else {
            JTextField textField = textFields[row][column];
            textField.setBackground(notEditableFieldColor);
            textField.setFont(notEditableFieldFont);
            textField.setEditable(false);
        }
    }

    public void setSuccessful(int row, int column) {
        JTextField textField = textFields[row][column];
        textField.setBackground(successfulFieldColor);
    }

    public void setBad(int row, int column) {
        JTextField textField = textFields[row][column];
        textField.setBackground(badFieldColor);
    }

    private void prepareFields(int[][] array) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumIntegerDigits(1);

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                JFormattedTextField field = new JFormattedTextField(numberFormat);
                field.setHorizontalAlignment(JTextField.CENTER);
                field.setBackground(editableFieldColor);
                field.setFont(editableFieldFont);
                if(array[row][column]>0 && array[row][column]<=9)
                    field.setText(array[row][column] + "");
                else
                    field.setText("");
                textFields[row][column] = field;
            }
        }
    }

    private void createSquares() {
        this.setLayout(new GridLayout(3, 3, HORIZONAL_GAP, VERTICAL_GAP));
        for (int i = 0; i < 9; i++) {
            JPanel square = new JPanel();
            square.setLayout(new GridLayout(3, 3, HORIZONAL_GAP / 2, VERTICAL_GAP / 2));
            square.setBackground(squareBackgroundColor);
            this.add(square);
        }
    }

    private void addFieldsToSquares() {
        JPanel square1 = (JPanel) this.getComponent(0);
        JPanel square2 = (JPanel) this.getComponent(1);
        JPanel square3 = (JPanel) this.getComponent(2);

        square1.add(textFields[0][0]);
        square1.add(textFields[0][1]);
        square1.add(textFields[0][2]);
        square2.add(textFields[0][3]);
        square2.add(textFields[0][4]);
        square2.add(textFields[0][5]);
        square3.add(textFields[0][6]);
        square3.add(textFields[0][7]);
        square3.add(textFields[0][8]);

        square1.add(textFields[1][0]);
        square1.add(textFields[1][1]);
        square1.add(textFields[1][2]);
        square2.add(textFields[1][3]);
        square2.add(textFields[1][4]);
        square2.add(textFields[1][5]);
        square3.add(textFields[1][6]);
        square3.add(textFields[1][7]);
        square3.add(textFields[1][8]);

        square1.add(textFields[2][0]);
        square1.add(textFields[2][1]);
        square1.add(textFields[2][2]);
        square2.add(textFields[2][3]);
        square2.add(textFields[2][4]);
        square2.add(textFields[2][5]);
        square3.add(textFields[2][6]);
        square3.add(textFields[2][7]);
        square3.add(textFields[2][8]);

        JPanel square4 = (JPanel) this.getComponent(3);
        JPanel square5 = (JPanel) this.getComponent(4);
        JPanel square6 = (JPanel) this.getComponent(5);

        square4.add(textFields[3][0]);
        square4.add(textFields[3][1]);
        square4.add(textFields[3][2]);
        square4.add(textFields[3][3]);
        square4.add(textFields[3][4]);
        square4.add(textFields[3][5]);
        square4.add(textFields[3][6]);
        square4.add(textFields[3][7]);
        square4.add(textFields[3][8]);

        square5.add(textFields[4][0]);
        square5.add(textFields[4][1]);
        square5.add(textFields[4][2]);
        square5.add(textFields[4][3]);
        square5.add(textFields[4][4]);
        square5.add(textFields[4][5]);
        square5.add(textFields[4][6]);
        square5.add(textFields[4][7]);
        square5.add(textFields[4][8]);

        square6.add(textFields[5][0]);
        square6.add(textFields[5][1]);
        square6.add(textFields[5][2]);
        square6.add(textFields[5][3]);
        square6.add(textFields[5][4]);
        square6.add(textFields[5][5]);
        square6.add(textFields[5][6]);
        square6.add(textFields[5][7]);
        square6.add(textFields[5][8]);

        JPanel square7 = (JPanel) this.getComponent(6);
        JPanel square8 = (JPanel) this.getComponent(7);
        JPanel square9 = (JPanel) this.getComponent(8);

        square7.add(textFields[6][0]);
        square7.add(textFields[6][1]);
        square7.add(textFields[6][2]);
        square7.add(textFields[6][3]);
        square7.add(textFields[6][4]);
        square7.add(textFields[6][5]);
        square7.add(textFields[6][6]);
        square7.add(textFields[6][7]);
        square7.add(textFields[6][8]);

        square8.add(textFields[7][0]);
        square8.add(textFields[7][1]);
        square8.add(textFields[7][2]);
        square8.add(textFields[7][3]);
        square8.add(textFields[7][4]);
        square8.add(textFields[7][5]);
        square8.add(textFields[7][6]);
        square8.add(textFields[7][7]);
        square8.add(textFields[7][8]);

        square9.add(textFields[8][0]);
        square9.add(textFields[8][1]);
        square9.add(textFields[8][2]);
        square9.add(textFields[8][3]);
        square9.add(textFields[8][4]);
        square9.add(textFields[8][5]);
        square9.add(textFields[8][6]);
        square9.add(textFields[8][7]);
        square9.add(textFields[8][8]);

    }
}
