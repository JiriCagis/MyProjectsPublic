package main.view;

import main.data.Level;

public interface MainWindowListener {
    void nextSudoku();
    void verifySudoku();
    void restartSudoku();
    void setLevel(Level level);
    void onCloseWindow();
}
