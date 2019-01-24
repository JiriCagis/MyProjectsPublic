package main.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import main.data.Constant;
import main.data.Level;
import main.data.Sudoku;
import main.logic.SudokuManager;
import main.logic.SudokuManagerImpl;
import main.view.component.GameBoard;
import main.view.component.GameHeader;
import main.view.component.GameMenu;
import utils.xmlParser.XmlParser;
import utils.xmlParser.XmlParserImpl;

public class MainWindow extends JFrame implements MainWindowListener {

    //Gui compotents
    private final GameHeader header;
    private final GameMenu gameMenu;
    private final GameBoard gameBoard;
    private final Dimension windowsSize = new Dimension(500, 620);

    //Logic
    private Sudoku sudoku;
    private final SudokuManager sudokuManager;
    private final File xmlFile = new File("configurateWindows.xml");
    private final XmlParser<Object> xmlParser;

    public MainWindow() {
        setTitle(Constant.APP_NAME + " " + Constant.APP_VERSION);
        xmlParser = new XmlParserImpl<Object>();
        loadWindowSetting();
        setLayout(new BorderLayout());

        sudokuManager = new SudokuManagerImpl(Level.EASY);
        sudoku = sudokuManager.loadSaved();
        if (sudoku == null) {
            sudoku = sudokuManager.next();
        }

        //top section
        JPanel topSection = new JPanel();
        topSection.setPreferredSize(new Dimension(getWidth(), 120));
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        header = new GameHeader(getWidth(), 100, this);
        header.setLevel(sudoku.getLevel());
        gameMenu = new GameMenu(20, this);
        topSection.add(gameMenu);
        topSection.add(header);

        //game board
        gameBoard = new GameBoard(sudoku.getCellValues());
        convertSudokuToGameBoard();
        this.add(topSection, BorderLayout.NORTH);
        this.add(gameBoard, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                onCloseWindow();
            }

        });
    }

    private void loadWindowSetting() {
        List<Object> list = xmlParser.parse(xmlFile);
        if (list.size() == 2) {
            this.setSize((Dimension) list.get(0));
            this.setLocation((Point) list.get(1));
        } else {
            this.setSize(windowsSize);
            this.setLocationRelativeTo(null);
        }
    }

    private void convertSudokuToGameBoard() {
        gameBoard.setSudoku(sudoku.getCellValues());
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                switch (sudoku.getState(row, column)) {
                    case EDITABLE:
                        gameBoard.setEditable(row, column, true);
                        break;
                    case NOT_EDITABLE:
                        gameBoard.setEditable(row, column, false);
                        break;
                    case SUCCESSFUL:
                        gameBoard.setSuccessful(row, column);
                        break;
                    case BAD:
                        gameBoard.setBad(row, column);
                        break;
                }
            }
        }
    }

    private void convertGameBoardToSudoku() {
        int[][] array = gameBoard.getSudoku();
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (array[row][column] != sudoku.getValue(row, column)) {
                    sudoku.setCell(row, column, array[row][column]);
                }
            }
        }
    }

    @Override
    public void nextSudoku() {
        this.sudoku = sudokuManager.next();
        convertSudokuToGameBoard();
    }

    @Override
    public void verifySudoku() {
        //prepare sudoku (add user filled field to sudoku with flag edited
        convertGameBoardToSudoku();

        //verify
        sudokuManager.verify(sudoku);

        //show result on game board (green successful, red bad filled)
        convertSudokuToGameBoard();
    }

    @Override
    public void restartSudoku() {
        convertGameBoardToSudoku();
        sudokuManager.restart(sudoku);
        convertSudokuToGameBoard();
    }

    @Override
    public void setLevel(Level level) {
        sudokuManager.setSudokuLevel(level);
        sudoku = sudokuManager.next();
        header.setLevel(level);
        convertSudokuToGameBoard();
    }

    @Override
    public void onCloseWindow() {
        List<Object> list = new ArrayList<Object>();
        list.add(this.getSize());
        list.add(this.getLocation());
        xmlParser.save(list, xmlFile);
        convertGameBoardToSudoku();
        sudokuManager.save(sudoku);
        System.exit(0);
    }

}
