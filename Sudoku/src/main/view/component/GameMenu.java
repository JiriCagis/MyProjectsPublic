package main.view.component;

import main.data.Constant;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import main.data.Level;
import main.view.MainWindowListener;

/**
 * Top menu in application with menus(game,level,help)
 * @author adminuser
 */
public class GameMenu extends JMenuBar {

    private final JMenu gameMenu;
    private final JMenuItem restartGameItem;
    private final JMenuItem nextSudokuItem;
    private final JMenuItem verifySudokuItem;
    private final JMenuItem exitItem;

    private final JMenu levelMenu;
    private final JMenuItem easyLeveltem;
    private final JMenuItem mediumLevelItem;
    private final JMenuItem hardLevelItem;

    private final JMenu helpMenu;
    private final JMenuItem controlItem;
    private final JMenuItem rulesItem;
    private final JMenuItem aboutProgramItem;

    private final MainWindowListener mainWindowListener;

    public GameMenu(int height, MainWindowListener mainWindowListener) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));

        //Game menu
        gameMenu = new JMenu(Constant.MENU_GAME);
        nextSudokuItem = new JMenuItem(Constant.MENU_ITEM_NEXT_GAME);
        verifySudokuItem = new JMenuItem(Constant.MENU_ITEM_VERIFY);
        restartGameItem = new JMenuItem(Constant.MENU_ITEM_RESTART);
        exitItem = new JMenuItem(Constant.MENU_ITEM_EXIT);
        gameMenu.add(verifySudokuItem);
        gameMenu.add(nextSudokuItem);
        gameMenu.add(restartGameItem);
        gameMenu.add(new JSeparator());
        gameMenu.add(exitItem);
        this.add(gameMenu);

        //Level menu
        levelMenu = new JMenu(Constant.MENU_LEVEL);
        easyLeveltem = new JMenuItem(Constant.MENU_ITEM_EASY_LEVEL);
        mediumLevelItem = new JMenuItem(Constant.MENU_ITEM_MEDIUM_LEVEL);
        hardLevelItem = new JMenuItem(Constant.MENU_ITEM_HARD_LEVEL);
        levelMenu.add(easyLeveltem);
        levelMenu.add(mediumLevelItem);
        levelMenu.add(hardLevelItem);
        this.add(levelMenu);

        //help menu
        helpMenu = new JMenu(Constant.MENU_HELP);
        controlItem = new JMenuItem(Constant.MENU_ITEM_CONTROL_HELP);
        rulesItem = new JMenuItem(Constant.MENU_ITEM_RULES);
        aboutProgramItem = new JMenuItem(Constant.MENU_ITEM_ABOUT_PROGRAM);
        helpMenu.add(controlItem);
        helpMenu.add(rulesItem);
        helpMenu.add(aboutProgramItem);
        this.add(helpMenu);

        this.mainWindowListener = mainWindowListener;
        registerMenuListeners();
    }

    private void registerMenuListeners() {
        //Game menu
        nextSudokuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                mainWindowListener.nextSudoku();
            }
        });
        
        restartGameItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                mainWindowListener.restartSudoku();
            }
        });
        
        verifySudokuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                mainWindowListener.verifySudoku();
            }
        });
        
        exitItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                mainWindowListener.onCloseWindow();
            }
        });
        
        //Level menu
        easyLeveltem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                mainWindowListener.setLevel(Level.EASY);
            }
        });
        
        mediumLevelItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                mainWindowListener.setLevel(Level.MEDIUM);
            }
        });
        
        hardLevelItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                mainWindowListener.setLevel(Level.HARD);
            }
        });
        
        //Help menu
        controlItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                HelpDialog helpDialog = new HelpDialog(Constant.HELP_CONTROL_HEADER, Constant.HELP_CONTROL_CONTENT);
                helpDialog.setVisible(true);
            }
        });

        rulesItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                HelpDialog helpDialog = new HelpDialog(Constant.HELP_RULES_HEADER, Constant.HELP_RULES_CONTENT);
                helpDialog.setVisible(true);
            }
        });

        aboutProgramItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                HelpDialog helpDialog = new HelpDialog(Constant.HELP_ABOUT_PROGRAM_HEADER, Constant.HELP_ABOUT_PROGRAM_CONTENT);
                helpDialog.setVisible(true);
            }
        });
    }
    

}
