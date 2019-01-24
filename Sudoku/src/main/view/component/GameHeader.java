package main.view.component;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import main.data.Constant;
import main.data.Level;
import main.view.MainWindowListener;

/**
 * Top section bellow menu. Contain text tell information about your level and
 * it have two button for control game(verify Sudoku, next Sudoku)
 * @author adminuser
 */
public class GameHeader extends JPanel {

    //Gui components
    private final JLabel levelLabel;
    private final JLabel verifyGameLabel;
    private final JLabel nextGameLabel;

    //Logic
    private final MainWindowListener mainWindowListener;

    public GameHeader(int width, int height, MainWindowListener mainWindowListener) {
        this.setPreferredSize(new Dimension(width, height));
        this.mainWindowListener = mainWindowListener;

        Font headerFont = new Font("Arial", Font.ITALIC, 36);
        Map<TextAttribute, Integer> fontAttributes = new HashMap<TextAttribute, Integer>();
        fontAttributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        Font controlButtonFont = new Font("Arial", Font.BOLD, 18).deriveFont(fontAttributes);

        levelLabel = new JLabel("Easy");
        verifyGameLabel = new JLabel(Constant.VERIFY_BUTTON_LABEL);
        nextGameLabel = new JLabel(Constant.NEXT_BUTTON_LABEL);
        levelLabel.setFont(headerFont);
        verifyGameLabel.setFont(controlButtonFont);
        nextGameLabel.setFont(controlButtonFont);

        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        JPanel topPanel = new JPanel();
        topPanel.add(levelLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
        bottomPanel.add(verifyGameLabel, BorderLayout.WEST);
        bottomPanel.add(nextGameLabel, BorderLayout.EAST);

        this.add(topPanel);
        this.add(bottomPanel);
        registerLabelListener();
    }

    private void registerLabelListener() {
        nextGameLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                mainWindowListener.nextSudoku();
            }

        });

        verifyGameLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                mainWindowListener.verifySudoku();
            }

        });
    }

    public void setLevel(Level level) {
        switch(level){
            case EASY:
                levelLabel.setText(Constant.EASY_LEVEL_LABEL);
                break;
            case MEDIUM:
                levelLabel.setText(Constant.MEDIUM_LEVEL_LABEL);
                break;
            case HARD:
                levelLabel.setText(Constant.HARD_LEVEL_LABEL);
                break;
        }
    }

}
