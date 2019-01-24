package view.listeners;

import data.ServerInfo;

/**
 * Listener action for main window for remotely call.
 * @author adminuser
 */
public interface MainWindowListener {
    public void updateWindow();
    public void openNewServerDialog();
    public void openConfigServerDialog(ServerInfo serverInfo);
    public void openHelpDialog();
}
